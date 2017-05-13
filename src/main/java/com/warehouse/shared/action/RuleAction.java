package com.warehouse.shared.action;

import com.warehouse.shared.dto.RuleDTO;
import com.warehouse.shared.dto.UserTypeDTO;

import java.util.List;

/**
 * Created by Дима on 13.05.2017.
 *
 */

public interface RuleAction
{
    List<RuleDTO> requestRules(UserTypeDTO userTypeDTO, String present);
}
