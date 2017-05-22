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

public class UserDetailPresent extends Present implements Dialog
{
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
    private UserDetail userDetail;

    UserDetailPresent()
    {
        initWidget(binder.createAndBindUi(this));

        widgets.putIfAbsent(userTypeListBox.getId(), userTypeListBox);
        widgets.putIfAbsent(txtUserName.getId(), txtUserName);
        widgets.putIfAbsent(txtPassword.getId(), txtPassword);

        lblUserType.setText(Warehouse.i18n.userTypeLabel());
        lblUserName.setText(Warehouse.i18n.userNameLabel());
        txtUserName.setPlaceholder(Warehouse.i18n.userTxtNamePlaceholder());
        lblPassword.setText(Warehouse.i18n.captionPassword());
        txtPassword.setPlaceholder(Warehouse.i18n.userTxtPasswordPlaceholder());

        Server.setCallback(this::receiveUserTypes).findList(new Request(Type.USER_TYPE_LIST, new UserType()));
        addValidators();
    }

    UserDetailPresent(UserDetail userDetail){
        this();
        this.userDetail = userDetail;
    }

    private void updateView(){

        if(userDetail == null) return;

        if(userDetail.isLocked()){
            setReadOnly();
        }

        txtUserName.setText(userDetail.getName());
        txtPassword.setText(userDetail.getPassword());

        String id = String.valueOf(userDetail.getUserType().getId());
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

    private void persistStatus(DTO dto){
        if(dto instanceof Empty){
            Warehouse.severe(((Empty) dto).getMsg());
            return;
        }
        Warehouse.info(dto.getRequest().name() + " success");
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

        if(userDetail == null) {
            UserDetail user = new UserDetail();
            user.setStatus(UserListPresent.UserStatus.NEW.getId());
            user.setName(txtUserName.getText());
            user.setPassword(Utils.hashString(txtPassword.getText()));
            long id = Long.parseLong(userTypeListBox.getValue(userTypeListBox.getSelectedIndex()));
            user.setUserType(userTypeList.get(id));
            Server.setCallback(this::persistStatus).insert(new Request(Type.INSERT_USER, user));
        } else {
            Server.setCallback(this::persistStatus).insert(new Request(Type.UPDATE_USER, userDetail));
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
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }
}
