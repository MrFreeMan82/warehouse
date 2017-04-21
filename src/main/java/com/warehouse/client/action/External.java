package com.warehouse.client.action;

import com.warehouse.client.page.Page;

/**
 * Created by Дима on 19.04.2017.
 *
 */

public interface External
{
    void request(Page sender, String url, String data);
}
