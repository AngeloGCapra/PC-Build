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

    @Column(name = "game_performance", nullable = false)
    private Integer gamePerformance;

    @Column(name = "single_thread_performance", nullable = false)
    private Integer singleThreadPerformance;

    @Column(name = "multi_thread_performance", nullable = false)
    private Integer multiThreadPerformance;

    @ManyToOne
    @JoinColumn(name = "cpu_id", nullable = false)
    private Processor cpu;

}
