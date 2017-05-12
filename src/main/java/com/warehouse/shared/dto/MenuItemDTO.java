package com.warehouse.shared.dto;


import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

public class MenuItemDTO extends DTO implements Serializable
{
    private Long id;
    private MenuItemDTO parent;
    private byte[] name;
    private List<MenuItemDTO> children;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public MenuItemDTO getParent() {return parent;}
    public void setParent(MenuItemDTO parent) {this.parent = parent;}


    public String getName() {return new String(name, Charset.forName("UTF-8"));}
    public void setName(String name) {this.name = name.getBytes();}


    public List<MenuItemDTO> getChildren() {return children;}
    public void setChildren(List<MenuItemDTO> children) {this.children = children;}
}
