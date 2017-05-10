package com.warehouse.shared.transition;

/**
 * Created by Дима on 08.05.2017.
 *
 */

public class Transition<T, F extends Function>
{
    public T trigger;
    public F action;

    public Transition(T trigger, F action)
    {
        this.trigger = trigger;
        this.action = action;
    }
}
