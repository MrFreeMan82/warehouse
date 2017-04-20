package com.warehouse.client.actions;

import com.google.gwt.user.client.Window;
import com.warehouse.client.AppController;
import com.warehouse.client.JSON;
import com.warehouse.client.Warehouse;
import com.warehouse.client.events.AppEvent;
import com.warehouse.client.events.ErrorEvent;
import com.warehouse.client.events.EventAction;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 17.04.2017.
 *
 */

public enum ActionsLogin implements EventAction
{
    LOGIN {
        @Override
        public void action(AppEvent event) {
            Warehouse.getExternal().login(event);
        }

    }, EXTERNAL_RESPONSE {
        @Override
        public void action(AppEvent event)
        {
            String response = event.getParam(AppController.MapKeys.APP_EXTERNAL);
            try{
                JSON json = new JSON(response);
                String result  = json.getString(Utils.JSON_Keys.RESULT.name());
                if(result.contains("200"))
                {
                    System.out.println(json.getString(Utils.JSON_Keys.LOGIN_KEY.name()));
                    Window.alert(json.getString(Utils.JSON_Keys.LOGIN_KEY.name()));
                    event.getPage().close();
                } else {
                    System.out.println(result);
                    Window.alert(result);
                }
                event.setHandled(true);
            } catch (Exception e) {
                event.setHandled(false);
                Warehouse.getEventBus().fireEvent(new ErrorEvent(e, response));
            }
        }
    }
}
