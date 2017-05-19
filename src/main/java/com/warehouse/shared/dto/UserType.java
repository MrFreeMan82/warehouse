package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.UserTypeEntity;

import java.io.Serializable;


/**
 * Created by Дима on 27.04.2017.
 *
 */
@EntityLocator(value = UserTypeEntity.class)
public final class UserType extends DTO implements Serializable
{
    private RuleSet ruleSet;
    private String name;

    public UserType(){}
    public UserType(UserTypeEntity userType) {
        copyEntity(userType);
    }

    public RuleSet getRuleSet() {return ruleSet;}
    public void setRuleSet(RuleSet ruleSet) { this.ruleSet = ruleSet; }


    public String getName() {  return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public DTO copyEntity(CustomEntity entity)
    {
        UserTypeEntity userType = (UserTypeEntity) entity;
        super.setId(userType.getId());
        ruleSet = new RuleSet(userType.getRuleSet());
        name = userType.getName();
        return this;
    }
}


