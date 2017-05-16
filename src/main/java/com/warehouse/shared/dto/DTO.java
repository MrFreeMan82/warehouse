package com.warehouse.shared.dto;

import java.io.Serializable;

/**
 * Created by Дима on 02.05.2017.
 *
 */

public class DTO implements Serializable {

    private Long id;
    private String sessionKey;


    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getSessionKey() {return sessionKey;}
    public void setSessionKey(String sessionKey) {this.sessionKey = sessionKey;}
}
