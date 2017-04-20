package com.warehouse.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.AppController;
import com.warehouse.client.Warehouse;
import com.warehouse.client.actions.ActionsLogin;
import com.warehouse.client.events.AppEventBuilder;
import com.warehouse.client.i18n.I18N;
import com.warehouse.shared.Utils;
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

    @UiTemplate("com.warehouse.client.view.Authorize.ui.xml")
    interface AuthorizeUIBinder extends UiBinder<Widget, LoginPage> {}
    private static AuthorizeUIBinder binder = GWT.create(AuthorizeUIBinder.class);
    private static final String sendButtonID = "sendButton";
    private static final String passwordID = "password";


    public LoginPage()
    {
        I18N messages = GWT.create(I18N.class);

        initWidget(binder.createAndBindUi(this));

        title.setText(messages.loginPageTitle());
        sendButton.setText(messages.captionSend());
        sendButton.setId(sendButtonID);
        password.setId(passwordID);
        label.setText(messages.captionPassword());
        RootPanel.get().add(this);
    }

    @UiHandler(sendButtonID)
    public void onClick(ClickEvent event)
    {
        Warehouse.getEventBus().fireEvent(
                new AppEventBuilder()
                .setEvent(event)
                .setSenderID(sendButtonID)
                .setPage(this)
                .setAction(ActionsLogin.LOGIN)
                .addParam(AppController.MapKeys.LOGIN_PASSWORD, Utils.hashString(password.getText()))
                .build()
        );
    }
}
