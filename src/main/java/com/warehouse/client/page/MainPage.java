package com.warehouse.client.page;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public class MainPage extends Page
{
    @UiTemplate("com.warehouse.client.view.MainView.ui.xml")
    interface MainUIBinder extends UiBinder<Widget, MainPage> {}
    private static final MainUIBinder binder = GWT.create(MainUIBinder.class);

    public MainPage()
    {
        initWidget(binder.createAndBindUi(this));
        RootLayoutPanel.get().add(this);
    }
}
