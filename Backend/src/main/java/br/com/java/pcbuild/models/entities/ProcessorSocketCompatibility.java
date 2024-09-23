package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data

@Entity
@Table(name = "processor_socket_compatibility")
public class ProcessorSocketCompatibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "psc_id")
    private Long pscId;

    @ManyToOne
    @JoinColumn(name = "cpu_id", nullable = false)
    private Processor cpu;

    @ManyToOne
    @JoinColumn(name = "socket_id", nullable = false)
    private Socket socket;

}
