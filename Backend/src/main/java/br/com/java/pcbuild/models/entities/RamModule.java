package br.com.java.pcbuild.models.entities;

import br.com.java.pcbuild.Utils.Component;
import br.com.java.pcbuild.enums.RamModuleManufacturerEnum;
import br.com.java.pcbuild.enums.RamModuleModulesEnum;
import br.com.java.pcbuild.enums.RamModuleTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "ram_modules")
public class RamModule implements Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ram_id")
    private Long ramId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false, length = 50)
    private RamModuleManufacturerEnum manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private RamModuleTypeEnum type;

    @Column(name = "speed", nullable = false)
    private Integer speed;

    @Column(name = "cas_latency")
    private Integer casLatency;

    @Enumerated(EnumType.STRING)
    @Column(name = "modules", nullable = false, length = 50)
    private RamModuleModulesEnum modules;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "component_ranking", precision = 5, scale = 2)
    private BigDecimal componentRanking;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @Override
    public BigDecimal getPrice() {
        return price;
    }

}
