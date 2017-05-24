package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.Rule;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 13.05.2017.
 *
 */

@Entity(name = "RULE")
@DTOLocator(value = Rule.class)
public final class RuleEntity extends CustomEntity
{
    private  Long id;
    private UserTypeEntity userType;
    private Integer order;
    private String present;
    private String widgets;
    private String request;
    private String comment;
    private char apply;         // + enabled;   - disabled;  v visible  # invisible  s - set value

    @Id
    @Column(name = "ID")
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_TYPE_ID", referencedColumnName = "id")
    public UserTypeEntity getUserTypeEntity() {return userType;}
    public void setUserTypeEntity(UserTypeEntity userType) {this.userType = userType;}

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
    @Column(name = "REQUEST")
    public String getQueries() { return request;}
    public void setQueries(String request) {this.request = request;}

    @Column(name = "COMMENT")
    public String getComment() {return comment;}
    public void setComment(String comment) {this.comment = comment;}


    @NotNull
    @Column(name = "APPLY")
    public char getApply() {return apply;}
    public void setApply(char apply) {this.apply = apply;}
}
