package com.warehouse.server.dao;

import com.warehouse.shared.entity.Base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 08.05.2017.
 *
 */

public class NavItemDAO extends DAO {
    @Override
    public List<? extends Base> querySelect(String namedQuery, Base params)
    {
        return new ArrayList<>();
    }
}
