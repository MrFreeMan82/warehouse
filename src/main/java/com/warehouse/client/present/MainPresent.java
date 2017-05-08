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
import com.warehouse.client.utils.Transition;
import com.warehouse.shared.entity.NavItem;
import com.warehouse.shared.entity.UserType;
import org.gwtbootstrap3.client.ui.Pre;

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

    @UiField DockLayoutPanel mainLayout;
    @UiField ScrollPanel navigationPanel;

    private UserType userType;
    private Present center;
    private List<Transition> transitions;

    private void centerView(Present present)
    {
        if(center != null) mainLayout.remove(center);
        center = present;
        mainLayout.add(center);
    }

    public MainPresent()
    {
        initWidget(binder.createAndBindUi(this));
        transitions = new ArrayList<>();
        transitions.add(new Transition(NavItem.USER_LIST, this::dockUserListPresent));
    }

    @Override
    public void dockUserListPresent() {
        centerView(new UserListPresent());
    }

    @Override
    public void show(UserType userType) {
        this.userType = userType;
        new NavigationPresent(navigationPanel, transitions);
        RootLayoutPanel.get().add(this);
    }
}
