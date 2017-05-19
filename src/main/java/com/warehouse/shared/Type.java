package com.warehouse.shared;

/**
 * Created by Дима on 19.05.2017.
 *
 */

public enum Type {
    LOGIN(""),
    USER_BY_PASSWORD("select ud from USER_DETAIL ud where ud.password ='%s'"),
    MAIN_MENU("select m from MENU m");

    private String pattern;

    Type(String pattern){this.pattern = pattern;}

    public String getPattern(){return pattern;}
}
