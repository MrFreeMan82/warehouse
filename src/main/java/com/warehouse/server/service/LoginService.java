package com.warehouse.server.service;

import com.google.gson.JsonObject;
import com.warehouse.server.object.UserDetail;
import com.warehouse.shared.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


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


        String psw =  req.getParameter("");

        UserDetail user = new UserDetail();
        user.setUserType(1);
        user.setName("Петя");
        user.setPassword(psw);

        PrintWriter out = resp.getWriter();

         if ((psw == null) || psw.equals(""))
            json.addProperty(Utils.JSON.RESULT.name(), "401: Invalid password.");
        else
            json.addProperty(Utils.JSON.RESULT.name(), "200: OK");

        json.addProperty(Utils.JSON.LOGIN_KEY.name(), psw);
        out.println(json.toString());
    }
}
