package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.ArtiquleEntity;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@EntityLocator(read = ArtiquleEntity.class)
public class Artiqule extends DTO {

    public Long groupId;
    public Long metricId;
    public Long statusId;
    public String name;
    public String shortName;

    @Override
    public DTO copyEntity(CustomEntity entity) {

        ArtiquleEntity artiqule = (ArtiquleEntity) entity;
        setId(artiqule.id);
        groupId = artiqule.groupId;
        metricId = artiqule.metricId;
        statusId = artiqule.statusId;
        name = artiqule.name;
        shortName = artiqule.shortName;
        return this;
    }

    @Override
    public CustomEntity createEntity() {

        ArtiquleEntity artiqule = new ArtiquleEntity();
        artiqule.id = getId();
        artiqule.groupId = groupId;
        artiqule.metricId = metricId;
        artiqule.statusId = statusId;
        artiqule.name = name;
        artiqule.shortName = shortName;
        return artiqule;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {name}", getId(), name);
    }
}
