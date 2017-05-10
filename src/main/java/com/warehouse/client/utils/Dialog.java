package com.warehouse.client.utils;

import org.gwtbootstrap3.client.ui.Modal;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface Dialog
{
    void onPositive(Modal dialog);
    void onNeutral(Modal dialog);
    void onNegative(Modal dialog);
}
