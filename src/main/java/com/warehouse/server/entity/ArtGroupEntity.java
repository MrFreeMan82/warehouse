package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.ArtGroup;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@Entity(name = "AR_GROUP")
@DTOLocator(value = ArtGroup.class)
public final class ArtGroupEntity extends CustomEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "GEN_AR_GROUP_ID")
            })
    public Long id;

    @NotNull
    @Column(name = "GROUP_ID")
    public Long groupId;

    @NotNull
    @Column(name = "STATUS_ID")
    public Long statusId;

    @NotNull
    @Column(name = "NAME")
    public String name;

    @NotNull
    @Column(name = "L")
    public Integer left;

    @NotNull
    @Column(name = "R")
    public Integer right;
}
