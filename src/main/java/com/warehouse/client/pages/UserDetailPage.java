package com.warehouse.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.actions.ActionsUserDetail;
import com.warehouse.client.events.AppEventBuilder;
import com.warehouse.client.i18n.I18N;
import org.gwtbootstrap3.client.ui.*;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class UserDetailPage extends Page
{
    @UiField Legend pageTitle;
    @UiField FormLabel lblUserType;
    @UiField ListBox listUserType;
    @UiField FormLabel lblUserName;
    @UiField TextBox txtUserName;
    @UiField FormLabel lblPassword;
    @UiField Input txtPassword;
    @UiField Button btnSave;
    @UiField Button btnCancel;

    @UiTemplate("com.warehouse.client.view.UserDetail.ui.xml")
    interface UserUIBinder extends UiBinder<Widget, UserDetailPage>{}
    private static UserUIBinder binder = GWT.create(UserUIBinder.class);

    public UserDetailPage()
    {
        I18N messages = GWT.create(I18N.class);
        initWidget(binder.createAndBindUi(this));

        pageTitle.setText(messages.userTitle());
        lblUserType.setText(messages.userTypeLabel());
        lblUserName.setText(messages.userNameLabel());
        txtUserName.setPlaceholder(messages.userTxtNamePlaceholder());
        lblPassword.setText(messages.captionPassword());
        txtPassword.setPlaceholder(messages.userTxtPasswordPlaceholder());
        btnSave.setText(messages.captionSave());
        btnCancel.setText(messages.captionCancel());

        RootPanel.get().add(this);
    }

    @UiHandler("btnSave")
    public void onSaveClick(ClickEvent event)
    {
        JSONObject json = new JSONObject();
        json.put(listUserType.getId(), new JSONNumber(1));
        json.put(txtUserName.getId(), new JSONString(txtUserName.getText()));
        json.put(txtPassword.getId(), new JSONString(txtPassword.getText()));

        Warehouse.getEventBus().fireEvent(
                new AppEventBuilder()
                .setPage(this)
                .setSenderID("btnSave")
                .setEvent(event)
                .setAction(ActionsUserDetail.SAVE)
                .setJSONObject(json)
                .build()
        );
    }

    @UiHandler("btnCancel")
    public void onCancelClick(ClickEvent event)
    {
        Window.alert("Cancel click");
    }

}
