package com.warehouse.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 13.05.2017.
 *
 */

@Entity
@Table(name = "RULE")
public class RuleEntity
{
    private Long id;
    private RuleSetEntity ruleSet;
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

    @Id
    @Column(name = "ID")
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RULE_SET_ID", referencedColumnName = "ID")
    public RuleSetEntity getRuleSet() {return ruleSet;}
    public void setRuleSet(RuleSetEntity ruleSet) {this.ruleSet = ruleSet;}


    @NotNull
    @Column(name = "ORDERBY")
    public Integer getOrder() {return order;}
    public void setOrder(Integer order) {this.order = order;}


    @NotNull
    @Column(name = "PRESENT")
    public String getPresent() {return present;}
    public void setPresent(String present) {this.present = present;}


    @NotNull
    @Column(name = "WIDGETS")
    public String getWidgets() {return widgets;}
    public void setWidgets(String widgets) {this.widgets = widgets;}


    @NotNull
    @Column(name = "QUERIES")
    public String getQueries() { return queries;}
    public void setQueries(String queries) {this.queries = queries;}

    @Column(name = "COMMENT")
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}


    @NotNull
    @Column(name = "APPLY")
    public char getApply() {return apply;}
    public void setApply(char apply) {this.apply = apply;}

    @Column(name = "SET_VALUE")
    public String getSetValue() {return setValue;}
    public void setSetValue(String setValue) {this.setValue = setValue;}

    @Column(name = "IF_CONDITION")
    public String getIfCondition() {return ifCondition;}
    public void setIfCondition(String ifCondition) {this.ifCondition = ifCondition;}

    @Column(name = "CONDITION")
    public String getCondition() {return condition;}
    public void setCondition(String condition) {this.condition = condition;}

    @Column(name = "CONDITION_VAL")
    public Integer getValue() {return value;}
    public void setValue(Integer value) {this.value = value;}
}
