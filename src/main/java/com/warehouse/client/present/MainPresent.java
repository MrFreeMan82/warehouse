package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.*;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.dto.ServerException;
import com.warehouse.shared.function.FunctionNoArg;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Дима on 21.04.2017.
 *
 */

public class MainPresent extends Present implements Dock<Present>, CellInfo<MenuItem> {

    @UiTemplate("com.warehouse.client.page.MainPage.ui.xml")
    interface MainUIBinder extends UiBinder<Widget, MainPresent> {}
    private static final MainUIBinder binder = GWT.create(MainUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField DockLayoutPanel mainLayout;
    @SuppressWarnings("WeakerAccess") @UiField ScrollPanel menuPanel;

    private HashMap<String, FunctionNoArg<Dockable<Present>>> dockables = new HashMap<>();
    private UserType userType;
    private Dockable<Present> center;
    private Hashed menuItems;

    public MainPresent()
    {
        initWidget(binder.createAndBindUi(this));
        dockables.put(UserListPresent.TAG, UserListPresent::new);
        Request request = new Request(SQL.MAIN_MENU, new MenuItem());
        Server.setCallback(this::onReceiveMenuItems).findList(request);
    }

    private void onReceiveMenuItems(DTO listDTO){

        if(listDTO instanceof Hashed) {

            menuItems = (Hashed) listDTO;
            Warehouse.info("Receive Menu Items " + menuItems.getList().size());

            TreeModel treeModel = new TreeModel<>(this);

            CellTree cellTree = new CellTree(treeModel, null);
            cellTree.setAnimationEnabled(true);
            cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            menuPanel.add(cellTree);
        }
        else if(listDTO instanceof ServerException) Warehouse.severe(((ServerException) listDTO).getMsg());
    }


    @Override
    public String cellText(MenuItem cell) {
        return cell.getName();
    }

    @Override
    public List<MenuItem> getChildren(MenuItem parent) {

        long id = parent == null ? 0: parent.getId();
        List<MenuItem> items = (List<MenuItem>) menuItems.getList();
        return  items.stream()
                .filter(menuItem -> id == menuItem.getParent().getId())
                .collect(Collectors.toList()
        );
    }

    @Override
    public boolean isLeaf(MenuItem cell) {
        return cell.isLeaf();
    }

    @Override
    public void onClick(MenuItem menuItem) {

        if(dockables.containsKey(menuItem.getPresent())) {
            dockPresent(dockables.get(menuItem.getPresent()).go());
        }
    }


    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }

    @Override
    public void dockPresent(Dockable<Present> present) {

        if(center != null) mainLayout.remove((Present)center);
        center = present;
        mainLayout.add((Present) center);
    }
}
