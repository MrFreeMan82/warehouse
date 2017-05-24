package com.warehouse.server;

import com.google.gwt.thirdparty.guava.common.base.Throwables;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.warehouse.client.utils.Service;
import com.warehouse.shared.request.Type;
import com.warehouse.shared.dto.Empty;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.source.DataSource;
import com.warehouse.shared.dto.DTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;




/**
 * Created by Дима on 30.04.2017.
 *
 */

public class DAOService extends RemoteServiceServlet implements Service {
    public static final Logger logger = Logger.getLogger("DAOService");
    private static final DataSource dataSource = Hibernate.getInstance();// Memory.getInstance();
    private static final String INVALID_PARAM = "Invalid login parameters";

    private DTO onFail(Enum requestType, Exception e){
        String error = "Fatal " + e.toString() + " : \n" + Throwables.getStackTraceAsString(e);
        logger.severe(error);
        DTO dto = new Empty(error);
        dto.setRequest(requestType);
        return dto;
    }

    @Override
    public DTO login(String loginParameters) {
        logger.info("User try to login with " + loginParameters);

        DTO dto;
        List<String> params = new ArrayList<>(Arrays.asList(loginParameters.split("=")));
        if(params.size() < 2) return new Empty(INVALID_PARAM);
        try {
            switch (params.get(0))
            {
                case "key": dto = dataSource.loginByKey(params.get(1));
                            dto.setRequest(Type.LOGIN);
                            return dto;

                case "password": dto = dataSource.loginByPassword(params.get(1));
                                 dto.setRequest(Type.LOGIN);
                                 return dto;

                default: dto = new Empty(INVALID_PARAM);
                         dto.setRequest(Type.LOGIN);
                         return dto;
            }
        } catch (Exception e) {
            return onFail(Type.LOGIN, e);
        }
    }

    @Override
    public DTO select(Request request) {
        logger.info("Begin " + request.getType().name());

        try {
            DTO dto = dataSource.find(request);
            dto.setRequest(request.getType());
            return dto;
        } catch (Exception e) {
            return onFail(request.getType(), e);
        }
    }

    @Override
    public DTO selectList(Request request) {
        logger.info("Begin " + request.getType().name());

        try{
            DTO dto = dataSource.findList(request);
            dto.setRequest(request.getType());
            return dto;
        } catch (Exception e) {
            return onFail(request.getType(), e);
        }
    }

    @Override
    public DTO insert(Request request) {
        logger.info("Begin " + request.getType().name());
        try{
            dataSource.insert(request);
            DTO dto = new DTO();
            dto.setRequest(request.getType());
            return dto;
        } catch (Exception e) {
            return onFail(request.getType(), e);
        }
    }

    @Override
    public DTO update(Request request) {
        logger.info("Begin " + request.getType().name());
        try{
            dataSource.update(request);
            DTO dto = new DTO();
            dto.setRequest(request.getType());
            return dto;
        } catch (Exception e) {
            return onFail(request.getType(), e);
        }
    }

    @Override
    public DTO delete(Request request) {
        logger.info("Begin " + request.getType().name());
        try{
            dataSource.delete(request);
            DTO dto = new DTO();
            dto.setRequest(request.getType());
            return dto;
        } catch (Exception e) {
            return onFail(request.getType(), e);
        }
    }
}
