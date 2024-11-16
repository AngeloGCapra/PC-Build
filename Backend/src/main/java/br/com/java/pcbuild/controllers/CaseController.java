package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.Case;
import br.com.java.pcbuild.services.CaseService;
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
@RequestMapping("/api/case")
public class CaseController {

    private final CaseService caseService;

    @GetMapping(value = "/getAllCases")
    public ResponseEntity<List<Case>> getAllCases() {
        List<Case> cases = caseService.findAllCases();
        return ResponseEntity.ok(cases);
    }

    @GetMapping("/getCaseById/{caseId}")
    public ResponseEntity<Optional<Case>> getCaseById(@PathVariable("caseId") Integer caseId) {
        Optional<Case> caseEntity = caseService.findCaseById(caseId);
        return ResponseEntity.ok(caseEntity);
    }

    @PostMapping(value = "/createCase")
    public ResponseEntity<Case> createCase(@RequestBody Case caseEntity) {
        Case newCase = caseService.createCase(caseEntity);
        return new ResponseEntity<>(newCase, HttpStatus.CREATED);
    }

    @PutMapping("/updateCase/{caseId}")
    public ResponseEntity<Case> updateCase(@PathVariable("caseId") Integer caseId,
                                           @RequestBody Case caseEntity) {
        Case updatedCase = caseService.updateCase(caseId, caseEntity);
        return ResponseEntity.ok(updatedCase);
    }

    @DeleteMapping("/deleteCase/{caseId}")
    public ResponseEntity<Void> deleteCase(@PathVariable("caseId") Integer caseId) {
        caseService.deleteCase(caseId);
        return ResponseEntity.noContent().build();
    }

}
