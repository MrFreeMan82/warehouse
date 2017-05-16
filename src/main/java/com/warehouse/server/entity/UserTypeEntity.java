package com.warehouse.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by Дима on 27.04.2017.
 *
 */

@Entity
@Table(name = "USER_TYPE")
public class UserTypeEntity
{
    private Long id;
    private RuleSetEntity ruleSet;
    private String name;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GEN_USER_TYPE_ID")
            })
    public Long getId() {return id; }
    public void setId(Long id) { this.id = id;   }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "RULE_SET_ID", referencedColumnName = "ID")
    public RuleSetEntity getRuleSet() {return ruleSet;}
    public void setRuleSet(RuleSetEntity ruleSet) {this.ruleSet = ruleSet;}


    @Column(name = "NAME")
    @NotNull
    public String getName() {  return name; }
    public void setName(String name) { this.name = name; }
}


