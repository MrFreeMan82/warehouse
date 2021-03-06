package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.MenuItem;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 05.05.2017.
 *
 */

@Entity(name = "MENU")
@DTOLocator(value = MenuItem.class)
public final class MenuItemEntity extends CustomEntity
{
    private  Long id;
    private MenuItemEntity parent;
    private String name;
    private Integer order;
    private String present;
    private boolean isLeaf;
   // private List<MenuItemEntity> children;

    @Id
    @Column(name = "ID")
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}


    @NotNull
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "id")
    public MenuItemEntity getParent() {return parent;}
    public void setParent(MenuItemEntity parent) {this.parent = parent;}


    @NotNull
    @Column(name = "NAME")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @NotNull
    @Column(name = "ORDERBY")
    public Integer getOrder() {return order;}
    public void setOrder(Integer order) {this.order = order;}

    @Column(name = "PRESENT")
    public String getPresent() {return present;}
    public void setPresent(String present) {this.present = present;}

    @Column(name = "IS_LEAF")
    @Formula(value = "(select case when count(m1.id) > 0 then false else true end from MENU m1 where m1.parent_id = id)")
    public boolean isLeaf() {return isLeaf;}
    public void setLeaf(boolean leaf) {isLeaf = leaf;}


    /*   @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    public List<MenuItemEntity> getChildren() {return children;}
    public void setChildren(List<MenuItemEntity> children) {this.children = children;}

    */
}
