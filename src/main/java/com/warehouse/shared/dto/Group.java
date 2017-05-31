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
    public String name;
    public Integer left;
    public Integer right;
    public boolean isLeaf;
    public List<Artiqule> artiqules = new ArrayList<>();


    @Override
    public DTO copyEntity(CustomEntity entity) {

        GroupEntity group = (GroupEntity) entity;
        setId(group.id);
        groupId = group.groupId;
        statusId = group.statusId;
        name = group.name;
        left = group.left;
        right = group.right;
        isLeaf = group.isLeaf;
        if(group.artiqules.size() > 0)
            group.artiqules.forEach(item->artiqules.add((Artiqule) new Artiqule().copyEntity(item)));
        return this;
    }

    @Override
    public CustomEntity createEntity() {
        GroupEntity group = new GroupEntity();
        group.id = getId();
        group.groupId = groupId;
        group.statusId = statusId == null ? 1: statusId;
        group.name = name;
        group.left = left == null ? 1 : left;
        group.right = right == null ? 0: right;
        group.isLeaf = isLeaf;
        return group;
    }

    @Override
    public String toString() {
        StringBuilder art = new StringBuilder();
        if(artiqules != null && artiqules.size() > 0) {
            for (Artiqule artiqule : artiqules) {
                art.append(artiqule.name);
                art.append(" ");
                art.append(artiqule.metric.name);
                art.append('\n');
            }
        }
        return Utils.format("{id} {parentID} {name} {isLeaf} {tov}", getId(), 0, name, isLeaf, art);
    }
}

