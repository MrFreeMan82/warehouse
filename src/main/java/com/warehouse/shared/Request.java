package com.warehouse.shared;

import com.warehouse.shared.dto.DTO;
import com.warehouse.shared.dto.MenuItem;

import java.io.Serializable;

/**
 * Created by Дима on 17.05.2017.
 *
 */

public class Request implements Serializable {

    private DTO example;
    private Type type;
    private String sessionKey;

    public Request(){}
    public Request(Type type, DTO example){
        this.type = type;
        this.example = example;
    }

    public DTO getExample() {return example;}
    public Request setExample(DTO example) {this.example = example; return this;}

    public Type getType() {return type;}
    public Request setType(Type type) {this.type = type; return this;}

    public String getSessionKey() {return sessionKey;}
    public Request setSessionKey(String sessionKey) {this.sessionKey = sessionKey; return this;}
}
