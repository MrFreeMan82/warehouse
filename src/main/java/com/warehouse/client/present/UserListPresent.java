package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.DialogBuilder;
import com.warehouse.client.LogEvent;
import com.warehouse.client.Warehouse;
import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Modal;

/**
 * Created by Дима on 22.04.2017.
 *
 */

public class UserListPresent extends Present
{
    @UiTemplate("com.warehouse.client.view.UserListView.ui.xml")
    interface UserListUIBinder extends UiBinder<Widget, UserListPresent> {}
    private static final UserListUIBinder binder = GWT.create(UserListUIBinder.class);

    @UiField VerticalPanel listPanel;
    @UiField AnchorListItem newUser;

    private Modal userDetailDialog;

    public UserListPresent()
    {
        initWidget(binder.createAndBindUi(this));

        newUser.addClickHandler(clickEvent ->
        {
            Warehouse.logger.info(new LogEvent(this, newUser, newUser.getId()).toString());
            newUser.setActive(true);

            UserDetailPresent present = new UserDetailPresent();

            userDetailDialog = new DialogBuilder()
                    .setPresent(present)
                    .setTitle(Warehouse.i18n.userTitle())
                    .addPositiveButton(Warehouse.i18n.captionSave(), clickEvent1 -> {present.doSave(); userDetailDialog.hide();})
                    .addNegativeButton(Warehouse.i18n.captionCancel(), clickEvent1 -> userDetailDialog.hide())
                    .build();

            userDetailDialog.show();

            newUser.setActive(false);
        });

       // listPanel.add(null);
    }
}
