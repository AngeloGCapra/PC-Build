package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.GpuPerformance;
import br.com.java.pcbuild.services.GpuPerformanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gpu-performance")
public class GpuPerformanceController {

    private final GpuPerformanceService gpuPerformanceService;

    @GetMapping
    public ResponseEntity<List<GpuPerformance>> getAllGpuPerformances() {
        List<GpuPerformance> gpuPerformances = gpuPerformanceService.findAllGpuPerformances();
        return ResponseEntity.ok(gpuPerformances);
    }

    @GetMapping("/{gpuPerformanceId}")
    public ResponseEntity<Optional<GpuPerformance>> getGpuPerformanceById(@PathVariable("gpuPerformanceId") Integer gpuPerformanceId) {
        Optional<GpuPerformance> gpuPerformance = gpuPerformanceService.findGpuPerformanceById(gpuPerformanceId);
        return ResponseEntity.ok(gpuPerformance);
    }

    @PostMapping
    public ResponseEntity<GpuPerformance> createGpuPerformance(@RequestBody GpuPerformance gpuPerformance) {
        GpuPerformance newGpuPerformance = gpuPerformanceService.createGpuPerformance(gpuPerformance);
        return new ResponseEntity<>(newGpuPerformance, HttpStatus.CREATED);
    }

    @PutMapping("/{gpuPerformanceId}")
    public ResponseEntity<GpuPerformance> updateGpuPerformance(@PathVariable("gpuPerformanceId") Integer gpuPerformanceId,
                                                               @RequestBody GpuPerformance gpuPerformance) {
        GpuPerformance updatedGpuPerformance = gpuPerformanceService.updateGpuPerformance(gpuPerformanceId, gpuPerformance);
        return ResponseEntity.ok(updatedGpuPerformance);
    }

    @DeleteMapping("/{gpuPerformanceId}")
    public ResponseEntity<Void> deleteGpuPerformance(@PathVariable("gpuPerformanceId") Integer gpuPerformanceId) {
        gpuPerformanceService.deleteGpuPerformance(gpuPerformanceId);
        return ResponseEntity.noContent().build();
    }

}
