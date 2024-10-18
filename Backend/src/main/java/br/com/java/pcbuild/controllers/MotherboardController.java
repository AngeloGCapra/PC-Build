package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.Motherboard;
import br.com.java.pcbuild.services.MotherboardService;
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
@RequestMapping("/api/motherboard")
public class MotherboardController {

    private final MotherboardService motherboardService;

    @GetMapping
    public ResponseEntity<List<Motherboard>> getAllMotherboards() {
        List<Motherboard> motherboards = motherboardService.findAllMotherboards();
        return ResponseEntity.ok(motherboards);
    }

    @GetMapping("/{moboId}")
    public ResponseEntity<Optional<Motherboard>> getMotherboardById(@PathVariable("moboId") Integer moboId) {
        Optional<Motherboard> motherboard = motherboardService.findMotherboardById(moboId);
        return ResponseEntity.ok(motherboard);
    }

    @PostMapping
    public ResponseEntity<Motherboard> createMotherboard(@RequestBody Motherboard motherboard) {
        Motherboard newMotherboard = motherboardService.createMotherboard(motherboard);
        return new ResponseEntity<>(newMotherboard, HttpStatus.CREATED);
    }

    @PutMapping("/{moboId}")
    public ResponseEntity<Motherboard> updateMotherboard(@PathVariable("moboId") Integer moboId,
                                                         @RequestBody Motherboard motherboard) {
        Motherboard updatedMotherboard = motherboardService.updateMotherboard(moboId, motherboard);
        return ResponseEntity.ok(updatedMotherboard);
    }

    @DeleteMapping("/{moboId}")
    public ResponseEntity<Void> deleteMotherboard(@PathVariable("moboId") Integer moboId) {
        motherboardService.deleteMotherboard(moboId);
        return ResponseEntity.noContent().build();
    }

}
