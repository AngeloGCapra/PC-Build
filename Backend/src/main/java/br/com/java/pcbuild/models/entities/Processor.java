package br.com.java.pcbuild.models.entities;

import br.com.java.pcbuild.Utils.Component;
import br.com.java.pcbuild.enums.CpuBrandEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "processors") // Colocar indexes
public class Processor implements Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpu_id")
    private Long cpuId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false, length = 50)
    private CpuBrandEnum brand;

    @Column(name = "cores", nullable = false)
    private Integer cores;

    @Column(name = "threads", nullable = false)
    private Integer threads;

    @Column(name = "base_clock", nullable = false, precision = 4, scale = 2)
    private BigDecimal baseClock;

    @Column(name = "turbo_clock", nullable = false, precision = 4, scale = 2)
    private BigDecimal turboClock;

    @Column(name = "tdp", nullable = false)
    private Integer tdp;

    @Column(name = "power_consumption")
    private Integer powerConsumption;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "component_ranking", precision = 5, scale = 2)
    private BigDecimal componentRanking;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "socket_id", nullable = false)
    private Socket socket;

    @Override
    public BigDecimal getPrice() {
        return price;
    }

}
