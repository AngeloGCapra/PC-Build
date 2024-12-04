package br.com.java.pcbuild.models.entities;

import br.com.java.pcbuild.utils.Component;
import br.com.java.pcbuild.enums.StorageFormFactorEnum;
import br.com.java.pcbuild.enums.StorageInterfaceEnum;
import br.com.java.pcbuild.enums.StorageManufacturerEnum;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data

@Entity
@Table(name = "storages")
public class Storage implements Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storage_id")
    private Long storageId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false, length = 50)
    private StorageManufacturerEnum manufacturer;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "cache")
    private Integer cache;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false, length = 50)
    private StorageFormFactorEnum formFactor;

    @Enumerated(EnumType.STRING)
    @Column(name = "storage_interface", nullable = false, length = 50)
    private StorageInterfaceEnum storageInterface;

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
