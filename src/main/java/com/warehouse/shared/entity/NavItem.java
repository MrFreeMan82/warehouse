package com.warehouse.shared.entity;

import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public class NavItem extends Base
{
    // This constants must be used in id;
    public static final Long USER_LIST = 0x1L;

    private Long id;
    private byte[] name;
    private List<NavItem> children;
    private Integer level;
    private String owner;

    public NavItem(){}

    public NavItem(Long id){this.id = id;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return new String(name, Charset.forName("UTF-8"));}
    public void setName(String name) {this.name = name.getBytes();}

    public List<NavItem> getChildren() {return children;}
    public void setChildren(List<NavItem> children) {this.children = children;}

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) {this.level = level;}

    public String getOwner() {return owner;}
    public void setOwner(String owner) {this.owner = owner;}
}
