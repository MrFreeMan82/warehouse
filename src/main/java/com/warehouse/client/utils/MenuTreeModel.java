package com.warehouse.client.utils;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.shared.dto.DTO;


/**
 * Created by Дима on 05.05.2017.
 *
 */

public class MenuTreeModel<Item extends DTO> implements TreeViewModel
{
    private CellInfo<Item> info;
    private SingleSelectionModel<Item> selectionModel = new SingleSelectionModel<>();
    private Cell<Item> cell = new AbstractCell<Item>() {

        @Override
        public void render(Context context, Item item, SafeHtmlBuilder safeHtmlBuilder)
        {
            if(item != null) safeHtmlBuilder.appendEscaped(info.cellText(item));
        }
    };

    public MenuTreeModel(CellInfo<Item> info) {

        this.info = info;
        selectionModel.addSelectionChangeHandler(selectionChangeEvent -> info.onClick(selectionModel.getSelectedObject()));
    }

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value)
    {
        if(value == null) {

            ListDataProvider<Item> dataProvider = new ListDataProvider<>(info.getChildren(null));
            return new DefaultNodeInfo<>(dataProvider, cell);
        }
        else {
            ListDataProvider<Item> dataProvider = new ListDataProvider<>(info.getChildren((Item) value));
            return new DefaultNodeInfo<>(dataProvider, cell, selectionModel, null);
        }
    }

    @Override
    public boolean isLeaf(Object o)
    {
        return o != null && info.isLeaf((Item) o);
    }
}
