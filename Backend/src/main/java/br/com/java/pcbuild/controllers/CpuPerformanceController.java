package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.CpuPerformance;
import br.com.java.pcbuild.services.CpuPerformanceService;
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
@RequestMapping("/api/cpu-performance")
public class CpuPerformanceController {

    private final CpuPerformanceService cpuPerformanceService;

    @GetMapping("/getAllCpuPerformances")
    public ResponseEntity<List<CpuPerformance>> getAllCpuPerformances() {
        List<CpuPerformance> cpuPerformances = cpuPerformanceService.findAllCpuPerformances();
        return ResponseEntity.ok(cpuPerformances);
    }

    @GetMapping("/getCpuPerformanceById/{cpuPerformanceId}")
    public ResponseEntity<Optional<CpuPerformance>> getCpuPerformanceById(@PathVariable("cpuPerformanceId") Long cpuPerformanceId) {
        Optional<CpuPerformance> cpuPerformance = cpuPerformanceService.findCpuPerformanceById(cpuPerformanceId);
        return ResponseEntity.ok(cpuPerformance);
    }

    @PostMapping("/createCpuPerformance")
    public ResponseEntity<CpuPerformance> createCpuPerformance(@RequestBody CpuPerformance cpuPerformance) {
        CpuPerformance newCpuPerformance = cpuPerformanceService.createCpuPerformance(cpuPerformance);
        return new ResponseEntity<>(newCpuPerformance, HttpStatus.CREATED);
    }

    @PutMapping("/updateCpuPerformance/{cpuPerformanceId}")
    public ResponseEntity<CpuPerformance> updateCpuPerformance(@PathVariable("cpuPerformanceId") Long cpuPerformanceId,
                                                               @RequestBody CpuPerformance cpuPerformance) {
        CpuPerformance updatedCpuPerformance = cpuPerformanceService.updateCpuPerformance(cpuPerformanceId, cpuPerformance);
        return ResponseEntity.ok(updatedCpuPerformance);
    }

    @DeleteMapping("/deleteCpuPerformance/{cpuPerformanceId}")
    public ResponseEntity<Void> deleteCpuPerformance(@PathVariable("cpuPerformanceId") Long cpuPerformanceId) {
        cpuPerformanceService.deleteCpuPerformance(cpuPerformanceId);
        return ResponseEntity.noContent().build();
    }

}
