package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.utils.Dialog;
import com.warehouse.client.utils.Server;
import com.warehouse.client.utils.RequestCallBack;
import com.warehouse.client.validator.RequiredValidator;
import com.warehouse.client.validator.SizeValidator;
import com.warehouse.shared.dto.Group;
import com.warehouse.shared.request.Request;
import org.gwtbootstrap3.client.ui.*;

/**
 * Created by Дима on 31.05.2017.
 *
 */
public class GroupDialog extends Present implements Dialog{

    @SuppressWarnings("WeakerAccess") @UiField Form form;
    @SuppressWarnings("WeakerAccess") @UiField TextBox nameText;

    @UiTemplate("com.warehouse.client.page.GroupPage.ui.xml")
    interface GroupUIBinder extends UiBinder<Widget, GroupDialog> {}
    private static final GroupUIBinder binder = GWT.create(GroupUIBinder.class);
    private Mode mode;
    private Group parentGroup, editableGroup= new Group();

    GroupDialog(){
        initWidget(binder.createAndBindUi(this));
        addValidators();
    }

    GroupDialog createAt(Group parent){
        parentGroup = parent;
        mode = Mode.INSERT;
        return this;
    }

    GroupDialog update(Group group) {
        editableGroup = group;
        mode = Mode.EDIT;
        nameText.setText(editableGroup.name);
        return this;
    }

    private void addValidators() {

        nameText.addValidator(new RequiredValidator());
        nameText.addValidator(new SizeValidator(2, 255));
    }


    @Override
    public void onPositive(Modal dialog, RequestCallBack callBack) {

        if(! form.validate()) return;
        editableGroup.name = nameText.getText();

        if(mode == Mode.INSERT) {
            editableGroup.groupId = parentGroup.getId();
            Server.setCallback(callBack).insert(new Request(editableGroup));
        }
        else if(mode == Mode.EDIT) {
            Server.setCallback(callBack).update(new Request(editableGroup));
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

    }

    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }
}
