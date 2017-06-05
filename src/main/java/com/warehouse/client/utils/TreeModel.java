package com.warehouse.client.utils;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.*;
import com.warehouse.shared.dto.DTO;

import java.util.List;


/**
 * Created by Дима on 05.05.2017.
 *
 */

public class TreeModel<Item extends DTO> implements TreeViewModel
{
    private CellInfo<Item> info;
    private ListDataProvider<Item> dataProvider;
    private SingleSelectionModel<Item> selectionModel = new SingleSelectionModel<>();
    private Cell<Item> cell = new AbstractCell<Item>() {

        @Override
        public void render(Context context, Item item, SafeHtmlBuilder safeHtmlBuilder)
        {
            if(item != null) safeHtmlBuilder.appendEscaped(info.cellText(item));
        }
    };

    public TreeModel(CellInfo<Item> info) {

        this.info = info;
        selectionModel.addSelectionChangeHandler(selectionChangeEvent -> info.onClick(selectionModel.getSelectedObject()));
    }

    public void refresh(Item newItem){
        if(newItem != null) dataProvider.getList().add(newItem);
        dataProvider.refresh();
    }

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value)
    {
        dataProvider = value == null ?
                 new ListDataProvider<>(info.getChildren(null)):
                    new ListDataProvider<>(info.getChildren((Item) value));

        return new DefaultNodeInfo<>(dataProvider, cell, selectionModel, null);
    }

    @Override
    public boolean isLeaf(Object o)
    {
        return o != null && info.isLeaf((Item) o);
    }
}
