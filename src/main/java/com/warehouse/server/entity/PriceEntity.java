package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.Price;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 05.06.2017.
 *
 */
@Entity(name = "AR_PRICE")
@DTOLocator(value = Price.class)
public class PriceEntity extends CustomEntity {

    @Id
    public Long id;

    @NotNull
    @Column(name = "AR_ID")
    public Long artiquleId;

    @NotNull
    @Column(name = "TYPE_ID")
    public Long typeId;

    @NotNull
    @Column(name = "PRICE")
    public Long price;
}
