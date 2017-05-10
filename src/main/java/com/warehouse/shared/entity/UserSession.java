package com.warehouse.shared.entity;

import com.warehouse.server.dao.DAOLocator;
import com.warehouse.server.dao.UserSessionDAO;
import com.warehouse.shared.constraint.SessionConstaint;
import org.hibernate.annotations.*;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * Created by Дима on 02.05.2017.
 *
 */

@Entity
@Table(name = "USER_SESSION")
@DAOLocator(value = UserSessionDAO.class)
public class UserSession extends Base implements Serializable
{
    public static final String FIND_SESSION_BY_KEY = "findSessionByKey.sql";

    private Long id;
    private UserDetail user;
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
    public UserDetail getUser() { return user; }
    public void setUser(UserDetail user) { this.user = user; }


    @NotNull
    @Column(name = "S_KEY")
    @Size(min = SessionConstaint.MIN_KEY, max = SessionConstaint.MAX_KEY)
    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
}
