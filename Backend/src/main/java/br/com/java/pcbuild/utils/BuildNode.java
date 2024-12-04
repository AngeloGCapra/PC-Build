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

    // Construtor inicial
    public BuildNode(BigDecimal budget) {
        this.remainingBudget = budget;
        this.totalCost = BigDecimal.ZERO;
    }

    // Construtor para expansão de nós
    public BuildNode(BuildNode parent, Component newComponent) {
        this.cpu = parent.cpu;
        this.gpu = parent.gpu;
        this.motherboard = parent.motherboard;
        this.ram = parent.ram;
        this.cpuCooler = parent.cpuCooler;
        this.powerSupply = parent.powerSupply;
        this.storage = parent.storage;
        this.pcCase = parent.pcCase;
        this.remainingBudget = parent.remainingBudget.subtract(newComponent.getPrice());
        this.totalCost = parent.totalCost.add(newComponent.getPrice());

        // Atualiza o componente apropriado
        if (newComponent instanceof Processor) this.cpu = (Processor) newComponent;
        if (newComponent instanceof GraphicsCard) this.gpu = (GraphicsCard) newComponent;
        if (newComponent instanceof Motherboard) this.motherboard = (Motherboard) newComponent;
        if (newComponent instanceof RamModule) this.ram = (RamModule) newComponent;
        if (newComponent instanceof CpuCooler) this.cpuCooler = (CpuCooler) newComponent;
        if (newComponent instanceof PowerSupply) this.powerSupply = (PowerSupply) newComponent;
        if (newComponent instanceof Storage) this.storage = (Storage) newComponent;
        if (newComponent instanceof Case) this.pcCase = (Case) newComponent;
    }

    public boolean isComplete() {
        return cpu != null && gpu != null && motherboard != null && ram != null &&
                cpuCooler != null && powerSupply != null && storage != null && pcCase != null;
    }

}
