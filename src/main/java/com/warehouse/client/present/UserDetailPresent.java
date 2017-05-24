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
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.shared.Utils;
import com.warehouse.shared.constraint.UserDetailConstraint;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.Type;
import org.gwtbootstrap3.client.ui.*;

import java.util.List;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class UserDetailPresent extends Present implements Dialog {

    private enum Mode{ INSERT, EDIT, VIEW }

    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserType;
    @SuppressWarnings("WeakerAccess") @UiField ListBox userTypeListBox;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblUserName;
    @SuppressWarnings("WeakerAccess") @UiField TextBox txtUserName;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel lblPassword;
    @SuppressWarnings("WeakerAccess") @UiField Input txtPassword;

    @UiTemplate("com.warehouse.client.page.UserDetailPage.ui.xml")
    interface UserUIBinder extends UiBinder<Widget, UserDetailPresent>{}
    private static final UserUIBinder binder = GWT.create(UserUIBinder.class);
    private  ListDTO userTypeList;
    private UserDetail editableUser = new UserDetail();
    private Mode mode;


    UserDetailPresent()
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
            long id = Long.parseLong(userTypeListBox.getValue(userTypeListBox.getSelectedIndex()));
            editableUser.setType(userTypeList.get(id).getId());
        });

        Server.setCallback(this::receiveUserTypes).findList(new Request(Type.USER_TYPE_LIST, new UserType()));
        addValidators();
    }

    UserDetailPresent(UserDetail userDetail){
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
        if(list instanceof ListDTO){
            userTypeList = (ListDTO) list;
            Warehouse.info("receiveUserTypes " + userTypeList.getList().size());
            List<UserType> userTypes = (List<UserType>)userTypeList.getList();
            userTypes.forEach(userType -> userTypeListBox.addItem(
                    userType.getName(), String.valueOf(userType.getId()))
            );
        }
        else if(list instanceof Empty) Warehouse.severe(((Empty) list).getMsg());

        updateView();
    }

    private void addValidators()
    {
        RequiredValidator required = new RequiredValidator();

        txtUserName.addValidator(required);
        txtUserName.addValidator(new SizeValidator(
                UserDetailConstraint.MIN_USER_NAME, UserDetailConstraint.MAX_USER_NAME));

        txtPassword.addValidator(required);
        txtPassword.addValidator(new SizeValidator(UserDetailConstraint.MIN_PASSWORD, UserDetailConstraint.MAX_PASSWORD));
    }

    @Override
    public void onPositive(Modal dialog, Button positiveButton) {
        if(!form.validate() || (userTypeList == null)) return;

        if(mode == Mode.INSERT) {
            Server.setCallback(null).insert(new Request(Type.INSERT_USER, editableUser));

        } else if(mode == Mode.EDIT) {
            Warehouse.info(editableUser.getClass().getName());
            Server.setCallback(null).update(new Request(Type.UPDATE_USER, editableUser));
        }
        dialog.hide();
    }

    @Override
    public void onNeutral(Modal dialog, Button positiveButton) {}

    @Override
    public void onNegative(Modal dialog, Button positiveButton) {
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
