package com.warehouse.shared.dto;


import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.ArtGroupEntity;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@EntityLocator(read = ArtGroupEntity.class)
public final class ArtGroup extends DTO {

    public Long groupId;
    public Long statusId;
    public String name;
    public Integer left;
    public Integer right;


    @Override
    public DTO copyEntity(CustomEntity entity) {

        ArtGroupEntity group = (ArtGroupEntity) entity;
        setId(group.id);
        groupId = group.groupId;
        statusId = group.statusId;
        name = group.name;
        left = group.left;
        right = group.right;
        return this;
    }

    @Override
    public CustomEntity createEntity() {
        ArtGroupEntity group = new ArtGroupEntity();
        group.id = getId();
        group.groupId = groupId;
        group.statusId = statusId;
        group.name = name;
        group.left = left;
        group.right = right;
        return group;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {parentID} {name}", getId(), groupId, name);
    }
}

