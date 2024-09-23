package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data

@Entity
@Table(name = "user_builds")
public class UserBuild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_build_id")
    private Long userBuildId;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "usage_type", nullable = false, length = 50)
    private String usageType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "cpu_id", nullable = false)
    private Processor cpu;

    @ManyToOne
    @JoinColumn(name = "gpu_id", nullable = false)
    private GraphicsCard gpu;

    @ManyToOne
    @JoinColumn(name = "mobo_id", nullable = false)
    private Motherboard motherboard;

    @ManyToOne
    @JoinColumn(name = "ram_id", nullable = false)
    private RamModule ram;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "power_supply_id", nullable = false)
    private PowerSupply powerSupply;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false)
    private Case pcCase;

    @ManyToOne
    @JoinColumn(name = "cpu_cooler_id", nullable = false)
    private CpuCooler cpuCooler;

}
