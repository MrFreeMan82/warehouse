package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.Group;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@Entity(name = "AR_GROUP")
@DTOLocator(value = Group.class)
public final class GroupEntity extends CustomEntity {

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
    @Column(name = "OPERATOR_ID")
    public Long operator;

    @NotNull
    @Column(name = "NAME")
    public String name;

    @NotNull
    @Column(name = "L")
    public Integer left;

    @NotNull
    @Column(name = "R")
    public Integer right;

    @Column(name = "IS_LEAF")
    @Formula(value = "(select case when count(g1.id) > 0 then false else true end from ar_group g1 where g1.group_id = id)")
    public boolean isLeaf;
}
