package com.warehouse.client.utils;

/**
 * Created by Дима on 12.05.2017.
 *
 */

public interface Dock<T>
{
    void dockPresent(Dockable<T> present);
}
