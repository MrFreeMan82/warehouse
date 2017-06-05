package com.warehouse.shared.dto;


import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.GroupEntity;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.shared.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@EntityLocator(read = GroupEntity.class)
public final class Group extends DTO {

    public Long groupId;
    public Long statusId;
    public Long operator;
    public String name;
    public Integer left;
    public Integer right;
    public boolean isLeaf;

    public Group(){}
    public Group(Long id){setId(id);}

    @Override
    public DTO copyEntity(CustomEntity entity) {

        GroupEntity group = (GroupEntity) entity;
        setId(group.id);
        groupId = group.groupId;
        statusId = group.statusId;
        operator = group.operator;
        name = group.name;
        left = group.left;
        right = group.right;
        isLeaf = group.isLeaf;
        return this;
    }

    @Override
    public CustomEntity createEntity() {
        GroupEntity group = new GroupEntity();
        group.id = getId();
        group.groupId = groupId;
        group.statusId = statusId == null ? 1: statusId;
        group.operator = operator == null ? 0: operator;
        group.name = name;
        group.left = left == null ? 1 : left;
        group.right = right == null ? 0: right;
        group.isLeaf = isLeaf;
        return group;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {parentID} {name} {isLeaf}", getId(), 0, name, isLeaf);
    }
}

