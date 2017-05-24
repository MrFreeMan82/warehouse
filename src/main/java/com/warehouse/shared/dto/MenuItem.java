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
@EntityLocator(read = MenuItemEntity.class)
public final class MenuItem extends DTO implements Serializable
{
    private MenuItem parent;
    private String name;
    private Integer order;
    private String present;
    private boolean isGroup;
    private boolean isLeaf;

    public MenuItem(){}

    public MenuItem getParent() {return parent;}
    public void setParent(MenuItem parent) {this.parent = parent;}


    public Integer getOrder() {return order;}
    public void setOrder(Integer order) {this.order = order;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getPresent() {return present;}
    public void setPresent(String present) {this.present = present;}

    public boolean isGroup() {return isGroup;}
    public void setGroup(boolean group) {isGroup = group;}

    public boolean isLeaf() {return isLeaf;}
    public void setLeaf(boolean leaf) {isLeaf = leaf;}


    @Override
    public DTO copyEntity(CustomEntity entity) {

        MenuItemEntity menuItem = (MenuItemEntity) entity;
        MenuItemEntity parentEntity = menuItem.getParent();
        this.parent = new MenuItem();
        this.parent.setId(parentEntity.getId());

        setId(menuItem.getId());
        name = menuItem.getName();
        order = menuItem.getOrder();
        present = menuItem.getPresent() == null ? "" : menuItem.getPresent();
        isLeaf = menuItem.isLeaf();

        return this;
    }
}
