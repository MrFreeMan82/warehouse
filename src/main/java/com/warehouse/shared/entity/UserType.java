package com.warehouse.shared.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.nio.charset.Charset;


/**
 * Created by Дима on 27.04.2017.
 *
 */
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "all_user_types", query = "SELECT * FROM USER_TYPE", resultClass = UserType.class)
})


@Entity
@Table(name = "USER_TYPE")
public class UserType extends Base implements Serializable
{
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

    @Override
    public String toString()
    {
      //  JsonObject json = new JsonObject();
     //   json.addProperty("ID", id);
     //   json.addProperty("NAME", name);

        return "UserType"; //json.toString();
    }
}


