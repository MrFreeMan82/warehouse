package com.warehouse.shared.dto;

import com.warehouse.server.EntityLocator;
import com.warehouse.server.entity.ArtiquleEntity;
import com.warehouse.server.entity.CustomEntity;
import com.warehouse.server.entity.PriceEntity;
import com.warehouse.shared.Utils;

/**
 * Created by Дима on 05.06.2017.
 *
 */
@EntityLocator(read = PriceEntity.class)
public class Price extends DTO {

    public Long artiquleId;
    public Long typeId;
    public Long price;

    public Price(){}
    public Price(Long typeId, Long price){
        this.typeId = typeId; this.price = price;
    }

    @Override
    public DTO copyEntity(CustomEntity entity) {

        PriceEntity price = (PriceEntity) entity;
        setId(price.id);
        artiquleId = price.artiquleEntity.id;
        typeId = price.typeId;
        this.price = price.price;
        return this;
    }

    @Override
    public CustomEntity createEntity() {
        PriceEntity price = new PriceEntity();
        price.artiquleEntity = new ArtiquleEntity();
        price.artiquleEntity.id = artiquleId;
        price.typeId = typeId;
        price.price = this.price;
        return price;
    }

    @Override
    public String toString() {
        return Utils.format("{id} {price}", getId(), price);
    }
}
