package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.Artiqule;
import com.warehouse.shared.dto.Price;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 05.06.2017.
 *
 */
@Entity(name = "AR_PRICE")
@DTOLocator(value = Price.class)
public class PriceEntity extends CustomEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GEN_AR_PRICE_ID")
            })
    public Long id;

    @ManyToOne
    @JoinColumn(name = "AR_ID", referencedColumnName = "id")
    public ArtiquleEntity artiquleEntity;

    @NotNull
    @Column(name = "TYPE_ID")
    public Long typeId;

    @NotNull
    @Column(name = "PRICE")
    public Long price;

    @NotNull
    @Column(name = "DELETED")
    public boolean deleted;
}
