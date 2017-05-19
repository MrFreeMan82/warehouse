package com.warehouse.shared;

import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by Дима on 17.05.2017.
 *
 */

public class Utils
{
    public static String format (String pattern, final Object ... args)
    {
        for (Object arg : args) {
            String part1 = pattern.substring(0,pattern.indexOf('{'));
            String part2 = pattern.substring(pattern.indexOf('}') + 1);
            pattern = part1 + arg + part2;
        }
        return pattern;
    }

    public static String hashString(String password)
    {
        if(password.equals("")) return "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
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
