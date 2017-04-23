package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.warehouse.client.Warehouse;
import com.warehouse.client.context.UserDetailContext;
import com.warehouse.client.proxy.UserDetailProxy;
import org.gwtbootstrap3.client.ui.*;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class UserDetailPresent extends Present
{
    @SuppressWarnings("WeakerAccess") @UiField Legend pageTitle;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserType;
    @SuppressWarnings("WeakerAccess") @UiField ListBox listUserType;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserName;
    @SuppressWarnings("WeakerAccess") @UiField TextBox txtUserName;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblPassword;
    @SuppressWarnings("WeakerAccess") @UiField Input txtPassword;
    @SuppressWarnings("WeakerAccess") @UiField Button btnSave;
    @SuppressWarnings("WeakerAccess") @UiField Button btnCancel;

    @UiTemplate("com.warehouse.client.view.UserDetailView.ui.xml")
    interface UserUIBinder extends UiBinder<Widget, UserDetailPresent>{}
    private static final UserUIBinder binder = GWT.create(UserUIBinder.class);

    private static Modal dialog;

    private UserDetailPresent()
    {
        initWidget(binder.createAndBindUi(this));

        pageTitle.setText(Warehouse.i18n.userTitle());
        lblUserType.setText(Warehouse.i18n.userTypeLabel());
        lblUserName.setText(Warehouse.i18n.userNameLabel());
        txtUserName.setPlaceholder(Warehouse.i18n.userTxtNamePlaceholder());
        lblPassword.setText(Warehouse.i18n.captionPassword());
        txtPassword.setPlaceholder(Warehouse.i18n.userTxtPasswordPlaceholder());
        btnSave.setText(Warehouse.i18n.captionSave());
        btnCancel.setText(Warehouse.i18n.captionCancel());

        RootPanel.get().add(this);
    }

    static void showDialog()
    {
        UserDetailPresent page = new UserDetailPresent();
        dialog = new Modal();
        dialog.setClosable(true);
        dialog.setFade(true);

        ModalBody body = new ModalBody();
        ModalFooter footer = new ModalFooter();

        body.add(page);
        footer.add(page.btnSave);
        footer.add(page.btnCancel);
        dialog.add(body);
        dialog.add(footer);
        dialog.show();
    }

    @UiHandler("btnSave")
    void onSave(ClickEvent event)
    {
        UserDetailContext context = Warehouse.requestFactory.userDetailContext();
        UserDetailProxy userProxy = context.create(UserDetailProxy.class);
        userProxy.setName(txtUserName.getText());

        context.save(userProxy).fire(new Receiver<String>()
        {
            @Override
            public void onSuccess(String s) {
                Window.alert(s);
                dialog.hide();
            }
        });
    }

    @UiHandler("btnCancel")
    void onCancel(ClickEvent event) {dialog.hide();}
}
