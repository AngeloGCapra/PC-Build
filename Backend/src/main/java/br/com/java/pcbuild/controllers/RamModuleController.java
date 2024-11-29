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

    @GetMapping(value = "/getAllRamModules")
    public ResponseEntity<List<RamModule>> getAllRamModules() {
        List<RamModule> ramModules = ramModuleService.findAllRamModules();
        return ResponseEntity.ok(ramModules);
    }

    @GetMapping("/getRamModuleById/{ramId}")
    public ResponseEntity<Optional<RamModule>> getRamModuleById(@PathVariable("ramId") Long ramId) {
        Optional<RamModule> ramModule = ramModuleService.findRamModuleById(ramId);
        return ResponseEntity.ok(ramModule);
    }

    @PostMapping(value = "/createRamModule")
    public ResponseEntity<RamModule> createRamModule(@RequestBody RamModule ramModule) {
        RamModule newRamModule = ramModuleService.createRamModule(ramModule);
        return new ResponseEntity<>(newRamModule, HttpStatus.CREATED);
    }

    @PutMapping("/updateRamModule/{ramId}")
    public ResponseEntity<RamModule> updateRamModule(@PathVariable("ramId") Long ramId,
                                                     @RequestBody RamModule ramModule) {
        RamModule updatedRamModule = ramModuleService.updateRamModule(ramId, ramModule);
        return ResponseEntity.ok(updatedRamModule);
    }

    @DeleteMapping("/deleteRamModule/{ramId}")
    public ResponseEntity<Void> deleteRamModule(@PathVariable("ramId") Long ramId) {
        ramModuleService.deleteRamModule(ramId);
        return ResponseEntity.noContent().build();
    }

}
