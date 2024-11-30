package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.GraphicsCard;
import br.com.java.pcbuild.services.GraphicsCardService;
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
@RequestMapping("/api/graphics-card")
public class GraphicsCardController {

    private final GraphicsCardService graphicsCardService;

    @GetMapping("/getAllGraphicsCards")
    public ResponseEntity<List<GraphicsCard>> getAllGraphicsCards() {
        List<GraphicsCard> graphicsCards = graphicsCardService.findAllGraphicsCards();
        return ResponseEntity.ok(graphicsCards);
    }

    @GetMapping("/getGraphicsCardById/{gpuId}")
    public ResponseEntity<Optional<GraphicsCard>> getGraphicsCardById(@PathVariable("gpuId") Long gpuId) {
        Optional<GraphicsCard> graphicsCard = graphicsCardService.findGraphicsCardById(gpuId);
        return ResponseEntity.ok(graphicsCard);
    }

    @PostMapping("/createGraphicsCard")
    public ResponseEntity<GraphicsCard> createGraphicsCard(@RequestBody GraphicsCard graphicsCard) {
        GraphicsCard newGraphicsCard = graphicsCardService.createGraphicsCard(graphicsCard);
        return new ResponseEntity<>(newGraphicsCard, HttpStatus.CREATED);
    }

    @PutMapping("/updateGraphicsCard/{gpuId}")
    public ResponseEntity<GraphicsCard> updateGraphicsCard(@PathVariable("gpuId") Long gpuId,
                                                           @RequestBody GraphicsCard graphicsCard) {
        GraphicsCard updatedGraphicsCard = graphicsCardService.updateGraphicsCard(gpuId, graphicsCard);
        return ResponseEntity.ok(updatedGraphicsCard);
    }

    @DeleteMapping("/deleteGraphicsCard/{gpuId}")
    public ResponseEntity<Void> deleteGraphicsCard(@PathVariable("gpuId") Long gpuId) {
        graphicsCardService.deleteGraphicsCard(gpuId);
        return ResponseEntity.noContent().build();
    }

}
