package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.PowerSupply;
import br.com.java.pcbuild.services.PowerSupplyService;
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
@RequestMapping("/api/power-supply")
public class PowerSupplyController {

    private final PowerSupplyService powerSupplyService;

    @GetMapping
    public ResponseEntity<List<PowerSupply>> getAllPowerSupplies() {
        List<PowerSupply> powerSupplies = powerSupplyService.findAllPowerSupplies();
        return ResponseEntity.ok(powerSupplies);
    }

    @GetMapping("/{powerSupplyId}")
    public ResponseEntity<Optional<PowerSupply>> getPowerSupplyById(@PathVariable("powerSupplyId") Integer powerSupplyId) {
        Optional<PowerSupply> powerSupply = powerSupplyService.findPowerSupplyById(powerSupplyId);
        return ResponseEntity.ok(powerSupply);
    }

    @PostMapping
    public ResponseEntity<PowerSupply> createPowerSupply(@RequestBody PowerSupply powerSupply) {
        PowerSupply newPowerSupply = powerSupplyService.createPowerSupply(powerSupply);
        return new ResponseEntity<>(newPowerSupply, HttpStatus.CREATED);
    }

    @PutMapping("/{powerSupplyId}")
    public ResponseEntity<PowerSupply> updatePowerSupply(@PathVariable("powerSupplyId") Integer powerSupplyId,
                                                         @RequestBody PowerSupply powerSupply) {
        PowerSupply updatedPowerSupply = powerSupplyService.updatePowerSupply(powerSupplyId, powerSupply);
        return ResponseEntity.ok(updatedPowerSupply);
    }

    @DeleteMapping("/{powerSupplyId}")
    public ResponseEntity<Void> deletePowerSupply(@PathVariable("powerSupplyId") Integer powerSupplyId) {
        powerSupplyService.deletePowerSupply(powerSupplyId);
        return ResponseEntity.noContent().build();
    }

}
