package com.warehouse.client.utils;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.listener.MenuListener;
import com.warehouse.shared.dto.MenuItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public class MenuTreeModel implements TreeViewModel
{
    private MenuListener listener;
    private List<MenuItemDTO> navItems = new ArrayList<>();
    private SingleSelectionModel<MenuItemDTO> selectionModel = new SingleSelectionModel<>();
    private Cell<MenuItemDTO> cell = new AbstractCell<MenuItemDTO>("click") {
        @Override
        public void render(Context context, MenuItemDTO navItem, SafeHtmlBuilder safeHtmlBuilder)
        {
            if(navItem != null) safeHtmlBuilder.appendEscaped(navItem.getName());
        }

        @Override
        public void onBrowserEvent(Context context, Element parent, MenuItemDTO value, NativeEvent event, ValueUpdater<MenuItemDTO> valueUpdater)
        {
            if("click".equals(event.getType()))
            {
                listener.onNavigate(value);
            }
            super.onBrowserEvent(context, parent, value, event, valueUpdater);
        }
    };

    public MenuTreeModel(MenuListener listener, List<MenuItemDTO> items) {
        this.listener = listener;
        navItems = items;
    }

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value)
    {
        if(value == null)
        {
            // Level 0
            ListDataProvider<MenuItemDTO> dataProvider = new ListDataProvider<>(navItems);
            return new DefaultNodeInfo<>(dataProvider, cell);
        }
        else if(value instanceof MenuItemDTO)
        {
           MenuItemDTO navItem = (MenuItemDTO) value;

            ListDataProvider<MenuItemDTO> dataProvider = new ListDataProvider<>(navItem.getChildren());
            return new DefaultNodeInfo<>(dataProvider, cell, selectionModel, null);
        }
        return null;
    }

    @Override
    public boolean isLeaf(Object o)
    {
        if(! (o instanceof MenuItemDTO)) return false;

        MenuItemDTO navItem = (MenuItemDTO) o;
        return (navItem.getChildren() == null) || (navItem.getChildren().size() == 0);
    }
}
