package com.warehouse.shared.request;

/**
 * Created by Дима on 19.05.2017.
 *
 */

public enum SQL {
    LOGIN(""),

    INSERT("insert"),
    UPDATE("update"),
    DELETE("delete"),
    REFRESH("refresh"),       // load one record by ID

                // USER
    USER_BY_PASSWORD("select ud from USER_DETAIL ud where ud.password ='%s'"),
    USER_LIST("select uv from USER_VIEW uv"),
    USER_TYPE_LIST("select list from USER_TYPE list"),

            // ARTIQULE_GROUP
    GROUPS_WITH_ARTIQULES("from AR_GROUP list left outer join fetch list.artiqules artiqule left outer join fetch artiqule.metric where list.id > 0 and not list.deleted"),
    GROUPS("from AR_GROUP list where list.id > 0 and list.deleted is false"),
    ARTIQULES_BY_GROUP("from ARTIQULE list left join fetch list.metric left outer join fetch list.prices where list.groupId = %d"),
    METRIC_LIST("from METRIC"),
    PRICE_TYPE_LIST("from PRICE_TYPE"),
    DELETE_GROUP("DELETE_GROUP"),
                    // MENU
    MAIN_MENU("select m from MENU m where m.id > 0");


    private String pattern;

    SQL(String pattern){this.pattern = pattern;}
    public String getPattern(){return pattern;}
}
