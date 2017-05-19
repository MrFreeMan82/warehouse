package com.warehouse.shared.dto;


import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.MenuItemEntity;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */
@EntityLocator(value = MenuItemEntity.class)
public final class MenuItem extends DTO implements Serializable
{
    private MenuItem parent;
    private String name;
    private Integer order;
    private boolean isLeaf;

    public MenuItem(){}

    public MenuItem getParent() {return parent;}
    public void setParent(MenuItem parent) {this.parent = parent;}


    public Integer getOrder() {return order;}
    public void setOrder(Integer order) {this.order = order;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public boolean isLeaf() {return isLeaf;}
    public void setLeaf(boolean leaf) {isLeaf = leaf;}


    @Override
    public DTO copyEntity(CustomEntity entity) {

        MenuItemEntity menuItem = (MenuItemEntity) entity;
        MenuItemEntity parentEntity = menuItem.getParent();
        this.parent = new MenuItem();
        this.parent.setId(parentEntity.getId());
        this.parent.setName(parentEntity.getName());
        this.parent.setOrder(parentEntity.getOrder());
        this.parent.setLeaf(parentEntity.isLeaf());

        name = menuItem.getName();
        order = menuItem.getOrder();
        isLeaf = menuItem.isLeaf();

        return this;
    }
}
