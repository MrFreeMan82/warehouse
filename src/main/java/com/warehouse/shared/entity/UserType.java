package com.warehouse.shared.entity;

import com.warehouse.server.DAOLocator;
import com.warehouse.server.dao.UserTypeDAO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.nio.charset.Charset;


/**
 * Created by Дима on 27.04.2017.
 *
 */

@Entity
@Table(name = "USER_TYPE")
@DAOLocator(value = UserTypeDAO.class)
public class UserType extends Base implements Serializable
{
    public static final String GET_ALL_USER_TYPES = "getAllUserTypes.sql";

    private Long id;
    private byte[] name;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GEN_USER_TYPE_ID")
            })
    public Long getId() {return id; }
    public void setId(Long id) { this.id = id;   }

    @Column(name = "NAME")
    @NotNull
    public String getName() {  return new String(name, Charset.forName("UTF-8"));   }
    public void setName(String name) { this.name = name.getBytes(); }
}


