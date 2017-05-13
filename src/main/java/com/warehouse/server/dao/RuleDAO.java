package com.warehouse.server.dao;

import com.warehouse.server.DAOService;
import com.warehouse.server.Resource;
import com.warehouse.server.entity.Rule;
import com.warehouse.shared.action.RuleAction;
import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.RuleDTO;
import com.warehouse.shared.dto.UserTypeDTO;
import com.warehouse.shared.function.FunctionOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 13.05.2017.
 *
 */

public class RuleDAO extends DAO implements RuleAction
{
    public static final String GET_RULES_BY_PRESENT_USERTYPE = "getRuleByPresentUserType";

    private HashMap<String, FunctionOne<List<RuleDTO>, RuleDTO>> queryTable = new HashMap<>();

    public RuleDAO()
    {
        queryTable.put(GET_RULES_BY_PRESENT_USERTYPE, (dto)->this.requestRules(dto.getUserTypeDTO(), dto.getPresent()));
    }

    protected List<RuleDTO> doSelect(String sql)
    {
        List<Rule> rules = internalSelect(sql, Rule.class);
        List<RuleDTO> ruleDTOS = new ArrayList<>();

        if((rules == null) || (rules.size() == 0)) return ruleDTOS;
        for (Rule rule: rules) ruleDTOS.add(new RuleDTO(rule));

        return ruleDTOS;
    }

    @Override
    public List<? extends DTO> select(String queryName, DTO example) {
        return queryTable.get(queryName).go((RuleDTO) example);
    }

    @Override
    public List<RuleDTO> requestRules(UserTypeDTO userTypeDTO, String present)
    {
        DAOService.logger.info(GET_RULES_BY_PRESENT_USERTYPE);
        String resource = GET_RULES_BY_PRESENT_USERTYPE + ".sql";
        String sql = String.format(Resource.getSQL(resource), userTypeDTO.getId(), present);
        return doSelect(sql);
    }
}
