package com.warehouse.client.page;

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
import com.warehouse.client.action.ActionUserDetail;
import com.warehouse.client.event.AppEventBuilder;
import org.gwtbootstrap3.client.ui.*;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class UserDetailPage extends Page
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
    interface UserUIBinder extends UiBinder<Widget, UserDetailPage>{}
    private static final UserUIBinder binder = GWT.create(UserUIBinder.class);

    public UserDetailPage()
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

    @UiHandler("btnSave")
    public void onSaveClick(ClickEvent event)
    {
        JSONObject json = new JSONObject();
        json.put(listUserType.getId(), new JSONNumber(1));
        json.put(txtUserName.getId(), new JSONString(txtUserName.getText()));
        json.put(txtPassword.getId(), new JSONString(txtPassword.getText()));

        Warehouse.eventBus.fireEvent(
                new AppEventBuilder()
                .setPage(this)
                .setSenderID("btnSave")
                .setEvent(event)
                .setAction(ActionUserDetail.SAVE)
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
