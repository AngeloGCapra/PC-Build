package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.CpuCooler;
import br.com.java.pcbuild.repositories.CpuCoolerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CpuCoolerService {

    private final ModelMapper modelMapper;
    private final CpuCoolerRepository cpuCoolerRepository;

    public List<CpuCooler> findAllCpuCoolers() {
        return cpuCoolerRepository.findAllByOrderByPriceAsc();
    }

    public Optional<CpuCooler> findCpuCoolerById(Long cpuCoolerId) {
        return cpuCoolerRepository.findById(cpuCoolerId);
    }

    public CpuCooler createCpuCooler(CpuCooler cpuCooler) {
        return cpuCoolerRepository.save(cpuCooler);
    }

    public CpuCooler updateCpuCooler(Long cpuCoolerId, CpuCooler updatedCpuCooler) {
        return cpuCoolerRepository.findById(cpuCoolerId)
                .map(cpuCoolerEntity -> {
                    modelMapper.map(updatedCpuCooler, cpuCoolerEntity);
                    return cpuCoolerRepository.save(cpuCoolerEntity);
                }).orElseThrow(() -> new RuntimeException("CpuCooler not found"));
    }

    public void deleteCpuCooler(Long cpuCoolerId) {
        cpuCoolerRepository.deleteById(cpuCoolerId);
    }

}
