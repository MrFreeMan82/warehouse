package com.warehouse.shared.entity;

import com.warehouse.server.DAOLocator;
import com.warehouse.server.dao.SessionDAO;
import com.warehouse.shared.constraint.SessionConstaint;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.warehouse.shared.entity.Session.FIND_BY_KEY;

/**
 * Created by Дима on 02.05.2017.
 *
 */

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = FIND_BY_KEY, query = "SELECT * FROM SESSION WHERE S_KEY=:key", resultClass = Session.class)
})

@Entity
@Table(name = "SESSION")
@DAOLocator(value = SessionDAO.class)
public class Session extends Base implements Serializable
{
    public static final String FIND_BY_KEY = "find_by_key";

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
