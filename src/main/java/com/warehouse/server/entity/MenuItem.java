package com.warehouse.server.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Дима on 05.05.2017.
 *
 */

@Entity
@Table(name = "MENU_ITEM")
public class MenuItem
{
    private Long id;
    private MenuItem parent;
    private byte[] name;
    private List<MenuItem> children;

    @Id
    @Column(name = "ID")
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}


    @NotNull
    @ManyToOne
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    public MenuItem getParent() {return parent;}
    public void setParent(MenuItem parent) {this.parent = parent;}


    @NotNull
    @Column(name = "NAME")
    public String getName() {return new String(name, Charset.forName("UTF-8"));}
    public void setName(String name) {this.name = name.getBytes();}


    @NotNull
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID")
    public List<MenuItem> getChildren() {return children;}
    public void setChildren(List<MenuItem> children) {this.children = children;}
}
