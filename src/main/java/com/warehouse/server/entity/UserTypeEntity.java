package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.UserType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by Дима on 27.04.2017.
 *
 */

@Entity(name = "USER_TYPE")
@DTOLocator(value = UserType.class)
public final class UserTypeEntity extends CustomEntity
{
    private Long id;
    private String name;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GEN_USER_TYPE_ID")
            })
    public Long getId() {return id; }
    public void setId(Long id) { this.id = id; }


    @Column(name = "NAME")
    @NotNull
    public String getName() {  return name; }
    public void setName(String name) { this.name = name; }
}


