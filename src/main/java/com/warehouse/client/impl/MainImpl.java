package com.warehouse.client.impl;

import com.warehouse.client.interf.Main;
import com.warehouse.client.present.MainPresent;
import com.warehouse.shared.entity.UserDetail;

/**
 * Created by Дима on 03.05.2017.
 *
 */

public class MainImpl implements Main
{
    @Override
    public void mainView(UserDetail user)
    {
        //ToDo Здесь мы определяем правила отображения и передаем их в форму.
        new MainPresent("");
    }
}
