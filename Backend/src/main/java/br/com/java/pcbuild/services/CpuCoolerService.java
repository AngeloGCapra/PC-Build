package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.CpuCooler;
import br.com.java.pcbuild.repositories.CpuCoolerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CpuCoolerService {

    private final CpuCoolerRepository cpuCoolerRepository;

    public List<CpuCooler> findAllCpuCoolers() {
        return cpuCoolerRepository.findAll();
    }

    public Optional<CpuCooler> findCpuCoolerById(Integer cpuCoolerId) {
        return cpuCoolerRepository.findById(cpuCoolerId);
    }

    public CpuCooler createCpuCooler(CpuCooler cpuCooler) {
        return cpuCoolerRepository.save(cpuCooler);
    }

    public CpuCooler updateCpuCooler(Integer cpuCoolerId, CpuCooler updatedCpuCooler) {
        return cpuCoolerRepository.findById(cpuCoolerId)
                .map(cpuCooler -> {
                    cpuCooler.setName(updatedCpuCooler.getName());
                    cpuCooler.setManufacturer(updatedCpuCooler.getManufacturer());
                    cpuCooler.setFanRpm(updatedCpuCooler.getFanRpm());
                    cpuCooler.setNoiseLevel(updatedCpuCooler.getNoiseLevel());
                    cpuCooler.setRadiatorSize(updatedCpuCooler.getRadiatorSize());
                    cpuCooler.setPrice(updatedCpuCooler.getPrice());
                    cpuCooler.setComponentRanking(updatedCpuCooler.getComponentRanking());

                    return cpuCoolerRepository.save(cpuCooler);
                }).orElseThrow(() -> new RuntimeException("CpuCooler not found"));
    }

    public void deleteCpuCooler(Integer cpuCoolerId) {
        cpuCoolerRepository.deleteById(cpuCoolerId);
    }

}
