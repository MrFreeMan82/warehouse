package com.warehouse.shared.function;

/**
 * Created by Дима on 10.05.2017.
 *
 */
public interface FunctionOneArg<Result, Argument> extends Function
{
    Result go(Argument t);
}