package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.ArtiquleViewEntity;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@EntityLocator(read = ArtiquleViewEntity.class)
public final class ArtiquleView extends Artiqule {

    public Long parentGroupId;
    public String groupName;
    public String metricName;
    public Double price1;
    public Double price2;


    @Override
    public DTO copyEntity(CustomEntity entity) {

        ArtiquleViewEntity artiqule = (ArtiquleViewEntity) entity;
        setId(artiqule.id);
        groupId = artiqule.group_id;
        metricId = artiqule.metric_id;
        statusId = artiqule.status_id;
        name = artiqule.name;
        shortName = artiqule.short_name;
        parentGroupId = artiqule.parent_group_id;
        groupName = artiqule.group_name;
        metricName = artiqule.metric_name;
        price1 = artiqule.price1;
        price2 = artiqule.price2;
        return this;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {group} {name} {metric} {p1} {p2}", getId(), groupName, name, metricName, price1, price2);
    }
}
