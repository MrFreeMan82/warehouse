package com.warehouse.client.present;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.warehouse.client.utils.*;
import com.warehouse.shared.Request;
import com.warehouse.shared.Type;
import com.warehouse.shared.dto.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Дима on 21.04.2017.
 *
 */

public class MainPresent extends Present implements Dock, CellInfo<MenuItem> {

    @UiTemplate("com.warehouse.client.page.MainPage.ui.xml")
    interface MainUIBinder extends UiBinder<Widget, MainPresent> {}
    private static final MainUIBinder binder = GWT.create(MainUIBinder.class);

    @SuppressWarnings("WeakerAccess") @UiField DockLayoutPanel mainLayout;
    @SuppressWarnings("WeakerAccess") @UiField ScrollPanel menuPanel;

    private UserType userType;
    private Present center;
    private List<MenuItem> menuItems;

    private void dockPresentInternal(Present present)
    {
        if(center != null) mainLayout.remove(center);
        center = present;
        mainLayout.add(center);
    }

    public MainPresent()
    {
        initWidget(binder.createAndBindUi(this));
        //ToDo Сделать загрузку списка пользователей
        Server.setCallback(this::onReceiveMenuItems).findList(new Request(Type.MAIN_MENU, new MenuItem()));
    }

    private void onReceiveMenuItems(DTO listDTO){

        List<? extends DTO> dtoList = ((ListDTO) listDTO).getList();
        menuItems = (List<MenuItem>) dtoList;
        MenuTreeModel treeModel = new MenuTreeModel<>(this, menuItems);

        CellTree cellTree = new CellTree(treeModel, null);
        menuPanel.add(cellTree);
    }


    @Override
    public String cellText(MenuItem cell) {return cell.getName(); }

    @Override
    public List<MenuItem> getChildren(MenuItem parent) {

        long id = parent.getId();
        return  menuItems.stream().filter(
                    menuItem -> id == menuItem.getParent().getId()).collect(Collectors.toList()
        );
    }

    @Override
    public boolean isLeaf(MenuItem cell) {return cell.isLeaf();}

    @Override
    public void onClick(MenuItem menuItem) {

        Window.alert(menuItem.getName());
    }


    @Override
    public void show() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(this);
    }

    @Override
    public void dockPresent(Present present) {
        dockPresentInternal(present);
    }
}
