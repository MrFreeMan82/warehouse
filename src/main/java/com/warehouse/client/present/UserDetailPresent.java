package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.Dialog;
import com.warehouse.client.utils.Service;
import com.warehouse.client.utils.ServiceAsync;
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.shared.constraint.UserDetailConstraint;
import com.warehouse.shared.dto.UserTypeDTO;
import org.gwtbootstrap3.client.ui.*;

import java.util.List;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class UserDetailPresent extends Present implements Dialog
{
    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserType;
    @SuppressWarnings("WeakerAccess") @UiField ListBox listUserType;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserName;
    @SuppressWarnings("WeakerAccess") @UiField TextBox txtUserName;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblPassword;
    @SuppressWarnings("WeakerAccess") @UiField Input txtPassword;

    @UiTemplate("com.warehouse.client.page.UserDetailPage.ui.xml")
    interface UserUIBinder extends UiBinder<Widget, UserDetailPresent>{}
    private static final UserUIBinder binder = GWT.create(UserUIBinder.class);


    UserDetailPresent()
    {
        initWidget(binder.createAndBindUi(this));

        lblUserType.setText(Warehouse.i18n.userTypeLabel());
        lblUserName.setText(Warehouse.i18n.userNameLabel());
        txtUserName.setPlaceholder(Warehouse.i18n.userTxtNamePlaceholder());
        lblPassword.setText(Warehouse.i18n.captionPassword());
        txtPassword.setPlaceholder(Warehouse.i18n.userTxtPasswordPlaceholder());

        requestUserTypes();
        addValidators();
    }

    private void requestUserTypes()
    {
        ServiceAsync<List<UserTypeDTO>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey,
               "", new UserTypeDTO(), new AsyncCallback<List<UserTypeDTO>>()
        {
            @Override
            public void onFailure(Throwable throwable) {
                Warehouse.severe("Fail: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(List<UserTypeDTO> userTypes) {
                for(UserTypeDTO user: userTypes)
                    listUserType.insertItem(user.getName(), user.getId().intValue());
            }
        });
    }

    private void addValidators()
    {
        RequiredValidator required = new RequiredValidator();

        txtUserName.addValidator(required);
        txtUserName.addValidator(new SizeValidator(
                UserDetailConstraint.MIN_USER_NAME, UserDetailConstraint.MAX_USER_NAME));

        txtPassword.addValidator(required);
        txtPassword.addValidator(new SizeValidator(UserDetailConstraint.MIN_PASSWORD, UserDetailConstraint.MAX_PASSWORD));
    }

    @Override
    public void onPositive(Modal dialog) {
        Window.alert("Positive");
        form.validate();
    }

    @Override
    public void onNeutral(Modal dialog) {}

    @Override
    public void onNegative(Modal dialog) {
        dialog.hide();
    }

    @Override
    public void show() {}
}
