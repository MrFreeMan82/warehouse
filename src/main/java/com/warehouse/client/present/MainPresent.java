package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.warehouse.client.Warehouse;
import com.warehouse.client.utils.*;
import com.warehouse.shared.dto.MenuItem;
import com.warehouse.shared.function.FunctionNoArg;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.Type;
import com.warehouse.shared.dto.*;

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
    private ListDTO menuItems;

    public MainPresent()
    {
        initWidget(binder.createAndBindUi(this));
        dockables.put(UserListPresent.TAG, UserListPresent::new);
        Server.setCallback(this::onReceiveMenuItems).findList(new Request(Type.MAIN_MENU, new MenuItem()));
    }

    private void onReceiveMenuItems(DTO listDTO){

        if(listDTO instanceof ListDTO) {

            menuItems = (ListDTO) listDTO;
            Warehouse.info("Receive Menu Items " + menuItems.getList().size());

            MenuTreeModel treeModel = new MenuTreeModel<>(this);

            CellTree cellTree = new CellTree(treeModel, null);
            cellTree.setAnimationEnabled(true);
            cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
            menuPanel.add(cellTree);
        }
        else if(listDTO instanceof Empty) Warehouse.severe(((Empty) listDTO).getMsg());
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
