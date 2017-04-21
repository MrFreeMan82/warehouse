package com.warehouse.client.page;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by Дима on 16.04.2017.
 *
 */

public abstract class Page extends Composite
{
    public void close()
    {
        RootPanel.get().remove(this);
    }

}
