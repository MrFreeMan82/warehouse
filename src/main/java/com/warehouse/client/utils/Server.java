package com.warehouse.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.warehouse.client.Warehouse;
import com.warehouse.shared.Utils;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.Empty;
import com.warehouse.shared.request.Request;
import com.warehouse.shared.source.DataSource;

import java.util.HashMap;

/**
 * Created by Дима on 17.05.2017.
 *
 */

public class Server implements DataSource, AsyncCallback<DTO>
{
    private static final Server instance = new Server();
    private final HashMap<String, DTO> queryCache = new HashMap<>();
    private final ServiceAsync async = GWT.create(Service.class);
    private String sessionKey;
    private ServerCallBack callback;

    private Server(){}

    public void setSessionKey(String sessionKey){this.sessionKey = sessionKey;}

    public static Server getInstance(){return instance;}
    public static Server setCallback(ServerCallBack callback) {instance.callback = callback; return instance;}

    private void requestStatus(DTO dto){

        if(dto instanceof Empty){
            Warehouse.severe(dto.getRequest().name() + ':' + ((Empty) dto).getMsg());
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
    public void insert(Request request) {
        async.insert(request, this);
    }

    @Override
    public void update(Request request) {
        async.update(request, this);
    }

    @Override
    public void delete(Request request) {
        async.delete(request, this);
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
    public void onFailure(Throwable throwable) {
        Warehouse.severe(throwable.getMessage());
    }

    @Override
    public void onSuccess(DTO dto) {

        if(callback == null) requestStatus(dto); else callback.receive(dto);
    }
}
