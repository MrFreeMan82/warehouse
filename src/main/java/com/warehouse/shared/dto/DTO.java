package com.warehouse.shared.dto;

import com.warehouse.server.entity.CustomEntity;

import java.io.Serializable;

/**
 * Created by Дима on 02.05.2017.
 *
 */

public class DTO implements Serializable {

    private Long id;
    private Enum request;
    private boolean locked;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public Enum getRequest() {return request;}
    public void setRequest(Enum request) {this.request = request;}

    public boolean isLocked() {return locked;}
    public void setLocked(boolean locked) {this.locked = locked;}

    public DTO copyEntity(CustomEntity entity){throw new RuntimeException("Cant copy abstract entity.");}

    public CustomEntity createEntity(){throw new RuntimeException("Cant create abstract entity.");}
}
