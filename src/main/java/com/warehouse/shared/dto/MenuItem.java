package com.warehouse.shared.dto;


import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public class MenuItem extends DTO implements Serializable
{
    private MenuItem parent;
    private String name;
    private List<MenuItem> children;

    public MenuItem getParent() {return parent;}
    public void setParent(MenuItem parent) {this.parent = parent;}


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}


    public List<MenuItem> getChildren() {return children;}
    public void setChildren(List<MenuItem> children) {this.children = children;}
}
