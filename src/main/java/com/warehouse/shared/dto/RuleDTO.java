package com.warehouse.shared.dto;

import com.warehouse.server.entity.Rule;

import java.nio.charset.Charset;

/**
 * Created by Дима on 13.05.2017.
 *
 */

public class RuleDTO extends DTO
{
    private Long id;
    private Integer order;
    private RuleSetDTO ruleSetDTO;
    private String present;
    private String widgets;
    private byte[] comment;
    private char apply;         // + enabled;   - disabled;  v visible  # invisible

    public RuleDTO(){}
    public RuleDTO(Rule rule, RuleSetDTO ruleSetDTO)
    {
        id = rule.getId();
        order = rule.getOrder();
        this.ruleSetDTO = ruleSetDTO;
        present = rule.getPresent();
        widgets = rule.getWidgets();
        comment = (rule.getComment() == null)? new byte[]{}: rule.getComment().getBytes();
        apply = rule.getApply();
    }


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public RuleSetDTO getRuleSetDTO() {return ruleSetDTO;}
    public void setRuleSetDTO(RuleSetDTO ruleSetDTO) {this.ruleSetDTO = ruleSetDTO;}

    public Integer getOrder() {return order;}
    public void setOrder(Integer order) {this.order = order;}


    public String getPresent() {return present;}
    public void setPresent(String present) {this.present = present;}

    public String getWidgets() {return widgets;}
    public void setWidgets(String widgets) {this.widgets = widgets;}

    public String getComment() {return new String(comment, Charset.forName("UTF-8"));}
    public void setComment(String comment) {this.comment = comment.getBytes();}

    public char getapply() {return apply;}
    public void setapply(char apply) {this.apply = apply;}
}
