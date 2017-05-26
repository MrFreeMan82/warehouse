package com.warehouse.shared.request;

/**
 * Created by Дима on 19.05.2017.
 *
 */

public enum Type {
    LOGIN(""),
                // USER
    USER_BY_PASSWORD("select ud from USER_DETAIL ud where ud.password ='%s'"),
    USER_LIST("select uv from USER_VIEW uv"),
    USER_TYPE_LIST("select list from USER_TYPE list"),
    INSERT_USER("insert user"),
    UPDATE_USER("update user"),
    DELETE_USER("delete user"),

            // ARTIQULE_GROUP
    AR_GROUP_LIST("select list from AR_GROUP list"),

                    // MENU
    MAIN_MENU("select m from MENU m where m.id > 0");


    private String pattern;

    Type(String pattern){this.pattern = pattern;}
    public String getPattern(){return pattern;}
}
