package com.warehouse.shared.transition;

import com.warehouse.shared.entity.Base;

import java.util.List;

/**
 * Created by Дима on 10.05.2017.
 *
 */

public interface FunctionOneArg extends Function
{
    List<? extends Base> go(Base t);
}
