package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "motherboards")
public class Motherboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobo_id")
    private Long moboId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @Column(name = "chipset", nullable = false, length = 50)
    private String chipset;

    @Column(name = "form_factor", nullable = false, length = 50)
    private String formFactor;

    @Column(name = "ram_slots", nullable = false)
    private Integer ramSlots;

    @Column(name = "memory_max", nullable = false)
    private Integer memoryMax;

    @Column(name = "pcie_slots", nullable = false)
    private Integer pcieSlots;

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

}
