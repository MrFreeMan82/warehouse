package com.warehouse.server.app_interface;

import com.warehouse.server.object.UserDetail;

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
