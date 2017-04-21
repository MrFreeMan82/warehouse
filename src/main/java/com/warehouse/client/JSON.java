package com.warehouse.client;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

/**
 * Created by Дима on 17.04.2017.
 *
 */

public class JSON
{
    private JSONObject object;

    public JSON(String json)
    {
        JSONValue value = JSONParser.parseStrict(json);
        object = value.isObject();
    }

    public String getString(String key)
    {
        return object.get(key).toString();
    }

}
