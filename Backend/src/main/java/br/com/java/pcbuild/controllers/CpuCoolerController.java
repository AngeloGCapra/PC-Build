package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.CpuCooler;
import br.com.java.pcbuild.services.CpuCoolerService;
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
@RequestMapping("/api/cpu-cooler")
public class CpuCoolerController {

    private final CpuCoolerService cpuCoolerService;

    @GetMapping(value = "/getAllCpuCoolers")
    public ResponseEntity<List<CpuCooler>> getAllCpuCoolers() {
        List<CpuCooler> cpuCoolers = cpuCoolerService.findAllCpuCoolers();
        return ResponseEntity.ok(cpuCoolers);
    }

    @GetMapping("/getCpuCoolerById/{cpuCoolerId}")
    public ResponseEntity<Optional<CpuCooler>> getCpuCoolerById(@PathVariable("cpuCoolerId") Integer cpuCoolerId) {
        Optional<CpuCooler> cpuCooler = cpuCoolerService.findCpuCoolerById(cpuCoolerId);
        return ResponseEntity.ok(cpuCooler);
    }

    @PostMapping(value = "/createCpuCooler")
    public ResponseEntity<CpuCooler> createCpuCooler(@RequestBody CpuCooler cpuCooler) {
        CpuCooler newCpuCooler = cpuCoolerService.createCpuCooler(cpuCooler);
        return new ResponseEntity<>(newCpuCooler, HttpStatus.CREATED);
    }

    @PutMapping("/updateCpuCooler/{cpuCoolerId}")
    public ResponseEntity<CpuCooler> updateCpuCooler(@PathVariable("cpuCoolerId") Integer cpuCoolerId,
                                                     @RequestBody CpuCooler cpuCooler) {
        CpuCooler updatedCpuCooler = cpuCoolerService.updateCpuCooler(cpuCoolerId, cpuCooler);
        return ResponseEntity.ok(updatedCpuCooler);
    }

    @DeleteMapping("/deleteCpuCooler/{cpuCoolerId}")
    public ResponseEntity<Void> deleteCpuCooler(@PathVariable("cpuCoolerId") Integer cpuCoolerId) {
        cpuCoolerService.deleteCpuCooler(cpuCoolerId);
        return ResponseEntity.noContent().build();
    }

}
