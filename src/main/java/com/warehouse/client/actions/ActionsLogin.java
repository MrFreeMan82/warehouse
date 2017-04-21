package com.warehouse.client.actions;

import com.warehouse.client.events.AppEvent;
import com.warehouse.client.events.EventAction;


/**
 * Created by Дима on 17.04.2017.
 *
 */

public enum ActionsLogin implements EventAction
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
