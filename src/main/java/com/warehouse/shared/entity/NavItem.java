package com.warehouse.shared.entity;

import javax.persistence.Entity;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public class NavItem extends Base
{
    private Long id;
    private byte[] name;
    private List<NavItem> children;
    private Integer level;
    private String tag;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return new String(name, Charset.forName("UTF-8"));}
    public void setName(String name) {this.name = name.getBytes();}

    public List<NavItem> getChildren() {return children;}
    public void setChildren(List<NavItem> children) {this.children = children;}

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) {this.level = level;}

    public String getTag() {return tag;}
    public void setTag(String tag) {this.tag = tag;}
}
