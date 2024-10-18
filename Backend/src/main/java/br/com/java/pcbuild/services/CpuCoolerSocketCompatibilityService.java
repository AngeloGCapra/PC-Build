package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.CpuCoolerSocketCompatibility;
import br.com.java.pcbuild.repositories.CpuCoolerSocketCompatibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CpuCoolerSocketCompatibilityService {

    private final CpuCoolerSocketCompatibilityRepository compatibilityRepository;

    public List<CpuCoolerSocketCompatibility> findAllCompatibilities() {
        return compatibilityRepository.findAll();
    }

    public Optional<CpuCoolerSocketCompatibility> findCompatibilityById(Integer cscId) {
        return compatibilityRepository.findById(cscId);
    }

    public CpuCoolerSocketCompatibility createCompatibility(CpuCoolerSocketCompatibility compatibility) {
        return compatibilityRepository.save(compatibility);
    }

    public CpuCoolerSocketCompatibility updateCompatibility(Integer cscId, CpuCoolerSocketCompatibility updatedCompatibility) {
        return compatibilityRepository.findById(cscId)
                .map(existingCompatibility -> {
                    existingCompatibility.setCompatibility(updatedCompatibility.getCompatibility());
                    existingCompatibility.setCpuCooler(updatedCompatibility.getCpuCooler());
                    existingCompatibility.setSocket(updatedCompatibility.getSocket());

                    return compatibilityRepository.save(existingCompatibility);
                }).orElseThrow(() -> new RuntimeException("Compatibility not found"));
    }

    public void deleteCompatibility(Integer cscId) {
        compatibilityRepository.deleteById(cscId);
    }

}
