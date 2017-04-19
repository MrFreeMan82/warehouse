package com.warehouse.client.events;

import com.google.gwt.user.client.Window;
import com.warehouse.client.JSON;
import com.warehouse.client.Warehouse;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 17.04.2017.
 *
 */

public enum KindOfLogin implements EventAction
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
            String response = event.getParams()[0];
            try{
                JSON json = new JSON(response);
                String result  = json.getString(Utils.RESULT);
                if(result.contains("200"))
                {
                    Window.alert(json.getString(Utils.KEY));
                    event.getSender().close();
                    event.setHandled(true);
                } else {
                    Window.alert(result);
                }

            } catch (Exception e) {
                Warehouse.getEventBus().fireEvent(new ErrorEvent(e, response));
            }
        }
    }, TEST{
        @Override
        public void action(AppEvent event) {event.setHandled(true);}
    }
}
