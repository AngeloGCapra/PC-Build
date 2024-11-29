package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.CpuCoolerSocketCompatibility;
import br.com.java.pcbuild.repositories.CpuCoolerSocketCompatibilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CpuCoolerSocketCompatibilityService {

    private final ModelMapper modelMapper;
    private final CpuCoolerSocketCompatibilityRepository compatibilityRepository;

    public List<CpuCoolerSocketCompatibility> findAllCompatibilities() {
        return compatibilityRepository.findAll();
    }

    public Optional<CpuCoolerSocketCompatibility> findCompatibilityById(Long cscId) {
        return compatibilityRepository.findById(cscId);
    }

    public CpuCoolerSocketCompatibility createCompatibility(CpuCoolerSocketCompatibility compatibility) {
        return compatibilityRepository.save(compatibility);
    }

    public CpuCoolerSocketCompatibility updateCompatibility(Long cscId, CpuCoolerSocketCompatibility updatedCompatibility) {
        return compatibilityRepository.findById(cscId)
                .map(existingCompatibilityEntity -> {
                    modelMapper.map(updatedCompatibility, existingCompatibilityEntity);
                    return compatibilityRepository.save(existingCompatibilityEntity);
                }).orElseThrow(() -> new RuntimeException("Compatibility not found"));
    }

    public void deleteCompatibility(Long cscId) {
        compatibilityRepository.deleteById(cscId);
    }

}
