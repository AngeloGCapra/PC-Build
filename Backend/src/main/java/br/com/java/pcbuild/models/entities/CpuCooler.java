package br.com.java.pcbuild.models.entities;

import br.com.java.pcbuild.enums.CpuCoolerManufacturerEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "cpu_coolers")
public class CpuCooler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpu_cooler_id")
    private Long cpuCoolerId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false, length = 50)
    private CpuCoolerManufacturerEnum manufacturer;

    @Column(name = "fan_rpm")
    private Integer fanRpm;

    @Column(name = "noise_level", precision = 4, scale = 2)
    private BigDecimal noiseLevel;

    @Column(name = "radiator_size")
    private Integer radiatorSize;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "component_ranking", precision = 5, scale = 2)
    private BigDecimal componentRanking;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}
