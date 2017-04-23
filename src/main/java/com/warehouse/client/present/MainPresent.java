package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.TreeViewModel;

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
    @UiField ScrollPanel tree;


    public MainPresent()
    {
        initWidget(binder.createAndBindUi(this));

        UserListPresent userList = new UserListPresent();
        mainLayout.add(userList);

        TreeViewModel model = new TreeSample.CustomTreeModel();
        CellTree cellTree = new CellTree(model, null);
        cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        tree.add(cellTree);

        RootLayoutPanel.get().add(this);
    }
}
