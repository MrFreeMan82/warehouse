package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.ArtiquleEntity;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.PriceEntity;
import com.warehouse.shared.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@EntityLocator(read = ArtiquleEntity.class)
public class Artiqule extends DTO {

    public Long groupId;
    public Long metricId;
    public Long statusId;
    public Long operator;
    public String name;
    public String shortName;
    public Metric metric;
    public List<Price> prices = new ArrayList<>();

    public Artiqule setGroupId(Long groupId) {this.groupId = groupId; return this;}
    public Artiqule setIdent(Long id){setId(id); return this;}

    @Override
    public DTO copyEntity(CustomEntity entity) {

        ArtiquleEntity artiqule = (ArtiquleEntity) entity;
        setId(artiqule.id);
        groupId = artiqule.groupId;
        metricId = artiqule.metricId;
        statusId = artiqule.statusId;
        operator = artiqule.operator;
        name = artiqule.name;
        shortName = artiqule.shortName;

        metric = (Metric) new Metric().copyEntity(artiqule.metric);

        if(artiqule.prices.size() > 0)
            artiqule.prices.forEach(price-> prices.add((Price) new Price().copyEntity(price)));
        return this;
    }

    @Override
    public CustomEntity createEntity() {

        ArtiquleEntity artiqule = new ArtiquleEntity();
        artiqule.id = getId();
        artiqule.groupId = groupId;
        artiqule.metricId = metricId == null ? 0: metricId;
        artiqule.statusId = statusId == null ? 0: statusId;
        artiqule.operator = operator == null ? 0: operator;
        artiqule.name = name;
        artiqule.shortName = shortName;
        artiqule.prices = new ArrayList<>();
        if (prices.size() > 0) prices.forEach(price-> {
            PriceEntity entity = (PriceEntity) price.createEntity();
            entity.artiquleEntity = artiqule;
            artiqule.prices.add(entity);
        });
        return artiqule;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(prices.size() > 0) {
            prices.forEach(price->{builder.append(price.toString()); builder.append(';');});
        }
        return Utils.format("{id} {name} {metric} [{prices}]", getId(), name, metric.name, builder.toString());
    }
}
