package br.com.java.pcbuild.models.entities;

import br.com.java.pcbuild.utils.Component;
import br.com.java.pcbuild.enums.MotherboardChipsetEnum;
import br.com.java.pcbuild.enums.MotherboardFormFactorEnum;
import br.com.java.pcbuild.enums.MotherboardManufacturerEnum;
import br.com.java.pcbuild.enums.RamModuleTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data

@Entity
@Table(name = "motherboards")
public class Motherboard implements Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mobo_id")
    private Long moboId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "manufacturer", nullable = false, length = 50)
    private MotherboardManufacturerEnum manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "chipset", nullable = false, length = 50)
    private MotherboardChipsetEnum chipset;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false, length = 50)
    private MotherboardFormFactorEnum formFactor;

    @Column(name = "ram_slots", nullable = false)
    private Integer ramSlots;

    @Column(name = "memory_max", nullable = false)
    private Integer memoryMax;

    @Column(name = "pcie_slots", nullable = false)
    private Integer pcieSlots;

    @Enumerated(EnumType.STRING)
    @Column(name = "supported_ddr", nullable = false)
    private RamModuleTypeEnum supportedDdr;

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

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    // Getter que converte para uma lista de enums
//    public List<RamModuleTypeEnum> getSupportedDdr() {
//        if (supportedDdr != null && !supportedDdr.isEmpty()) {
//            return Arrays.stream(supportedDdr.split(","))
//                    .map(RamModuleTypeEnum::valueOf)
//                    .collect(Collectors.toList());
//        }
//
//        return new ArrayList<>();
//    }

    // Setter que converte uma lista de enums para string separada por vírgulas
//    public void setSupportedDdr(List<RamModuleTypeEnum> ddrTypes) {
//        if (ddrTypes != null && !ddrTypes.isEmpty()) {
//            this.supportedDdr = ddrTypes.stream()
//                    .map(Enum::name)
//                    .collect(Collectors.joining(","));
//        } else {
//            this.supportedDdr = null;
//        }
//    }

}
