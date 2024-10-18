package br.com.java.pcbuild.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    private Long caseId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false, length = 50)
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "side_panel", nullable = false, length = 50)
    private String sidePanel;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "component_ranking", precision = 5, scale = 2)
    private BigDecimal componentRanking;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}
