package com.warehouse.shared.entity;

import com.warehouse.server.dao.DAOLocator;
import com.warehouse.server.dao.MenuItemDAO;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

@DAOLocator(value = MenuItemDAO.class)
public class MenuItem extends Base
{
    public static final String GET_ALL_MENU_ITEMS ="getAllMenuItems.sql";

    // This constants must be used in id;
    public static final Long USER_LIST = 0x1L;

    private Long id;
    private byte[] name;
    private List<MenuItem> children;
    private Integer level;
    private String owner;

    public MenuItem(){}

    public MenuItem(Long id){this.id = id;}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return new String(name, Charset.forName("UTF-8"));}
    public void setName(String name) {this.name = name.getBytes();}

    public List<MenuItem> getChildren() {return children;}
    public void setChildren(List<MenuItem> children) {this.children = children;}

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) {this.level = level;}

    public String getOwner() {return owner;}
    public void setOwner(String owner) {this.owner = owner;}
}
