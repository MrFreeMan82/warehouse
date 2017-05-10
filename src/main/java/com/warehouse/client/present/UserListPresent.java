package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.DialogBuilder;
import org.gwtbootstrap3.client.ui.AnchorListItem;

/**
 * Created by Дима on 22.04.2017.
 *
 */

class UserListPresent extends Present
{
    @UiTemplate("com.warehouse.client.view.UserListView.ui.xml")
    interface UserListUIBinder extends UiBinder<Widget, UserListPresent> {}
    private static final UserListUIBinder binder = GWT.create(UserListUIBinder.class);

    @UiField VerticalPanel listPanel;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newUser;

    UserListPresent()
    {
        initWidget(binder.createAndBindUi(this));

        newUser.addClickHandler(clickEvent ->

            new DialogBuilder<UserDetailPresent>()
                    .setPresent(new UserDetailPresent())
                    .setTitle(Warehouse.i18n.userTitle())
                    .addPositiveButton(Warehouse.i18n.captionSave())
                    .addNegativeButton(Warehouse.i18n.captionCancel())
                    .build()
                    .show()
        );
        // ToDo добавить список пользователей
       // listPanel.add(null);
    }
}
