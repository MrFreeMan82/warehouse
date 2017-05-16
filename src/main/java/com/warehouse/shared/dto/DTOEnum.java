package com.warehouse.shared.dto;

import java.util.HashMap;

/**
 * Created by Дима on 16.05.2017.
 *
 */

public enum DTOEnum {
    USER_TYPE("com.warehouse.shared.dto.UserType"),
    USER_DETAIL("com.warehouse.shared.dto.UserDetail");

    private String string;
    private static final HashMap<String, DTO> dtoHashMap = new HashMap<>();
    static {
        dtoHashMap.put(USER_TYPE.toString(), new UserType());
        dtoHashMap.put(USER_DETAIL.toString(), new UserDetail());
    }

    DTOEnum(String string){
        this.string = string;
    }

    @Override
    public String toString()
    {
        return string;
    }

    public static void updateDTO(DTOEnum dtoEnum, DTO dto)
    {
        dtoHashMap.replace(dtoEnum.toString(), dto);
    }

    public static DTO getDTO(String string)
    {
        return dtoHashMap.get(string);
    }
}
