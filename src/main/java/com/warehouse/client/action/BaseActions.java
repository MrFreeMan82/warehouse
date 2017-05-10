package com.warehouse.client.action;

import com.warehouse.shared.entity.Base;

import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface BaseActions<T extends Base>
{
    void create();
    void edit(T t);
    void delete(T t);
    List<T> find(T example);
}
