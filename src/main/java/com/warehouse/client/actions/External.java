package com.warehouse.client.actions;

import com.warehouse.client.pages.Page;

/**
 * Created by Дима on 19.04.2017.
 *
 */

public interface External
{
    void request(Page sender, String url, String data);
}
