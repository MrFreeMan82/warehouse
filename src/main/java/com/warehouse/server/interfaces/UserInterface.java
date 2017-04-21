package com.warehouse.server.interfaces;

import com.warehouse.server.objects.UserDetail;

/**
 * Created by Дима on 20.04.2017.
 *
 */

public interface UserInterface
{
    void addUser(UserDetail user);
    void updateUser(UserDetail user);
    void removeUser(UserDetail user);
}
