package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.RuleEntity;
import com.warehouse.server.entity.RuleSetEntity;

import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * Created by Дима on 13.05.2017.
 *
 */
@EntityLocator(value = RuleEntity.class)
public final class Rule extends DTO implements Serializable
{
    private RuleSet ruleSetDTO;
    private Integer order;
    private String present;
    private String widgets;
    private String queries;
    private String comment;
    private char apply;         // + enabled;   - disabled;  v visible  # invisible  s - set value
    private String setValue;
    private String ifCondition;
    private String condition;
    private Integer value;

    public Rule(){}
    public Rule(RuleEntity rule, RuleSet ruleSetDTO) {

        this.ruleSetDTO = ruleSetDTO;
        copyEntity(rule);
    }

    public RuleSet getRuleSetDTO() {return ruleSetDTO;}
    public void setRuleSetDTO(RuleSet ruleSetDTO) {this.ruleSetDTO = ruleSetDTO;}


    public Integer getOrder() {return order;}
    public void setOrder(Integer order) {this.order = order;}


    public String getPresent() {return present;}
    public void setPresent(String present) {this.present = present;}


    public String getWidgets() {return widgets;}
    public void setWidgets(String widgets) {this.widgets = widgets;}


    public String getQueries() { return queries;}
    public void setQueries(String queries) {this.queries = queries;}

    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}


    public char getApply() {return apply;}
    public void setApply(char apply) {this.apply = apply;}


    public String getSetValue() {return setValue;}
    public void setSetValue(String setValue) {this.setValue = setValue;}


    public String getIfCondition() {return ifCondition;}
    public void setIfCondition(String ifCondition) {this.ifCondition = ifCondition;}


    public String getCondition() {return condition;}
    public void setCondition(String condition) {this.condition = condition;}


    public Integer getValue() {return value;}
    public void setValue(Integer value) {this.value = value;}


    @Override
    public CustomEntity createEntity() {

        RuleEntity entity = new RuleEntity();
        entity.setId(getId());
        entity.setOrder(order);
        entity.setPresent(present);
        entity.setWidgets(widgets);
        entity.setQueries(queries);
        entity.setComment(comment);
        entity.setApply(apply);
        entity.setSetValue(setValue);
        entity.setIfCondition(ifCondition);
        entity.setCondition(condition);
        entity.setValue(value);
        return entity;
    }

    @Override
    public DTO copyEntity(CustomEntity entity) {

        RuleEntity  rule = (RuleEntity) entity;

        super.setId(rule.getId());
        order = rule.getOrder();
        present = rule.getPresent().trim();
        widgets = rule.getWidgets().trim();
        queries = rule.getQueries().trim();
        comment = rule.getComment();
        apply = rule.getApply();
        setValue = rule.getSetValue() == null ? null : rule.getSetValue().trim();
        ifCondition = rule.getIfCondition() == null ? null : rule.getIfCondition().trim();
        condition = rule.getCondition() == null ? null : rule.getCondition().trim();
        value = rule.getValue();
        return this;
    }
}
