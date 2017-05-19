package com.warehouse.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Дима on 09.05.2017.
 *
 */

class Resource
{
    static String getSQL(String resourceName)
    {
        String resource = "/sql/" + resourceName;

        try(InputStream stream = Resource.class.getResourceAsStream(resource))
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder query = new StringBuilder();
            while ((line = reader.readLine()) != null) {query.append(line); query.append('\n');}

            return query.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Resource " + resource + " not found");
        }
    }
}
