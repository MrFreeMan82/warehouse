package com.warehouse.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 13.05.2017.
 *
 */

@Entity
@Table(name = "RULE")
public class Rule
{
    private Long id;
    private RuleSet ruleSet;
    private Integer order;
    private String present;
    private String widgets;
    private String comment;
    private char apply;         // + enabled;   - disabled;  v visible  # invisible

    @Id
    @Column(name = "ID")
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @ManyToOne
    @JoinColumn(name = "RULE_SET_ID", referencedColumnName = "ID")
    public RuleSet getRuleSet() {return ruleSet;}
    public void setRuleSet(RuleSet ruleSet) {this.ruleSet = ruleSet;}


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


    @Column(name = "COMMENT")
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}


    @NotNull
    @Column(name = "APPLY")
    public char getApply() {return apply;}
    public void setApply(char apply) {this.apply = apply;}
}
