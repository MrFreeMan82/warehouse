package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.utils.Service;
import com.warehouse.client.utils.ServiceAsync;
import com.warehouse.client.Warehouse;
import com.warehouse.client.action.LoginAction;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.entity.Session;
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
public class LoginPresent extends Present implements LoginAction
{
    @UiField Input password;
    @UiField PageHeader title;
    @UiField Button sendButton;
    @UiField FormLabel label;

    @UiTemplate("com.warehouse.client.view.LoginView.ui.xml")
    interface LoginUIBinder extends UiBinder<Widget, LoginPresent> {}
    private static LoginUIBinder binder = GWT.create(LoginUIBinder.class);
    private static final String sendButtonID = "sendButton";
    private static final String passwordID = "password";
    private List<LoginListener> listeners = new ArrayList<>();


    public LoginPresent()
    {
        initWidget(binder.createAndBindUi(this));

        title.setText(Warehouse.i18n.loginPageTitle());
        sendButton.setText(Warehouse.i18n.captionSend());
        sendButton.setId(sendButtonID);
        password.setId(passwordID);
        label.setText(Warehouse.i18n.captionPassword());
    }

    @UiHandler(sendButtonID)
    public void onClick(ClickEvent event)
    {
        loginByPassword(password.getText());
    }

    @Override
    public void loginByKey(String key) {
        Warehouse.logger.info("loginByKey " + key);
        Session params = new Session();
        params.setKey(key);

        ServiceAsync<List<Session>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey, Session.FIND_BY_KEY, params, new AsyncCallback<List<Session>>()
        {
            @Override
            public void onFailure(Throwable throwable) {
                for(LoginListener listener: listeners)
                    listener.onFail(throwable.getMessage());
            }

            @Override
            public void onSuccess(List<Session> session)
            {
                if((session == null) || (session.size() != 1))
                    onFailure(new Exception("Such key '" + key + "' not found" ));

                else for(LoginListener listener: listeners) listener.onSuccess(session.get(0).getUser());
            }
        });
    }

    @Override
    public void loginByPassword(String password) {
        Warehouse.logger.info("loginByKey " + password);
        // ToDo create request to server
    }

    @Override
    public void addLoginListener(LoginListener listener) {
        listeners.add(listener);
    }

    @Override
    public void show() {
        RootPanel.get().add(this);
    }
}
