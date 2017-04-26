package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.AppReceiver;
import com.warehouse.client.AppRequestFactory;
import com.warehouse.client.LogEvent;
import com.warehouse.client.Warehouse;
import com.warehouse.client.proxy.UserDetailProxy;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.shared.constraint.UserDetailConstraint;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.form.validator.Validator;

import java.util.List;

/**
 * Created by Дима on 21.04.2017.
 *
 */

class UserDetailPresent extends Present
{
    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField Legend pageTitle;
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

        pageTitle.setText(Warehouse.i18n.userTitle());
        lblUserType.setText(Warehouse.i18n.userTypeLabel());
        lblUserName.setText(Warehouse.i18n.userNameLabel());
        txtUserName.setPlaceholder(Warehouse.i18n.userTxtNamePlaceholder());
        lblPassword.setText(Warehouse.i18n.captionPassword());
        txtPassword.setPlaceholder(Warehouse.i18n.userTxtPasswordPlaceholder());

        addValidators();
        RootPanel.get().add(this);
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

        AppRequestFactory.UserDetailContext context = Warehouse.requestFactory.userDetailContext();
        UserDetailProxy userProxy = context.create(UserDetailProxy.class);
        userProxy.setUserType(1L);
        userProxy.setName(txtUserName.getText());
        userProxy.setPassword(txtPassword.getText());

        context.save(userProxy).fire(new AppReceiver<String>() {
            @Override
            public void onSuccess(String result) {
                Window.alert(result);
                dialog.hide();
            }
        });
    }
}
