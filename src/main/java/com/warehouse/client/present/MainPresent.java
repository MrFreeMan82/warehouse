package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.listener.MenuListener;
import com.warehouse.client.utils.Dock;
import com.warehouse.client.utils.Dockable;
import com.warehouse.shared.action.MenuAction;
import com.warehouse.shared.function.VoidNoArg;
import com.warehouse.shared.dto.MenuItemDTO;
import com.warehouse.shared.dto.UserTypeDTO;

import java.util.HashMap;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class MainPresent extends Present implements Dock, MenuListener
{
    @UiTemplate("com.warehouse.client.page.MainPage.ui.xml")
    interface MainUIBinder extends UiBinder<Widget, MainPresent> {}
    private static final MainUIBinder binder = GWT.create(MainUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField DockLayoutPanel mainLayout;
    @SuppressWarnings("WeakerAccess") @UiField ScrollPanel menuPanel;

    private UserTypeDTO userType;
    private Present center;
    private Present menu;

    private void dockPresentInternal(Present present)
    {
        if(center != null) mainLayout.remove(center);
        center = present;
        mainLayout.add(center);
    }

    public MainPresent()
    {
        initWidget(binder.createAndBindUi(this));
        HashMap<Long, VoidNoArg> transition = new HashMap<>();
      //  transition.put(MenuItemDTO.USER_LIST, () -> this.dockPresent(new UserListPresent()));
        //ToDo Сделать загрузку списка пользователей
        menu = new MenuPresent();
        MenuAction action = (MenuAction) menu;
        action.addMenuListener(this);
        action.requestMenuList();
    }

    @Override
    public void onNavigate(MenuItemDTO navItem) {

    }

    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        Dockable tree = (Dockable) menu;
        menuPanel.add((Widget) tree.dockable());
        RootLayoutPanel.get().add(this);
    }

    @Override
    public void dockPresent(Present present) {
        dockPresentInternal(present);
    }
}
