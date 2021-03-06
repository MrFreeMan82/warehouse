package com.warehouse.client;


import com.warehouse.client.present.ArtiquleListPresent;
import com.warehouse.client.present.LoginPresent;
import com.warehouse.client.present.MainPresent;
import com.warehouse.client.utils.Server;
import com.warehouse.shared.dto.*;
import com.warehouse.shared.dto.ServerException;


/**
 * Created by Дима on 30.04.2017.
 *
 */

public class Application
{
    private static final Application instance = new Application();

    private Application(){}

    public static Application getInstance(){return instance;}

    static void go()
    {
      //  new MainPresent().show();
        new ArtiquleListPresent().show();

        // ToDo read key from coockie
        //Server.setCallback(instance::onReceiveLoginStatus).loginByKey("");
    }

    public void onReceiveLoginStatus(DTO dto){

        if(dto instanceof UserSession) {
            UserSession session = (UserSession) dto;
            UserDetail user = session.getUser();
            Warehouse.info("Login OK, user " + user.getName() + '\n' + " session: " + session.getKey());

            Server.getInstance().setSessionKey(session.getKey());
            new MainPresent();
        }
        else if(dto instanceof ServerException) {
            Warehouse.info("{name}: {msg}", dto.getRequest().name(), ((ServerException) dto).getMsg());
            new LoginPresent().show();
        }
        else new LoginPresent().show();
    }
}
