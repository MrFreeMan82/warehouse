package com.warehouse.server;

import com.warehouse.shared.dto.*;
import com.warehouse.shared.function.FunctionOne;
import com.warehouse.shared.Request;
import com.warehouse.shared.source.DataSource;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public class Memory implements DataSource
{
    private static Memory instance;
    private HashMap<String, FunctionOne<List<? extends DTO>, DTO>> prepareTable = new HashMap<>();

    private Memory() {

    }

    public static Memory getInstance()
    {
        if(instance == null) instance = new Memory();
        return instance;
    }

    @Override
    public DTO loginByKey(String key) {
        return null;
    }

    @Override
    public DTO loginByPassword(String password) throws Exception {
        return null;
    }

    @Override
    public void create(Request query) {

    }

    @Override
    public void edit(Request query) {

    }

    @Override
    public void delete(Request query) {

    }

    @Override
    public DTO find(Request request) throws Exception {
        return null;
    }

    @Override
    public DTO findList(Request request) throws Exception {
        return null;
    }

}
