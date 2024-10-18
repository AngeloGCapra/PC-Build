package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.PowerSupply;
import br.com.java.pcbuild.repositories.PowerSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PowerSupplyService {

    private final PowerSupplyRepository powerSupplyRepository;

    public List<PowerSupply> findAllPowerSupplies() {
        return powerSupplyRepository.findAll();
    }

    public Optional<PowerSupply> findPowerSupplyById(Integer powerSupplyId) {
        return powerSupplyRepository.findById(powerSupplyId);
    }

    public PowerSupply createPowerSupply(PowerSupply powerSupply) {
        return powerSupplyRepository.save(powerSupply);
    }

    public PowerSupply updatePowerSupply(Integer powerSupplyId, PowerSupply updatedPowerSupply) {
        return powerSupplyRepository.findById(powerSupplyId)
                .map(powerSupply -> {
                    powerSupply.setName(updatedPowerSupply.getName());
                    powerSupply.setManufacturer(updatedPowerSupply.getManufacturer());
                    powerSupply.setType(updatedPowerSupply.getType());
                    powerSupply.setEfficiencyRating(updatedPowerSupply.getEfficiencyRating());
                    powerSupply.setWattage(updatedPowerSupply.getWattage());
                    powerSupply.setModular(updatedPowerSupply.isModular());
                    powerSupply.setPrice(updatedPowerSupply.getPrice());
                    powerSupply.setComponentRanking(updatedPowerSupply.getComponentRanking());

                    return powerSupplyRepository.save(powerSupply);
                }).orElseThrow(() -> new RuntimeException("Power Supply not found"));
    }

    public void deletePowerSupply(Integer powerSupplyId) {
        powerSupplyRepository.deleteById(powerSupplyId);
    }

}
