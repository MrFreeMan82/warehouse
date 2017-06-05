package com.warehouse.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by Дима on 01.06.2017.
 *
 */

public interface Resources extends ClientBundle {
    public static final Resources INSTANCE =  GWT.create(Resources.class);
    @Source("com.warehouse.present.css.my.css")
    CssResource css();
}
