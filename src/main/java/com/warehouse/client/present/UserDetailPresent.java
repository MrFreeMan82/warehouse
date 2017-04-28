package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.service.AppRemoteService;
import com.warehouse.client.service.AppRemoteServiceAsync;
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.shared.DAOEnum;
import com.warehouse.shared.constraint.UserDetailConstraint;
import com.warehouse.shared.entity.UserType;
import org.gwtbootstrap3.client.ui.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Дима on 21.04.2017.
 *
 */

class UserDetailPresent extends Present
{
    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserType;
    @SuppressWarnings("WeakerAccess") @UiField ListBox listUserType;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserName;
    @SuppressWarnings("WeakerAccess") @UiField TextBox txtUserName;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblPassword;
    @SuppressWarnings("WeakerAccess") @UiField Input txtPassword;

    @UiTemplate("com.warehouse.client.view.UserDetailView.ui.xml")
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

        loadTypes();
        addValidators();
        RootPanel.get().add(this);
    }

    private void loadTypes()
    {

       AppRemoteServiceAsync<List<UserType>> async = GWT.create(AppRemoteService.class);
       async.entityRequest(DAOEnum.USER_TYPE, "getAllTypes", null,  new AsyncCallback<List<UserType>>()
       {
           @Override
           public void onFailure(Throwable throwable) {
               Window.alert(throwable.getMessage());
           }

           @Override
           public void onSuccess(List<UserType> list)
           {
               UserType userType = list.get(0);
             //  String o = new String(userType.getName(), Charset.forName("UTF-8"));
               listUserType.addItem(userType.getName().toString());
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

    void doSaveAndClose(Modal dialog)
    {
        if(!form.validate()) return;

    }
}
