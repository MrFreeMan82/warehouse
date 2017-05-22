package com.warehouse.shared.request;

/**
 * Created by Дима on 19.05.2017.
 *
 */

public enum Type {
    LOGIN(""),
    USER_BY_PASSWORD("select ud from USER_DETAIL ud where ud.password ='%s'"),
    USER_LIST("select list from USER_DETAIL list"),
    USER_TYPE_LIST("select list from USER_TYPE list"),
    INSERT_USER("insert into USER_DETAIL"),
    UPDATE_USER("update user"),
    MAIN_MENU("select m from MENU m where m.id > 0");


    private String pattern;

    Type(String pattern){this.pattern = pattern;}

    public String getPattern(){return pattern;}
}
