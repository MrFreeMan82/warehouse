package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.UserDetail;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Дима on 20.04.2017.
 *
 */
@Entity(name = "USER_DETAIL")
@DTOLocator(value = UserDetail.class)
public final class UserDetailEntity extends CustomEntity
{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "GEN_USER_DETAIL_ID")
            })
    public Long id;

    @NotNull
    @Column(name = "TYPE_ID")
    public Long type_id;

    @NotNull
    @Column(name = "STATUS_ID")
    public Long status_id;

    @Column(name = "NAME")
    @NotNull
    @Size(min = 2, max = 255)
    public String name;

    @Column(name = "HASHED_PASSWORD")
    @NotNull
    @Size(min = 2, max = 255)
    public String password;
}
