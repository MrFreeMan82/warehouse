package com.warehouse.server;

import com.warehouse.shared.dto.Artiqule;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.UserDetail;
import com.warehouse.shared.function.FunctionOne;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;

import java.util.HashMap;

/**
 * Created by Дима on 18.05.2017.
 *
 */

class SQLMapper
{
    private static final SQLMapper instance = new SQLMapper();
    private final HashMap<Enum, FunctionOne<String, DTO>> sqlMap = new HashMap<>();
    private final HashMap<Enum, FunctionOne<Object[], DTO>> parameterMap = new HashMap<>();
    private SQL type;

    private SQLMapper(){
        sqlMap.put(SQL.USER_BY_PASSWORD, dto-> String.format(type.getPattern(), ((UserDetail)dto).getPassword()));
        sqlMap.put(SQL.USER_LIST, dto-> type.getPattern());
        sqlMap.put(SQL.USER_TYPE_LIST, dto->type.getPattern());
        sqlMap.put(SQL.MAIN_MENU, dto-> type.getPattern());
        sqlMap.put(SQL.GROUPS_WITH_ARTIQULES, dto->type.getPattern());
        sqlMap.put(SQL.ARTIQULES_BY_GROUP, dto->String.format(type.getPattern(), ((Artiqule)dto).groupId));
        sqlMap.put(SQL.GROUPS, dto->type.getPattern());
        sqlMap.put(SQL.METRIC_LIST, dto->type.getPattern());
        sqlMap.put(SQL.PRICE_TYPE_LIST, dto->type.getPattern());

                // Procedure Parameters
        parameterMap.put(SQL.DELETE_GROUP, group->new Object[]{group.getId()});
    }

    static Object[] procedureParameters(Request request){
        instance.type = request.getType();
        return instance.parameterMap.get(instance.type).go(request.getExample());
    }

    static String buildFrom(Request request){

        instance.type = request.getType();
        return instance.sqlMap.get(instance.type).go(request.getExample());
    }
}
