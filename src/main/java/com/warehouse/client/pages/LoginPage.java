package com.warehouse.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.events.AppEvent;
import com.warehouse.client.events.KindOfLogin;
import com.warehouse.client.i18n.I18N;
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


    public LoginPage()
    {
        I18N messages = GWT.create(I18N.class);

        initWidget(binder.createAndBindUi(this));

        title.setText(messages.loginTitle());
        sendButton.setText(messages.send());
        label.setText(messages.passwordText());
        RootPanel.get().add(this);
    }

    @UiHandler("sendButton")
    public void onClick(ClickEvent event)
    {
        Warehouse.getEventBus().fireEvent(
                new AppEvent<>(KindOfLogin.LOGIN, this, password.getText()));
    }
}
