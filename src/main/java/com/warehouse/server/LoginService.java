package com.warehouse.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.JsonObject;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 13.04.2017.
 *
 */
public class LoginService extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        JsonObject json = new JsonObject();

        String psw =  req.getParameter("psw");
        PrintWriter out = resp.getWriter();

        if (psw.equals(""))
            json.addProperty(Utils.RESULT, "401: Invalid password.");
        else
            json.addProperty(Utils.RESULT, "200: OK");
        json.addProperty(Utils.KEY, psw);

        out.println(json.toString());
    }
}
