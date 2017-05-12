package com.warehouse.server.entity;

import com.warehouse.shared.constraint.UserDetailConstraint;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by Дима on 20.04.2017.
 *
 */
@Entity
@Table(name = "USER_DETAIL")
public class UserDetail
{
    private Long id;
    private UserType type;
    private String name;
    private String password;


    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "GEN_USER_DETAIL_ID")
            })
    public Long getId() { return id;  }
    public void setId(Long id) { this.id = id; }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    public UserType getUserType() { return type; }
    public void setUserType(UserType type) { this.type = type; }


    @Column(name = "NAME")
    @NotNull
    @Size(min = UserDetailConstraint.MIN_USER_NAME, max = UserDetailConstraint.MAX_USER_NAME)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    @Column(name = "HASHED_PASSWORD")
    @NotNull
    @Size(min = UserDetailConstraint.MIN_PASSWORD, max = UserDetailConstraint.MAX_PASSWORD)
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
