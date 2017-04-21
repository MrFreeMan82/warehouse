package com.warehouse.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.PageHeader;

/**
 * Created by Дима on 15.04.2017.
 *
 */
public class LoginPage extends Page
{
    @UiField Input password;
    @UiField PageHeader title;
    @UiField Button sendButton;
    @UiField FormLabel label;

    @UiTemplate("com.warehouse.client.view.Login.ui.xml")
    interface LoginUIBinder extends UiBinder<Widget, LoginPage> {}
    private static LoginUIBinder binder = GWT.create(LoginUIBinder.class);
    private static final String sendButtonID = "sendButton";
    private static final String passwordID = "password";


    public LoginPage()
    {
        initWidget(binder.createAndBindUi(this));

        title.setText(Warehouse.i18n.loginPageTitle());
        sendButton.setText(Warehouse.i18n.captionSend());
        sendButton.setId(sendButtonID);
        password.setId(passwordID);
        label.setText(Warehouse.i18n.captionPassword());
        RootPanel.get().add(this);
    }

    @UiHandler(sendButtonID)
    public void onClick(ClickEvent event)
    {

    }
}
