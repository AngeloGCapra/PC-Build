package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.CpuPerformance;
import br.com.java.pcbuild.repositories.CpuPerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CpuPerformanceService {

    private final ModelMapper modelMapper;
    private final CpuPerformanceRepository cpuPerformanceRepository;

    public List<CpuPerformance> findAllCpuPerformances() {
        return cpuPerformanceRepository.findAll();
    }

    public Optional<CpuPerformance> findCpuPerformanceById(Long cpuPerformanceId) {
        return cpuPerformanceRepository.findById(cpuPerformanceId);
    }

    public CpuPerformance createCpuPerformance(CpuPerformance cpuPerformance) {
        return cpuPerformanceRepository.save(cpuPerformance);
    }

    public CpuPerformance updateCpuPerformance(Long cpuPerformanceId, CpuPerformance updatedCpuPerformance) {
        return cpuPerformanceRepository.findById(cpuPerformanceId)
                .map(cpuPerformanceEntity -> {
                    modelMapper.map(updatedCpuPerformance, cpuPerformanceEntity);
                    return cpuPerformanceRepository.save(cpuPerformanceEntity);
                }).orElseThrow(() -> new RuntimeException("CpuPerformance not found"));
    }

    public void deleteCpuPerformance(Long cpuPerformanceId) {
        cpuPerformanceRepository.deleteById(cpuPerformanceId);
    }

}
