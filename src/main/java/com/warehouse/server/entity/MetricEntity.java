package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.Metric;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Дима on 29.05.2017.
 *
 */
@Entity(name = "METRIC")
@DTOLocator(value = Metric.class)
public final class MetricEntity extends CustomEntity {

    @Id
    @Column(name = "ID")
    public Long id;

    @Column(name = "NAME")
    public String name;
}
