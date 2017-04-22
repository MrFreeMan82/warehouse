package com.warehouse.shared;

import com.warehouse.client.Warehouse;
import com.warehouse.client.event.ErrorEvent;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by Дима on 19.04.2017.
 *
 */

public class Utils
{
    public enum Services
    {
        LOGIN, USER;

        public enum Detail{
            CREATE, UPDATE, REMOVE, SELECT
        }
    }

    public enum JSON
    {
        RESULT, LOGIN_KEY
    }

    public static String hashString(String password)
    {
        if(password.equals("")) return "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            Warehouse.eventBus.fireEvent(new ErrorEvent(e, "Hash Error"));
            return "";
        }

        md.reset();
        byte[] bytes = md.digest(password.getBytes(Charset.forName("UTF-8")));
        StringBuilder hash = new StringBuilder();
        for(byte one: bytes)
            hash.append(Integer.toHexString((one & 0xFF) | 0x100).substring(1,3));
        return hash.toString();
    }
}