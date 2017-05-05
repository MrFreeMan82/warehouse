package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.impl.MainImpl;
import com.warehouse.shared.entity.UserDetail;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class MainPresent extends Present
{
    @UiTemplate("com.warehouse.client.view.MainView.ui.xml")
    interface MainUIBinder extends UiBinder<Widget, MainPresent> {}
    private static final MainUIBinder binder = GWT.create(MainUIBinder.class);

    @UiField DockLayoutPanel mainLayout;
    @UiField ScrollPanel navigationPanel;

    private UserDetail user;
    private Present center;

    public void centerView(Present present)
    {
        if(center != null) mainLayout.remove(center);
        center = present;
        mainLayout.add(center);
    }


    public MainPresent(UserDetail forUser)
    {
        user = forUser;
        initWidget(binder.createAndBindUi(this));
        navigationPanel.add(new NavigationPresent(new MainImpl(this)).getCellTree());
        RootLayoutPanel.get().add(this);
    }
}
