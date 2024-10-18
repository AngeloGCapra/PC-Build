package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.RamModule;
import br.com.java.pcbuild.services.RamModuleService;
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
@RequestMapping("/api/ram-module")
public class RamModuleController {

    private final RamModuleService ramModuleService;

    @GetMapping
    public ResponseEntity<List<RamModule>> getAllRamModules() {
        List<RamModule> ramModules = ramModuleService.findAllRamModules();
        return ResponseEntity.ok(ramModules);
    }

    @GetMapping("/{ramId}")
    public ResponseEntity<Optional<RamModule>> getRamModuleById(@PathVariable("ramId") Integer ramId) {
        Optional<RamModule> ramModule = ramModuleService.findRamModuleById(ramId);
        return ResponseEntity.ok(ramModule);
    }

    @PostMapping
    public ResponseEntity<RamModule> createRamModule(@RequestBody RamModule ramModule) {
        RamModule newRamModule = ramModuleService.createRamModule(ramModule);
        return new ResponseEntity<>(newRamModule, HttpStatus.CREATED);
    }

    @PutMapping("/{ramId}")
    public ResponseEntity<RamModule> updateRamModule(@PathVariable("ramId") Integer ramId,
                                                     @RequestBody RamModule ramModule) {
        RamModule updatedRamModule = ramModuleService.updateRamModule(ramId, ramModule);
        return ResponseEntity.ok(updatedRamModule);
    }

    @DeleteMapping("/{ramId}")
    public ResponseEntity<Void> deleteRamModule(@PathVariable("ramId") Integer ramId) {
        ramModuleService.deleteRamModule(ramId);
        return ResponseEntity.noContent().build();
    }

}
