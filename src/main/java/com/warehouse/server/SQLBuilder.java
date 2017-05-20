package com.warehouse.server;

import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.Type;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.UserDetail;
import com.warehouse.shared.function.FunctionOne;

import java.util.HashMap;

/**
 * Created by Дима on 18.05.2017.
 *
 */

class SQLBuilder
{
    private static final SQLBuilder instance = new SQLBuilder();
    private final HashMap<Enum, FunctionOne<String, DTO>> sqlMap = new HashMap<>();
    private Enum type;

    private SQLBuilder(){
        sqlMap.put(Type.USER_BY_PASSWORD, dto-> String.format(((Type)type).getPattern(), ((UserDetail)dto).getPassword()));
        sqlMap.put(Type.USER_LIST, dto-> ((Type)type).getPattern());
        sqlMap.put(Type.MAIN_MENU, dto-> ((Type)type).getPattern());
    }

    static String buildFrom(Request request){

        instance.type = request.getType();
        return instance.sqlMap.get(instance.type).go(request.getExample());
    }
}
