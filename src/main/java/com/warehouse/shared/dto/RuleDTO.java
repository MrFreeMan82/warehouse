package com.warehouse.shared.dto;

import com.warehouse.server.dao.DAOLocator;
import com.warehouse.server.dao.RuleDAO;
import com.warehouse.server.entity.Rule;

/**
 * Created by Дима on 13.05.2017.
 *
 */

@DAOLocator(value = RuleDAO.class)
public class RuleDTO extends DTO
{
    private Long id;
    private UserTypeDTO userType;
    private Integer order;
    private String present;
    private String widgets;
    private String comment;
    private char action;         // + enabled;   - disabled;  v visible  # invisible
    private String exprA;
    private String condition;
    private String exprB;

    public RuleDTO(){}
    public RuleDTO(Rule rule)
    {
        id = rule.getId();
        userType = new UserTypeDTO(rule.getUserType());
        order = rule.getOrder();
        present = rule.getPresent();
        widgets = rule.getWidgets();
        comment = rule.getComment();
        action = rule.getAction();
        exprA = rule.getExprA();
        condition = rule.getCondition();
        exprB = rule.getExprB();
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public UserTypeDTO getUserTypeDTO() {return userType;}
    public void setUserTypeDTO(UserTypeDTO userType) {this.userType = userType;}

    public Integer getOrder() {return order;}
    public void setOrder(Integer order) {this.order = order;}

    public String getPresent() {return present;}
    public void setPresent(String present) {this.present = present;}

    public String getWidgets() {return widgets;}
    public void setWidgets(String widgets) {this.widgets = widgets;}

    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}

    public char getAction() {return action;}
    public void setAction(char action) {this.action = action;}

    public String getExprA() { return exprA;}
    public void setExprA(String exprA) {this.exprA = exprA;}

    public String getCondition() {return condition;}
    public void setCondition(String condition) {this.condition = condition;}

    public String getExprB() {return exprB;}
    public void setExprB(String exprB) {this.exprB = exprB;}
}
