package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.CpuCoolerSocketCompatibility;
import br.com.java.pcbuild.services.CpuCoolerSocketCompatibilityService;
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
@RequestMapping("/api/cpu-cooler-socket-compatibility")
public class CpuCoolerSocketCompatibilityController {

    private final CpuCoolerSocketCompatibilityService compatibilityService;

    @GetMapping(value = "/getAllCompatibilities")
    public ResponseEntity<List<CpuCoolerSocketCompatibility>> getAllCompatibilities() {
        List<CpuCoolerSocketCompatibility> compatibilities = compatibilityService.findAllCompatibilities();
        return ResponseEntity.ok(compatibilities);
    }

    @GetMapping("/getCompatibilityById/{cscId}")
    public ResponseEntity<Optional<CpuCoolerSocketCompatibility>> getCompatibilityById(@PathVariable("cscId") Long cscId) {
        Optional<CpuCoolerSocketCompatibility> compatibility = compatibilityService.findCompatibilityById(cscId);
        return ResponseEntity.ok(compatibility);
    }

    @PostMapping(value = "/createCompatibility")
    public ResponseEntity<CpuCoolerSocketCompatibility> createCompatibility(@RequestBody CpuCoolerSocketCompatibility compatibility) {
        CpuCoolerSocketCompatibility newCompatibility = compatibilityService.createCompatibility(compatibility);
        return new ResponseEntity<>(newCompatibility, HttpStatus.CREATED);
    }

    @PutMapping("/updateCompatibility/{cscId}")
    public ResponseEntity<CpuCoolerSocketCompatibility> updateCompatibility(@PathVariable("cscId") Long cscId,
                                                                            @RequestBody CpuCoolerSocketCompatibility compatibility) {
        CpuCoolerSocketCompatibility updatedCompatibility = compatibilityService.updateCompatibility(cscId, compatibility);
        return ResponseEntity.ok(updatedCompatibility);
    }

    @DeleteMapping("/deleteCompatibility/{cscId}")
    public ResponseEntity<Void> deleteCompatibility(@PathVariable("cscId") Long cscId) {
        compatibilityService.deleteCompatibility(cscId);
        return ResponseEntity.noContent().build();
    }

}
