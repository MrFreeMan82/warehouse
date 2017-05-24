package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.RuleEntity;
import com.warehouse.server.entity.UserTypeEntity;

import java.io.Serializable;

/**
 * Created by Дима on 13.05.2017.
 *
 */
@EntityLocator(read = RuleEntity.class)
public final class Rule extends DTO implements Serializable
{
    private UserType userType;
    private Integer order;
    private String present;
    private String widgets;
    private String queries;
    private String comment;
    private char apply;         // + enabled;   - disabled;  v visible  # invisible  s - set value

    public Rule(){}
    public Rule(RuleEntity rule) {
        copyEntity(rule);
    }

    public UserType getUserType() {return userType;}
    public void setUserType(UserType userType) {this.userType = userType;}

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


    @Override
    public CustomEntity createEntity() {

        RuleEntity entity = new RuleEntity();
        entity.setId(getId());
        entity.setUserTypeEntity((UserTypeEntity) userType.createEntity());
        entity.setOrder(order);
        entity.setPresent(present);
        entity.setWidgets(widgets);
        entity.setQueries(queries);
        entity.setComment(comment);
        entity.setApply(apply);
        return entity;
    }

    @Override
    public DTO copyEntity(CustomEntity entity) {

        RuleEntity  rule = (RuleEntity) entity;

        super.setId(rule.getId());
        userType = new UserType(rule.getUserTypeEntity());
        order = rule.getOrder();
        present = rule.getPresent().trim();
        widgets = rule.getWidgets().trim();
        queries = rule.getQueries().trim();
        comment = rule.getComment() == null ? "": rule.getComment();
        apply = rule.getApply();
        return this;
    }
}
