package com.warehouse.server.entity;

import com.warehouse.server.DTOLocator;
import com.warehouse.shared.dto.Artiqule;
import com.warehouse.shared.dto.Price;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
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
    @Column(name = "STATUS_ID")
    public Long statusId;

    @NotNull
    @Column(name = "OPERATOR_ID")
    public Long operator;

    @NotNull
    @Column(name = "NAME")
    public String name;

    @NotNull
    @Column(name = "SHORT_NAME")
    public String shortName;

    @ManyToOne
    @JoinColumn(name = "METRIC_ID", insertable = false, updatable = false)
    public MetricEntity metric;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "AR_ID", referencedColumnName = "id")
    public List<PriceEntity> prices;

   /* @ManyToOne
    @JoinColumn(name = "GROUP_ID", insertable = false, updatable = false)
    public GroupEntity artGroup;
    */
}
