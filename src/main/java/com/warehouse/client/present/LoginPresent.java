package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Application;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.Server;
import com.warehouse.shared.dto.Rule;
import com.warehouse.shared.source.RuleAction;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.PageHeader;

import java.util.List;

/**
 * Created by Дима on 15.04.2017.
 *
 */
public class LoginPresent extends Present implements RuleAction
{
    @SuppressWarnings("WeakerAccess") @UiField Input password;
    @SuppressWarnings("WeakerAccess") @UiField PageHeader title;
    @SuppressWarnings("WeakerAccess") @UiField Button sendButton;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel label;

    @UiTemplate("com.warehouse.client.page.LoginPage.ui.xml")
    interface LoginUIBinder extends UiBinder<Widget, LoginPresent> {}
    private static LoginUIBinder binder = GWT.create(LoginUIBinder.class);
    private static final String sendButtonID = "sendButton";
    private static final String passwordID = "password";

    public LoginPresent()
    {
        initWidget(binder.createAndBindUi(this));

        title.setText(Warehouse.i18n.loginPageTitle());
        sendButton.setText(Warehouse.i18n.captionSend());
        sendButton.addClickHandler(clickEvent ->
                Server.setCallback(Application.getInstance()::onReceiveLoginStatus).loginByPassword(password.getText()));
        password.setText("12345678");
        label.setText(Warehouse.i18n.captionPassword());

        widgets.putIfAbsent(sendButtonID, sendButton);
        widgets.putIfAbsent(passwordID, password);
        widgets.putIfAbsent("title", title);
    }

    @Override
    public void apply(List<Rule> rules) {
        try {
            internalApply(rules);
        } catch (Exception e) {
            Warehouse.severe(e.getMessage());
        }
    }

    @Override
    public void setTitle(String title)
    {
        this.title.setText(title);
    }

    @Override
    public String getTitle()
    {
        return title.getText();
    }

    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }
}
