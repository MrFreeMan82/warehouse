package com.warehouse.server.interfaces;

import com.warehouse.server.objects.User;

/**
 * Created by Дима on 20.04.2017.
 *
 */

public interface UserInterface
{
    void addUser(User user);
    void updateUser(User user);
    void removeUser(User user);
}
