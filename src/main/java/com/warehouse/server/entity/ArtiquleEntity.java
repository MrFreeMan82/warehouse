package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.Artiqule;
import com.warehouse.shared.dto.Price;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Дима on 26.05.2017.
 *
 */
@Entity(name = "ARTIQULE")
@DTOLocator(value = Artiqule.class)
public final class ArtiquleEntity extends CustomEntity{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @GenericGenerator(name = "gen", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {@Parameter(name = "sequence_name", value = "GEN_ARTIQULE_ID")
            })
    public Long id;

    @NotNull
    @Column(name = "GROUP_ID")
    public Long groupId;

    @NotNull
    @Column(name = "METRIC_ID")
    public Long metricId;

    @NotNull
    @Column(name = "OPERATOR_ID")
    public Long operator;

    @NotNull
    @Column(name = "NAME")
    public String name;

    @NotNull
    @Column(name = "SHORT_NAME")
    public String shortName;

    @NotNull
    @Column(name = "DELETED")
    public boolean deleted;

    @ManyToOne
    @JoinColumn(name = "METRIC_ID", insertable = false, updatable = false)
    public MetricEntity metric;

    @OneToMany(mappedBy = "artiquleEntity", fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<PriceEntity> prices;
}
