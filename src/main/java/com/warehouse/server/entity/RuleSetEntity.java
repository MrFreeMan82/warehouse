package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.RuleSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Дима on 14.05.2017.
 *
 */
@Entity(name = "RULE_SET")
@DTOLocator(value = RuleSet.class)
public class RuleSetEntity extends CustomEntity
{
    private Long id;
    private Integer priority;
    private String comment;
    private List<RuleEntity> rules;


    @Id
    @Column(name = "ID")
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @NotNull
    @Column(name = "PRIORITY")
    public Integer getPriority() {return priority;}
    public void setPriority(Integer priority) {this.priority = priority;}

    @Column(name = "COMMENT")
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    @NotNull
    @OneToMany(mappedBy = "ruleSet", fetch = FetchType.EAGER)
    @OrderBy(value = "present ASC, order ASC")
    public List<RuleEntity> getRules() {return rules;}
    public void setRules(List<RuleEntity> rules) {this.rules = rules;}
}
