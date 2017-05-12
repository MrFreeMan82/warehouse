package com.warehouse.server;

import com.warehouse.shared.dto.DTO;

import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface Database
{
    List<? extends DTO> select(String queryName, DTO example) throws Exception;
}
