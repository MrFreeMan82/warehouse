package com.warehouse.client.impl;

import com.warehouse.client.interf.Main;
import com.warehouse.client.present.MainPresent;
import com.warehouse.client.present.UserListPresent;


/**
 * Created by Дима on 03.05.2017.
 *
 */

public class MainImpl implements Main
{
    private MainPresent mainPresent;

    public MainImpl(MainPresent mainPresent) {this.mainPresent = mainPresent;}

    @Override
    public void showUserList() {
        mainPresent.centerView(new UserListPresent());
    }

}
