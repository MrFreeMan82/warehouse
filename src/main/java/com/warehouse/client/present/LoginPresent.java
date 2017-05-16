package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.client.utils.Service;
import com.warehouse.client.utils.ServiceAsync;
import com.warehouse.server.dao.LoginDAO;
import com.warehouse.shared.action.LoginAction;
import com.warehouse.shared.action.RuleAction;
import com.warehouse.shared.dto.Login;
import com.warehouse.shared.dto.Rule;
import com.warehouse.shared.dto.UserSession;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.Input;
import org.gwtbootstrap3.client.ui.PageHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 15.04.2017.
 *
 */
public class LoginPresent extends Present implements LoginAction, RuleAction, AsyncCallback<UserSession>
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
    private List<LoginListener> listeners = new ArrayList<>();
    private ServiceAsync<UserSession> async = GWT.create(Service.class);

    public LoginPresent()
    {
        initWidget(binder.createAndBindUi(this));

        title.setText(Warehouse.i18n.loginPageTitle());
        sendButton.setText(Warehouse.i18n.captionSend());
        sendButton.addClickHandler(clickEvent -> loginByPassword(password.getText()));
        password.setText("12345678");
        label.setText(Warehouse.i18n.captionPassword());

        widgets.putIfAbsent(sendButtonID, sendButton);
        widgets.putIfAbsent(passwordID, password);
        widgets.putIfAbsent("title", title);
    }

    @Override
    public void onFailure(Throwable throwable) {
        for(LoginListener listener: listeners) listener.onFail(throwable.getMessage());
    }

    @Override
    public void onSuccess(UserSession session) {
        if(session == null)
            onFailure(new Exception("Invalid key or password"));

        else {
            title.setText(title.getText() + '[' + session.getUser().getName() + ']');
            for(LoginListener listener: listeners) listener.onSuccess(session);
        }
    }

    @Override
    public UserSession loginByKey(String key)
    {
        Login example = new Login();
        example.setSessionKey(Warehouse.sessionKey);
        example.setKey(key);

        async.selectOne(LoginDAO.LOGIN_BY_KEY, example, this);
        return null;
    }

    @Override
    public UserSession loginByPassword(String password)
    {
        Login example = new Login();
        example.setSessionKey(Warehouse.sessionKey);
        example.setPassword(password);

        async.selectOne(LoginDAO.LOGIN_BY_PASSWORD, example, this);
        return null;
    }

    @Override
    public void addLoginListener(LoginListener listener) {
        listeners.add(listener);
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
