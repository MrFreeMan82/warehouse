package com.warehouse.client.utils;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.HashedDTO;

import java.util.List;

/**
 * Created by Дима on 01.06.2017.
 *
 */

public class DynamicTreeModel<Item extends DTO> {

    private Tree tree = new Tree();
    private CellInfo<Item> info;

    private TreeItem create(TreeItem parent, Item item){
        TreeItem item1 = parent.addTextItem(info.cellText(item));
        item1.setUserObject(item);
        return item1;
    }

    private void AddItem(TreeItem parent, List<Item> children) {

        children.forEach(child->AddItem(create(parent, child), info.getChildren(child)));
    }

    public void add(Item item){
        tree.getSelectedItem().addTextItem(info.cellText(item)).setUserObject(item);
    }

    public void update(Item item){
        tree.getSelectedItem().setText(info.cellText(item));
        tree.getSelectedItem().setUserObject(item);
    }

    public DynamicTreeModel(Tree tree, CellInfo<Item> info, HashedDTO data){
        tree.addSelectionHandler(selectionEvent ->
                info.onClick((Item) selectionEvent.getSelectedItem().getUserObject()));

        tree.setAnimationEnabled(true);
        this.tree = tree;
        this.info = info;
        Item item = data.get(1L);
        TreeItem root = tree.addTextItem(info.cellText(item));
        root.setUserObject(item);

        AddItem(root, info.getChildren(item));
    }

    public Tree getTree(){return tree;}
}
