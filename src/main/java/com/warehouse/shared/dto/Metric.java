package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.MetricEntity;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 29.05.2017.
 *
 */
@EntityLocator(read = MetricEntity.class)
public final class Metric extends DTO {

    public String name;

    @Override
    public DTO copyEntity(CustomEntity entity) {

        MetricEntity metric = (MetricEntity) entity;
        setId(metric.id);
        name = metric.name;
        return this;
    }

    @Override
    public CustomEntity createEntity() {

        return null;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {name}", getId(), name);
    }
}
