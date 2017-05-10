package com.warehouse.server;

import com.warehouse.shared.entity.Base;

import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface Database
{
    List<? extends Base> selectSessionByKey(Base example);
    List<? extends Base> selectAllMenuItems();
    List<? extends Base> selectAllUserType();
}
