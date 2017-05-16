package com.warehouse.shared.action;

import com.warehouse.shared.dto.DTO;

import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface BaseAction<Find extends DTO, Example extends DTO, CRUD extends DTO>
{
    void create(CRUD crud);
    void edit(CRUD crud);
    void delete(CRUD crud);
    List<Find> find(String queryName, Example example);
    Find findOne(String queryName, Example example);
}
