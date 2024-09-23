package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "gpu_performance")
public class GpuPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gpu_performance_id")
    private Long gpuPerformanceId;

    @Column(name = "relative_performance", nullable = false)
    private Integer relativePerformance;

    @ManyToOne
    @JoinColumn(name = "gpu_id", nullable = false)
    private GraphicsCard gpu;

}
