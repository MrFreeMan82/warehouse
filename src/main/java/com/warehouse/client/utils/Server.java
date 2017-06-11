package com.warehouse.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.client.Warehouse;
import com.warehouse.shared.Utils;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.ServerException;
import com.warehouse.shared.dto.Hashed;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.request.SQL;
import com.warehouse.shared.source.DataSource;


/**
 * Created by Дима on 17.05.2017.
 *
 */

public class Server implements DataSource, AsyncCallback<DTO>
{
    private static final Server instance = new Server();
   // private final HashMap<String, DTO> queryCache = new HashMap<>();
    private final ServiceAsync async = GWT.create(Service.class);
    private String sessionKey;
    private RequestCallBack callback;

    private Server(){}

    public void setSessionKey(String sessionKey){this.sessionKey = sessionKey;}

    public static Server getInstance(){return instance;}
    public static Server setCallback(RequestCallBack callback) {instance.callback = callback; return instance;}

    private void foo(DTO dto){

        if(dto instanceof ServerException){
            Warehouse.severe(dto.getRequest().name() + ':' + ((ServerException) dto).getMsg());
            return;
        }
        Warehouse.info(dto.getRequest().name() + " success");
    }

    @Override
    public DTO loginByKey(String key) {
        async.login("key=" + (key.equals("") ? sessionKey: key), this);
        return null;
    }

    @Override
    public DTO loginByPassword(String password) {
        async.login("password=" + Utils.hashString(password), this);
        return null;
    }

    @Override
    public Long insert(Request request) {
        if(request.getType() == null) request.setType(SQL.INSERT);
        async.insert(request, this);
        return null;
    }

    @Override
    public void update(Request request) {
        if(request.getType() == null) request.setType(SQL.UPDATE);
        async.update(request, this);
    }

    @Override
    public void delete(Request request) {
        if(request.getType() == null) request.setType(SQL.DELETE);
        async.delete(request, this);
    }

    @Override
    public void procedure(Request request) {
        async.procedure(request, this);
    }

    @Override
    public DTO find(Request request) {
        async.select(request.setSessionKey(sessionKey), this);
        return null;
    }

    @Override
    public DTO findList(Request request) {
        async.selectList(request.setSessionKey(sessionKey), this);
        return null;
    }

    @Override
    public DTO refresh(Request request) {
        Warehouse.info("Refreshing " + request.getExample().getClass().getName() + ":" + request.getExample().getId());
        if(request.getType() == null) request.setType(SQL.REFRESH);
        async.refresh(request.setSessionKey(sessionKey), this);
        return null;
    }

    @Override
    public void onFailure(Throwable throwable) {
        Warehouse.severe(throwable.getMessage());
    }

    @Override
    public void onSuccess(DTO dto) {
        Warehouse.info("Request: " + dto.getRequest().name());

        if (dto instanceof ServerException) {
            Window.alert("Server error, see log for detail.");
            ServerException serverException = (ServerException) dto;
            throw new RuntimeException(serverException.getMsg());
        }

        if (dto instanceof Hashed) {
            Warehouse.info(Utils.format("Receive {size}", ((Hashed) dto).getList().size()));
        } else {
            Warehouse.info("Receive one " + dto.getClass().getName());
        }

        if(callback == null) foo(dto); else callback.receive(dto);
    }
}
