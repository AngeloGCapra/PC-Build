package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.PowerSupply;
import br.com.java.pcbuild.repositories.PowerSupplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PowerSupplyService {

    private final ModelMapper modelMapper;
    private final PowerSupplyRepository powerSupplyRepository;

    public List<PowerSupply> findAllPowerSupplies() {
        return powerSupplyRepository.findAllByOrderByPriceAsc();
    }

    public Optional<PowerSupply> findPowerSupplyById(Long powerSupplyId) {
        return powerSupplyRepository.findById(powerSupplyId);
    }

    public PowerSupply createPowerSupply(PowerSupply powerSupply) {
        return powerSupplyRepository.save(powerSupply);
    }

    public PowerSupply updatePowerSupply(Long powerSupplyId, PowerSupply updatedPowerSupply) {
        return powerSupplyRepository.findById(powerSupplyId)
                .map(powerSupplyEntity -> {
                    modelMapper.map(updatedPowerSupply, powerSupplyEntity);
                    return powerSupplyRepository.save(powerSupplyEntity);
                }).orElseThrow(() -> new RuntimeException("Power Supply not found"));
    }

    public void deletePowerSupply(Long powerSupplyId) {
        powerSupplyRepository.deleteById(powerSupplyId);
    }

}
