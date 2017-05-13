package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.utils.Service;
import com.warehouse.client.utils.ServiceAsync;
import com.warehouse.client.Warehouse;
import com.warehouse.server.dao.LoginDAO;
import com.warehouse.server.dao.RuleDAO;
import com.warehouse.shared.action.LoginAction;
import com.warehouse.client.listener.LoginListener;
import com.warehouse.shared.action.RuleAction;
import com.warehouse.shared.dto.LoginDTO;
import com.warehouse.shared.dto.RuleDTO;
import com.warehouse.shared.dto.UserSessionDTO;
import com.warehouse.shared.dto.UserTypeDTO;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.base.ComplexWidget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Дима on 15.04.2017.
 *
 */
public class LoginPresent extends Present implements LoginAction, AsyncCallback<List<UserSessionDTO>>, RuleAction
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
    private List<RuleDTO> rules;


    public LoginPresent()
    {
        initWidget(binder.createAndBindUi(this));

        title.setText(Warehouse.i18n.loginPageTitle());
        sendButton.setText(Warehouse.i18n.captionSend());
        sendButton.setId(sendButtonID);
        sendButton.addClickHandler(clickEvent -> loginByPassword(password.getText()));
        password.setId(passwordID);
        label.setText(Warehouse.i18n.captionPassword());

      //  password.setReadOnly(true);
        widgets = Arrays.asList(sendButton, password);
    }

    @Override
    public void onFailure(Throwable throwable) {
        for(LoginListener listener: listeners) listener.onFail(throwable.getMessage());
    }

    @Override
    public void onSuccess(List<UserSessionDTO> session) {
        if((session == null) || (session.size() != 1))
            onFailure(new Exception("Invalid key or password"));

        else for(LoginListener listener: listeners) listener.onSuccess(session.get(0).getUser());
    }

    @Override
    public List<UserSessionDTO> loginByKey(String key)
    {
        LoginDTO example = new LoginDTO();
        example.setKey(key);

        ServiceAsync<List<UserSessionDTO>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey, LoginDAO.LOGIN_BY_KEY, example, this);
        return null;
    }

    @Override
    public List<UserSessionDTO> loginByPassword(String password)
    {
        LoginDTO example = new LoginDTO();
        example.setPassword(password);

        ServiceAsync<List<UserSessionDTO>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey, LoginDAO.LOGIN_BY_PASSWORD, example, this);
        return null;
    }

    @Override
    public void addLoginListener(LoginListener listener) {
        listeners.add(listener);
    }

    @Override
    public void show() {
        try {
            internalApply(rules);
        } catch (Exception e) {
            Warehouse.severe(e.getMessage());
        }
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }

    @Override
    public List<RuleDTO> requestRules(UserTypeDTO userTypeDTO, String present)
    {
        RuleDTO example = new RuleDTO();
        example.setUserTypeDTO(userTypeDTO);
        example.setPresent(present);

        ServiceAsync<List<RuleDTO>> async = GWT.create(Service.class);
        async.querySelect(Warehouse.sessionKey, RuleDAO.GET_RULES_BY_PRESENT_USERTYPE, example, new AsyncCallback<List<RuleDTO>>()
        {
            @Override
            public void onFailure(Throwable throwable) { Warehouse.severe(throwable.getMessage()); }

            @Override
            public void onSuccess(List<RuleDTO> ruleDTOS) {rules = ruleDTOS;}
        });
        return null;
    }

}
