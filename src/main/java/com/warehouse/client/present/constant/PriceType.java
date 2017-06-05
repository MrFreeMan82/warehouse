package com.warehouse.client.present.constant;

import com.warehouse.client.Warehouse;

/**
 * Created by Дима on 05.06.2017.
 *
 */

public enum PriceType{
    ROZNICA(1L), OPT(2L);

    private Long id;

    PriceType(Long id){this.id = id;}

    public Long Id() {return id;}

    public static String caption(Long type){
        switch (type.intValue()){
            case 1: return Warehouse.i18n.captionPriceRozn();
            case 2: return Warehouse.i18n.captionPriceOpt();
            default: return "N/A";
        }
    }
}