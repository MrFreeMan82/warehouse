package com.warehouse.client.action;

import com.warehouse.client.present.Present;

/**
 * Created by Дима on 19.04.2017.
 *
 */

public interface External
{
    void request(Present sender, String url, String data);
}
