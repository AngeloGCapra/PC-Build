package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Case;
import br.com.java.pcbuild.repositories.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaseService {

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
                    caseEntity.setName(updatedCase.getName());
                    caseEntity.setManufacturer(updatedCase.getManufacturer());
                    caseEntity.setType(updatedCase.getType());
                    caseEntity.setColor(updatedCase.getColor());
                    caseEntity.setSidePanel(updatedCase.getSidePanel());
                    caseEntity.setPrice(updatedCase.getPrice());
                    caseEntity.setComponentRanking(updatedCase.getComponentRanking());

                    return caseRepository.save(caseEntity);
                }).orElseThrow(() -> new RuntimeException("Case not found"));
    }

    public void deleteCase(Integer caseId) {
        caseRepository.deleteById(caseId);
    }

}
