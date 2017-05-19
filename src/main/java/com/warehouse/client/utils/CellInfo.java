package com.warehouse.client.utils;

import com.warehouse.shared.dto.DTO;

import java.util.List;

/**
 * Created by Дима on 19.05.2017.
 *
 */

public interface CellInfo<Cell extends DTO>
{
    String cellText(Cell cell);
    List<Cell> getChildren(Cell parent);
    boolean isLeaf(Cell cell);

    void onClick(Cell cell);
}
