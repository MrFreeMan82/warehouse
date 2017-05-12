package com.warehouse.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.warehouse.client.utils.Service;
import com.warehouse.shared.dto.DTO;

import java.util.List;
import java.util.logging.Logger;


/**
 * Created by Дима on 30.04.2017.
 *
 */

public class DAOService extends RemoteServiceServlet implements Service
{
    public static Logger logger = Logger.getLogger("DAOService");
    private static Database database = Hibernate.getInstance(); // Memory.getInstance();

    @Override
    public List<? extends DTO> querySelect(String sessionKey, String queryName, DTO example)
    {
        // ToDo добавить проверку sessionKey
        logger.info("Begin select " + queryName);

        try {
            return database.select(queryName, example);
        } catch (Exception e) {
            logger.severe("Fail:" + e.toString() + " : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public int queryInsert(String sessionKey, String namedQuery, DTO example) {
        return 0;
    }

}
