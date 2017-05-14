package com.warehouse.shared.dto;

import com.warehouse.server.entity.Rule;
import com.warehouse.server.entity.RuleSet;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 14.05.2017.
 *
 */

public class RuleSetDTO extends DTO
{
    private Long id;
    private Integer priority;
    private byte[] comment;
    private List<RuleDTO> ruleDTOS;

    public RuleSetDTO(){}
    public RuleSetDTO(RuleSet ruleSet)
    {
        id = ruleSet.getId();
        priority = ruleSet.getPriority();
        comment = (ruleSet.getComment() == null) ? new byte[]{}: ruleSet.getComment().getBytes();
        ruleDTOS = new ArrayList<>();
        if(ruleSet.getRules() == null) return;
        for(Rule rule: ruleSet.getRules()) ruleDTOS.add(new RuleDTO(rule, this));
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Integer getPriority() {return priority;}
    public void setPriority(Integer priority) {this.priority = priority;}

    public String getComment() {return new String(comment, Charset.forName("UTF-8"));}
    public void setComment(String comment) {this.comment = comment.getBytes();}


    public List<RuleDTO> getAsList() {return ruleDTOS;}
    public void setAsList(List<RuleDTO> ruleDTOS) {this.ruleDTOS = ruleDTOS;}
}
