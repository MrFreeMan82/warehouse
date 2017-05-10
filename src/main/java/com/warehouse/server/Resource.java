package com.warehouse.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Дима on 09.05.2017.
 *
 */

class Resource
{
    private static Properties strings;

    static {
        String resource = "/string/string.properties";
        strings = new Properties();
        try(InputStream stream = Resource.class.getResourceAsStream(resource))
        {
            strings.load(stream);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Resource " + resource + " not found");
        }
    }

    static String getStringResource(String resourceName)
    {
        return strings.getProperty(resourceName);
    }

    static String getSQLResource(String resourceName)
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
