package com.warehouse.shared.dto;

import com.warehouse.shared.request.SQL;

/**
 * Created by Дима on 11.06.2017.
 *
 */

public class Empty extends DTO {

    public Empty(){}
    public Empty(SQL type){setRequest(type);}
}
