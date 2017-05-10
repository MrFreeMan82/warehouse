package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.action.MainPresentAction;
import com.warehouse.shared.transition.VoidNoArg;
import com.warehouse.shared.transition.Transition;
import com.warehouse.shared.entity.MenuItem;
import com.warehouse.shared.entity.UserType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class MainPresent extends Present implements MainPresentAction
{
    @UiTemplate("com.warehouse.client.view.MainView.ui.xml")
    interface MainUIBinder extends UiBinder<Widget, MainPresent> {}
    private static final MainUIBinder binder = GWT.create(MainUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField DockLayoutPanel mainLayout;
    @SuppressWarnings("WeakerAccess") @UiField ScrollPanel menuPanel;

    private UserType userType;
    private Present center;
    private List<Transition<Long, VoidNoArg>> transitions;
    private MenuPresent menu;

    private void dockPresent(Present present)
    {
        if(center != null) mainLayout.remove(center);
        center = present;
        mainLayout.add(center);
    }

    public MainPresent()
    {
        initWidget(binder.createAndBindUi(this));
        transitions = new ArrayList<>();
        transitions.add(new Transition<>(MenuItem.USER_LIST, this::dockUserListPresent));
        menu = new MenuPresent(transitions);
    }

    @Override
    public void dockUserListPresent() {
        dockPresent(new UserListPresent());
    }

    @Override
    public void show(UserType userType) {
        this.userType = userType;
        menuPanel.add(menu.getCellTree());
        RootLayoutPanel.get().add(this);
    }
}
