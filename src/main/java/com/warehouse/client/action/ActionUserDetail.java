package com.warehouse.client.action;

import com.google.gwt.core.client.GWT;
import com.warehouse.client.Warehouse;
import com.warehouse.client.event.AppEvent;
import com.warehouse.client.event.EventAction;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 21.04.2017.
 *
 */

public enum ActionUserDetail implements EventAction
{
    SAVE {
        @Override
        public void action(AppEvent event)
        {
            String url =  GWT.getModuleBaseURL() +
                    Utils.Services.USER.name().toLowerCase() +
                    '/' + Utils.Services.Detail.CREATE.name().toLowerCase();

            String data = event.getJSONObject().toString();

            Warehouse.external.request(event.getPage(), url, data);
        }
    },
    CANCEL {
        @Override
        public void action(AppEvent event) {

        }
    },
    EXTERNAL_RESPONSE {
        @Override
        public void action(AppEvent event) {

        }
    }
}