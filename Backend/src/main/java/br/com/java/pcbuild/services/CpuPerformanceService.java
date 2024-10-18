package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.CpuPerformance;
import br.com.java.pcbuild.repositories.CpuPerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CpuPerformanceService {

    private final CpuPerformanceRepository cpuPerformanceRepository;

    public List<CpuPerformance> findAllCpuPerformances() {
        return cpuPerformanceRepository.findAll();
    }

    public Optional<CpuPerformance> findCpuPerformanceById(Integer cpuPerformanceId) {
        return cpuPerformanceRepository.findById(cpuPerformanceId);
    }

    public CpuPerformance createCpuPerformance(CpuPerformance cpuPerformance) {
        return cpuPerformanceRepository.save(cpuPerformance);
    }

    public CpuPerformance updateCpuPerformance(Integer cpuPerformanceId, CpuPerformance updatedCpuPerformance) {
        return cpuPerformanceRepository.findById(cpuPerformanceId)
                .map(cpuPerformance -> {
                    cpuPerformance.setRelativePerformance(updatedCpuPerformance.getRelativePerformance());
                    cpuPerformance.setCpu(updatedCpuPerformance.getCpu());

                    return cpuPerformanceRepository.save(cpuPerformance);
                }).orElseThrow(() -> new RuntimeException("CpuPerformance not found"));
    }

    public void deleteCpuPerformance(Integer cpuPerformanceId) {
        cpuPerformanceRepository.deleteById(cpuPerformanceId);
    }

}
