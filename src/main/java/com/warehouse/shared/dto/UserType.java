package com.warehouse.shared.dto;

import com.warehouse.server.entity.UserTypeEntity;

import java.io.Serializable;


/**
 * Created by Дима on 27.04.2017.
 *
 */

public class UserType extends DTO implements Serializable
{
    private RuleSet ruleSet;
    private String name;

    public UserType(){}
    public UserType(UserTypeEntity userType) {
        super.setId(userType.getId());
        ruleSet = new RuleSet(userType.getRuleSet());
        name = userType.getName();
    }

    public RuleSet getRuleSet() {return ruleSet;}
    public void setRuleSet(RuleSet ruleSet) { this.ruleSet = ruleSet; }


    public String getName() {  return name; }
    public void setName(String name) { this.name = name; }
}


