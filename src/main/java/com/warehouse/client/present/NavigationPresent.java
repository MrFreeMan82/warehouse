package com.warehouse.client.present;

import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.view.client.TreeViewModel;
import com.warehouse.client.impl.NavTreeModel;
import com.warehouse.client.impl.NavigateImpl;
import com.warehouse.client.interf.Main;
import com.warehouse.client.interf.Navigate;

/**
 * Created by Дима on 05.05.2017.
 *
 */

class NavigationPresent extends Present
{
    private CellTree cellTree;

    NavigationPresent(Main main)
    {
        Navigate navigate = new NavigateImpl(main);
        TreeViewModel navigateTreeModel = new NavTreeModel(navigate);
        cellTree = new CellTree(navigateTreeModel, null);
        cellTree.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
    }

    CellTree getCellTree() {return cellTree; }
}
