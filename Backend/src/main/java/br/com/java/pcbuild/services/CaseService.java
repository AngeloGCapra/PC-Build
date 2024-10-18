package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Case;
import br.com.java.pcbuild.repositories.CaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaseService {

    private final ModelMapper modelMapper;
    private final CaseRepository caseRepository;

    public List<Case> findAllCases() {
        return caseRepository.findAll();
    }

    public Optional<Case> findCaseById(Integer caseId) {
        return caseRepository.findById(caseId);
    }

    public Case createCase(Case caseEntity) {
        return caseRepository.save(caseEntity);
    }

    public Case updateCase(Integer caseId, Case updatedCase) {
        return caseRepository.findById(caseId)
                .map(caseEntity -> {
                    modelMapper.map(updatedCase, caseEntity);
                    return caseRepository.save(caseEntity);
                }).orElseThrow(() -> new RuntimeException("Case not found"));
    }

    public void deleteCase(Integer caseId) {
        caseRepository.deleteById(caseId);
    }

}
