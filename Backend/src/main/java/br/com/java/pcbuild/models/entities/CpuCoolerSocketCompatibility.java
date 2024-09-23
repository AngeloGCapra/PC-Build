package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "cpu_cooler_socket_compatibility")
public class CpuCoolerSocketCompatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "csc_id")
    private Long cscId;

    @Column(name = "compatibility", nullable = false)
    private Boolean compatibility;

    @ManyToOne
    @JoinColumn(name = "cpu_cooler_id", nullable = false)
    private CpuCooler cpuCooler;

    @ManyToOne
    @JoinColumn(name = "socket_id", nullable = false)
    private Socket socket;

}
