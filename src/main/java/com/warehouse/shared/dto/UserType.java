package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.RuleSetEntity;
import com.warehouse.server.entity.UserTypeEntity;
import com.warehouse.shared.Utils;

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
    public CustomEntity createEntity() {

        UserTypeEntity entity = new UserTypeEntity();
        entity.setId(getId());
        entity.setRuleSet((RuleSetEntity) ruleSet.createEntity());
        entity.setName(name);
        return entity;
    }

    @Override
    public DTO copyEntity(CustomEntity entity)
    {
        UserTypeEntity userType = (UserTypeEntity) entity;
        super.setId(userType.getId());
        ruleSet = new RuleSet(userType.getRuleSet());
        name = userType.getName();
        return this;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {name} {count_rules}", getId(), name, ruleSet == null ? 0 : ruleSet.getAsList().size());
    }
}


