package com.warehouse.shared.source;

import com.warehouse.shared.dto.Rule;

import java.util.List;

/**
 * Created by Дима on 16.05.2017.
 *
 */

public interface RuleAction
{
    void apply(List<Rule> rules);
}
