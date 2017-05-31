package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.utils.Dialog;
import com.warehouse.client.utils.Server;
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.shared.dto.Group;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
import org.gwtbootstrap3.client.ui.*;

/**
 * Created by Дима on 31.05.2017.
 *
 */
public class GroupDialog extends Present implements Dialog {

    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField FormLabel groupNameLabel;
    @SuppressWarnings("WeakerAccess") @UiField TextBox groupNameText;

    @UiTemplate("com.warehouse.client.page.GroupPage.ui.xml")
    interface GroupUIBinder extends UiBinder<Widget, GroupDialog> {}
    private static final GroupUIBinder binder = GWT.create(GroupUIBinder.class);
    private Mode mode;
    private Group parentGroup, editableGroup= new Group();

    GroupDialog(Group parentGroup){
        this.parentGroup = parentGroup;
        mode = Mode.INSERT;
        initWidget(binder.createAndBindUi(this));
        addValidators();
    }

    private void addValidators() {

        groupNameText.addValidator(new RequiredValidator());
        groupNameText.addValidator(new SizeValidator(2, 255));
    }

    @Override
    public void onPositive(Modal dialog) {

        if(! form.validate()) return;

        editableGroup.groupId = parentGroup.getId();
        editableGroup.name = groupNameText.getText();

        if(mode == Mode.INSERT) {
            Server.setCallback(null).insert(new Request(SQL.INSERT_USER, editableGroup));
        }
        else if(mode == Mode.EDIT) {
            Server.setCallback(null).update(new Request(SQL.INSERT_USER, editableGroup));
        }
        dialog.hide();
    }

    @Override
    public void onNeutral(Modal dialog) {

    }

    @Override
    public void onNegative(Modal dialog) {
        dialog.hide();
    }

    @Override
    public void setReadOnly() {

    }


    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }
}
