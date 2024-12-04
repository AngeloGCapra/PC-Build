package br.com.java.pcbuild.utils;

import br.com.java.pcbuild.models.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildNode {

    private Processor cpu;
    private GraphicsCard gpu;
    private Motherboard motherboard;
    private RamModule ram;
    private CpuCooler cpuCooler;
    private PowerSupply powerSupply;
    private Storage storage;
    private Case pcCase;
    private BigDecimal remainingBudget;
    private BigDecimal totalCost;

    public BuildNode(BigDecimal budget) {
        this.remainingBudget = budget;
        this.totalCost = BigDecimal.ZERO;
    }

    public boolean isComplete() {
        return cpu != null && gpu != null && motherboard != null && ram != null &&
                cpuCooler != null && powerSupply != null && storage != null && pcCase != null;
    }

}
