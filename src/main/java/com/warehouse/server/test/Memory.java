package com.warehouse.server.test;

import com.warehouse.server.Database;
import com.warehouse.server.dao.LoginDAO;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.function.FunctionOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public class Memory implements Database
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
    public List<? extends DTO> selectList(String queryName, DTO example) throws Exception {
        return null;
    }

    @Override
    public DTO selectOne(String queryName, DTO example) throws Exception {
        return null;
    }
}
