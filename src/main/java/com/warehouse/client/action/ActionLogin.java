package com.warehouse.client.action;

import com.warehouse.client.event.AppEvent;
import com.warehouse.client.event.EventAction;


/**
 * Created by Дима on 17.04.2017.
 *
 */

public enum ActionLogin implements EventAction
{
    LOGIN {
        @Override
        public void action(AppEvent event) {

        }

    }, EXTERNAL_RESPONSE {
        @Override
        public void action(AppEvent event)
        {

        }
    }
}
