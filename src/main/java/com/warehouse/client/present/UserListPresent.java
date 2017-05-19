package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.utils.Dockable;
import org.gwtbootstrap3.client.ui.AnchorListItem;

/**
 * Created by Дима on 22.04.2017.
 *
 */

class UserListPresent extends Present implements Dockable<Present>
{
    @Override
    public Present dockable() {
        return null;
    }

    @Override
    public void show() {

    }

    @UiTemplate("com.warehouse.client.page.UserListPage.ui.xml")
    interface UserListUIBinder extends UiBinder<Widget, UserListPresent> {}
    private static final UserListUIBinder binder = GWT.create(UserListUIBinder.class);

    @UiField VerticalPanel listPanel;
    @SuppressWarnings("WeakerAccess") @UiField AnchorListItem newUser;

    UserListPresent()
    {
        initWidget(binder.createAndBindUi(this));

      //  newUser.addClickHandler();
        // ToDo добавить таблицу пользователей
        // ToDo сохранять пользователей
       // listPanel.add(null);
    }

  /*  @Override
    public void create() {
        new DialogBuilder<UserDetailPresent>()
                .setPresent(new UserDetailPresent())
                .setTitle(Warehouse.i18n.userTitle())
                .addPositiveButton(Warehouse.i18n.captionSave())
                .addNegativeButton(Warehouse.i18n.captionCancel())
                .build()
                .show();
    }

*/
}
