package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.ProcessorSocketCompatibility;
import br.com.java.pcbuild.services.ProcessorSocketCompatibilityService;
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
@RequestMapping("/api/processor-socket-compatibility")
public class ProcessorSocketCompatibilityController {

    private final ProcessorSocketCompatibilityService compatibilityService;

    @GetMapping
    public ResponseEntity<List<ProcessorSocketCompatibility>> getAllCompatibilities() {
        List<ProcessorSocketCompatibility> compatibilities = compatibilityService.findAllCompatibilities();
        return ResponseEntity.ok(compatibilities);
    }

    @GetMapping("/{pscId}")
    public ResponseEntity<Optional<ProcessorSocketCompatibility>> getCompatibilityById(@PathVariable("pscId") Integer pscId) {
        Optional<ProcessorSocketCompatibility> compatibility = compatibilityService.findCompatibilityById(pscId);
        return ResponseEntity.ok(compatibility);
    }

    @PostMapping
    public ResponseEntity<ProcessorSocketCompatibility> createCompatibility(@RequestBody ProcessorSocketCompatibility compatibility) {
        ProcessorSocketCompatibility newCompatibility = compatibilityService.createCompatibility(compatibility);
        return new ResponseEntity<>(newCompatibility, HttpStatus.CREATED);
    }

    @PutMapping("/{pscId}")
    public ResponseEntity<ProcessorSocketCompatibility> updateCompatibility(@PathVariable("pscId") Integer pscId,
                                                                            @RequestBody ProcessorSocketCompatibility compatibility) {
        ProcessorSocketCompatibility updatedCompatibility = compatibilityService.updateCompatibility(pscId, compatibility);
        return ResponseEntity.ok(updatedCompatibility);
    }

    @DeleteMapping("/{pscId}")
    public ResponseEntity<Void> deleteCompatibility(@PathVariable("pscId") Integer pscId) {
        compatibilityService.deleteCompatibility(pscId);
        return ResponseEntity.noContent().build();
    }

}
