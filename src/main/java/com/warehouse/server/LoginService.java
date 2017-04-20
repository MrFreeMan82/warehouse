package com.warehouse.server;

import com.google.gson.JsonObject;
import com.warehouse.server.objects.User;
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

        String psw =  req.getParameter(Utils.POST_Keys.PASSWORD.name());

        User user = new User();
        user.setUser_type_id(1);
        user.setName("Петя");
        user.setPassword(psw);

        PrintWriter out = resp.getWriter();

        if (psw.equals(""))
            json.addProperty(Utils.JSON_Keys.RESULT.name(), "401: Invalid password.");
        else
            json.addProperty(Utils.JSON_Keys.RESULT.name(), "200: OK");

        json.addProperty(Utils.JSON_Keys.LOGIN_KEY.name(), psw);
        out.println(json.toString());
    }
}
