package com.warehouse.shared.dto;

import com.warehouse.server.entity.CustomEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Дима on 18.05.2017.
 *
 */

public final class HashedDTO extends DTO implements Serializable
{
    private HashMap<Long, DTO> index = new HashMap<>();

    public HashedDTO(){}
    public  List<? extends DTO> getList(){return new ArrayList<>(index.values());}

    public <T extends DTO> T get(Long id){return (T) index.get(id);}

    public <T extends DTO>void addCopy(CustomEntity entity, T dto){

            dto.copyEntity(entity);
            index.put(dto.getId(), dto);
    }
}
