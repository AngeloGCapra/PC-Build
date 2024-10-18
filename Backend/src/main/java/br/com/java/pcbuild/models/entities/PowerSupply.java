package br.com.java.pcbuild.models.entities;

import br.com.java.pcbuild.enums.PowerSupplyEfficiencyRatingEnum;
import br.com.java.pcbuild.enums.PowerSupplyManufacturerEnum;
import br.com.java.pcbuild.enums.PowerSupplyTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "power_supplies")
public class PowerSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "power_supply_id")
    private Long powerSupplyId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false, length = 50)
    private PowerSupplyManufacturerEnum manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private PowerSupplyTypeEnum type;

    @Enumerated(EnumType.STRING)
    @Column(name = "efficiency_rating", nullable = false, length = 20)
    private PowerSupplyEfficiencyRatingEnum efficiencyRating;

    @Column(name = "wattage", nullable = false)
    private Integer wattage;

    @Column(name = "modular", nullable = false)
    private boolean modular;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "component_ranking", precision = 5, scale = 2)
    private BigDecimal componentRanking;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}
