package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.Dialog;
import com.warehouse.client.utils.Server;
import com.warehouse.client.utils.RequestCallBack;
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.shared.Utils;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
import org.gwtbootstrap3.client.ui.*;

import java.util.List;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class UserDetailDialog extends Present implements Dialog {

    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserType;
    @SuppressWarnings("WeakerAccess") @UiField ListBox userTypeListBox;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserName;
    @SuppressWarnings("WeakerAccess") @UiField TextBox txtUserName;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblPassword;
    @SuppressWarnings("WeakerAccess") @UiField Input txtPassword;

    @UiTemplate("com.warehouse.client.page.UserDetailPage.ui.xml")
    interface UserUIBinder extends UiBinder<Widget, UserDetailDialog>{}
    private static final UserUIBinder binder = GWT.create(UserUIBinder.class);
    private HashedDTO userTypeList;
    private UserDetail editableUser = new UserDetail();
    private Mode mode;


    UserDetailDialog()
    {
        mode = Mode.INSERT;
        initWidget(binder.createAndBindUi(this));

        widgets.putIfAbsent(userTypeListBox.getId(), userTypeListBox);
        widgets.putIfAbsent(txtUserName.getId(), txtUserName);
        widgets.putIfAbsent(txtPassword.getId(), txtPassword);

        lblUserType.setText(Warehouse.i18n.userTypeLabel());
        lblUserName.setText(Warehouse.i18n.userNameLabel());
        txtUserName.setPlaceholder(Warehouse.i18n.userTxtNamePlaceholder());
        txtUserName.addValueChangeHandler(valueChangeEvent -> editableUser.setName(valueChangeEvent.getValue()));
        lblPassword.setText(Warehouse.i18n.captionPassword());
        txtPassword.setPlaceholder(Warehouse.i18n.userTxtPasswordPlaceholder());
        txtPassword.addValueChangeHandler(valueChangeEvent -> editableUser.setPassword(Utils.hashString(valueChangeEvent.getValue())));
        userTypeListBox.addChangeHandler(changeEvent -> {
            long index = Integer.parseInt(userTypeListBox.getValue(userTypeListBox.getSelectedIndex()));
            editableUser.setType(userTypeList.get(index).getId());
        });

        Server.setCallback(this::receiveUserTypes).findList(new Request(SQL.USER_TYPE_LIST, new UserType()));
        addValidators();
    }

    UserDetailDialog(UserDetail userDetail){
        this();
        this.editableUser = userDetail;
        mode = Mode.EDIT;
    }

    private void updateView(){

        if(mode == Mode.INSERT) return;

        txtUserName.setText(editableUser.getName());
        txtPassword.setText(editableUser.getPassword());

        String id = String.valueOf(editableUser.getType());
        for(int i = 0; i < userTypeListBox.getItemCount(); i++){

            if(userTypeListBox.getValue(i).equals(id)){
                userTypeListBox.setSelectedIndex(i);
                return;
            }
        }
    }

    private void receiveUserTypes(DTO list)
    {
        if(list instanceof HashedDTO){
            userTypeList = (HashedDTO) list;
            List<UserType> userTypes = (List<UserType>)userTypeList.getList();
            userTypes.forEach(userType -> userTypeListBox.addItem(
                    userType.getName(), String.valueOf(userType.getId()))
            );
        }

        updateView();
    }

    private void addValidators()
    {
        RequiredValidator required = new RequiredValidator();

        txtUserName.addValidator(required);
        txtUserName.addValidator(new SizeValidator(2, 255));

        txtPassword.addValidator(required);
        txtPassword.addValidator(new SizeValidator(1, 255));
    }

    @Override
    public void onPositive(Modal dialog, RequestCallBack callBack) {
        if(!form.validate() || (userTypeList == null)) return;

        if(mode == Mode.INSERT) {
            Server.setCallback(callBack).insert(new Request(SQL.INSERT, editableUser));
        } else if(mode == Mode.EDIT) {
            Server.setCallback(callBack).update(new Request(SQL.UPDATE, editableUser));
        }
        dialog.hide();
    }

    @Override
    public void onNeutral(Modal dialog) {}

    @Override
    public void onNegative(Modal dialog) {
        dialog.hide();
    }

    @Override
    public void setReadOnly() {
        mode = Mode.VIEW;
        lock();
    }

    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }
}
