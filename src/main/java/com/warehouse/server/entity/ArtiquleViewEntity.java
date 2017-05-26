package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.ArtiquleView;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@Entity(name = "ARTIQULE_VIEW")
@DTOLocator(value = ArtiquleView.class)
public final class ArtiquleViewEntity extends CustomEntity{
    @Id
    public Long id;
    public Long group_id;
    public Long parent_group_id;
    public Long metric_id;
    public Long status_id;
    public String group_name;
    public String name;
    public String short_name;
    public String metric_name;
    public Double price1;
    public Double price2;
}
