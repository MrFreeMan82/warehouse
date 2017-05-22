package com.warehouse.shared.source;

import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.request.Request;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface DataSource
{
    DTO loginByKey(String key);
    DTO loginByPassword(String password) throws Exception;
    void insert(Request request);
    void update(Request request);
    void delete(Request request);
    DTO find(Request request) throws Exception;
    DTO findList(Request request) throws Exception;
}
