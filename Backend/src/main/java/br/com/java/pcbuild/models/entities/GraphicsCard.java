package br.com.java.pcbuild.models.entities;

import br.com.java.pcbuild.enums.GpuBrandEnum;
import br.com.java.pcbuild.enums.GpuChipsetEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "graphics_cards")
public class GraphicsCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpu_id")
    private Long gpuId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "chipset", nullable = false, length = 50)
    private GpuChipsetEnum chipset;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false, length = 50)
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false, length = 50)
    private GpuBrandEnum brand;

    @Column(name = "base_clock", nullable = false)
    private Integer baseClock;

    @Column(name = "boost_clock", nullable = false)
    private Integer boostClock;

    @Column(name = "memory_size", nullable = false)
    private Integer memorySize;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "component_ranking", precision = 5, scale = 2)
    private BigDecimal componentRanking;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "gpu_performance_id")
    private GpuPerformance gpuPerformance;

}
