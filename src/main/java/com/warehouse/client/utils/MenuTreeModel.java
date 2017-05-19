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
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.MenuItem;
import org.apache.tapestry.ITemplateComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public class MenuTreeModel<Item extends DTO> implements TreeViewModel
{
    private CellInfo<Item> info;
    private List<Item> menuItems = new ArrayList<>();
    private SingleSelectionModel<Item> selectionModel = new SingleSelectionModel<>();
    private Cell<Item> cell = new AbstractCell<Item>() {

        @Override
        public void render(Context context, Item item, SafeHtmlBuilder safeHtmlBuilder)
        {
            if(item != null) safeHtmlBuilder.appendEscaped(info.cellText(item));
        }
    };

    public MenuTreeModel(CellInfo<Item> info, List<Item> items) {
        this.info = info;
        menuItems = items;
        selectionModel.addSelectionChangeHandler(selectionChangeEvent -> info.onClick(selectionModel.getSelectedObject()));
    }

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value)
    {
        if(value == null)
        {
            // Level 0
            ListDataProvider<Item> dataProvider = new ListDataProvider<>(menuItems);
            return new DefaultNodeInfo<>(dataProvider, cell);
        }
        else {
            List<Item> children = info.getChildren((Item) value);

            ListDataProvider<Item> dataProvider = new ListDataProvider<>(children);
            return new DefaultNodeInfo<>(dataProvider, cell, selectionModel, null);
        }
    }

    @Override
    public boolean isLeaf(Object o)
    {
        return info.isLeaf((Item) o);
    }
}
