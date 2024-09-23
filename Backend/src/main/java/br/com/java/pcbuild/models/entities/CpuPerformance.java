package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "cpu_performance")
public class CpuPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpu_performance_id")
    private Long cpuPerformanceId;

    @Column(name = "relative_performance", nullable = false)
    private Integer relativePerformance;

    @ManyToOne
    @JoinColumn(name = "cpu_id", nullable = false)
    private Processor cpu;

}
