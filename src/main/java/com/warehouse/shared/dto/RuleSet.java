package com.warehouse.shared.dto;

import com.warehouse.client.present.Present;
import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.RuleEntity;
import com.warehouse.server.entity.RuleSetEntity;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 14.05.2017.
 *
 */

@EntityLocator(value = RuleSetEntity.class)
public final class RuleSet extends DTO
{
    private Integer priority;
    private String comment;
    private List<Rule> rules = new ArrayList<>();

    public RuleSet(){}
    public RuleSet(RuleSetEntity ruleSet) {
        copyEntity(ruleSet);
    }

    public Integer getPriority() {return priority;}
    public void setPriority(Integer priority) {this.priority = priority;}

    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}


    public List<Rule> getAsList() {return rules;}
    public void setAsList(List<Rule> rules) {this.rules = rules;}

    public List<Rule> filterByPresent(Present present)
    {
        String filter = present.getClass().getName();
        List<Rule> filteredRules = new ArrayList<>();
        if(rules.size() > 0) {
            for (Rule rule : rules)
                if (rule.getPresent().equals(filter)) filteredRules.add(rule);
        }

        return filteredRules;
    }

    @Override
    public DTO copyEntity(CustomEntity entity) {
        RuleSetEntity ruleSet = (RuleSetEntity) entity;
        super.setId(ruleSet.getId());
        priority = ruleSet.getPriority();
        comment = ruleSet.getComment();
        if(ruleSet.getRules() != null)
            for(RuleEntity rule: ruleSet.getRules()) rules.add(new Rule(rule, this));
        return this;
    }
}
