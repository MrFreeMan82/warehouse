package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.UserSession;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Created by Дима on 02.05.2017.
 *
 */

@Entity
@Table(name = "USER_SESSION")
@DTOLocator(value = UserSession.class)
public final class UserSessionEntity extends CustomEntity
{
    private  Long id;
    private UserDetailEntity user;
    private String key;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@org.hibernate.annotations.Parameter(name = "sequence_name", value = "GEN_SESSION_ID")
            })
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    public UserDetailEntity getUser() { return user; }
    public void setUser(UserDetailEntity user) { this.user = user; }


    @NotNull
    @Column(name = "S_KEY")
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
}
