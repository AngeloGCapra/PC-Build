package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.Processor;
import br.com.java.pcbuild.services.ProcessorService;
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
@RequestMapping("/api/processor")
public class ProcessorController {

    private final ProcessorService processorService;

    @GetMapping("/getAllProcessors")
    public ResponseEntity<List<Processor>> getAllProcessors() {
        List<Processor> processors = processorService.findAllProcessors();
        return ResponseEntity.ok(processors);
    }

    @GetMapping("/getProcessorById/{processorId}")
    public ResponseEntity<Optional<Processor>> getProcessorById(@PathVariable("processorId") Long processorId) {
        Optional<Processor> processor = processorService.findProcessorById(processorId);
        return ResponseEntity.ok(processor);
    }

    @PostMapping("/createProcessor")
    public ResponseEntity<Processor> createProcessor(@RequestBody Processor processor) {
        Processor newProcessor = processorService.createProcessor(processor);
        return new ResponseEntity<>(newProcessor, HttpStatus.CREATED);
    }

    @PutMapping("/updateProcessor/{processorId}")
    public ResponseEntity<Processor> updateProcessor(@PathVariable("processorId") Long processorId,
                                                     @RequestBody Processor processor) {
        Processor updatedProcessor = processorService.updateProcessor(processorId, processor);
        return ResponseEntity.ok(updatedProcessor);
    }

    @DeleteMapping("/deleteProcessor/{processorId}")
    public ResponseEntity<Void> deleteProcessor(@PathVariable("processorId") Long processorId) {
        processorService.deleteProcessor(processorId);
        return ResponseEntity.noContent().build();
    }

}
