package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.GpuPerformance;
import br.com.java.pcbuild.repositories.GpuPerformanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GpuPerformanceService {

    private final ModelMapper modelMapper;
    private final GpuPerformanceRepository gpuPerformanceRepository;

    public List<GpuPerformance> findAllGpuPerformances() {
        return gpuPerformanceRepository.findAll();
    }

    public Optional<GpuPerformance> findGpuPerformanceById(Integer gpuPerformanceId) {
        return gpuPerformanceRepository.findById(gpuPerformanceId);
    }

    public GpuPerformance createGpuPerformance(GpuPerformance gpuPerformance) {
        return gpuPerformanceRepository.save(gpuPerformance);
    }

    public GpuPerformance updateGpuPerformance(Integer gpuPerformanceId, GpuPerformance updatedGpuPerformance) {
        return gpuPerformanceRepository.findById(gpuPerformanceId)
                .map(gpuPerformanceEntity -> {
                    modelMapper.map(updatedGpuPerformance, gpuPerformanceEntity);
                    return gpuPerformanceRepository.save(gpuPerformanceEntity);
                }).orElseThrow(() -> new RuntimeException("GpuPerformance not found"));
    }

    public void deleteGpuPerformance(Integer gpuPerformanceId) {
        gpuPerformanceRepository.deleteById(gpuPerformanceId);
    }

}
