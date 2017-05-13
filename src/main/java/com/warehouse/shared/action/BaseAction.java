package com.warehouse.shared.action;

import com.warehouse.shared.dto.DTO;

import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface BaseAction<T extends DTO>
{
    void create();
    void edit(T t);
    void delete(T t);
    List<T> find(T example);
}
