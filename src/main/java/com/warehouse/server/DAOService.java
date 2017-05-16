package com.warehouse.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.warehouse.client.utils.Service;
import com.warehouse.server.test.Memory;
import com.warehouse.shared.dto.DTO;

import java.util.List;
import java.util.logging.Logger;


/**
 * Created by Дима on 30.04.2017.
 *
 */

public class DAOService extends RemoteServiceServlet implements Service {
    public static Logger logger = Logger.getLogger("DAOService");
    private static Database database = Hibernate.getInstance();// Memory.getInstance();

    @Override
    public DTO selectOne(String queryName, DTO example) {
        logger.info("Begin select " + queryName);

        try {
            return database.selectOne(queryName, example);
        } catch (Exception e) {
            logger.severe("Fail:" + e.toString() + " : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<? extends DTO> selectList(String queryName, DTO example) {
        logger.info("Begin select " + queryName);

        try {
            return database.selectList(queryName, example);
        } catch (Exception e) {
            logger.severe("Fail:" + e.toString() + " : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
