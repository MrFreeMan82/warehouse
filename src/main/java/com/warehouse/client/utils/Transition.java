package com.warehouse.client.utils;

/**
 * Created by Дима on 08.05.2017.
 *
 */

public class Transition
{
    public Long trigger;
    public Function action;

    public Transition(Long trigger, Function action)
    {
        this.trigger = trigger;
        this.action = action;
    }
}
