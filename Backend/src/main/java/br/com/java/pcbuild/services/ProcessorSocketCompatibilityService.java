package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.ProcessorSocketCompatibility;
import br.com.java.pcbuild.repositories.ProcessorSocketCompatibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessorSocketCompatibilityService {

    private final ProcessorSocketCompatibilityRepository compatibilityRepository;

    public List<ProcessorSocketCompatibility> findAllCompatibilities() {
        return compatibilityRepository.findAll();
    }

    public Optional<ProcessorSocketCompatibility> findCompatibilityById(Integer pscId) {
        return compatibilityRepository.findById(pscId);
    }

    public ProcessorSocketCompatibility createCompatibility(ProcessorSocketCompatibility compatibility) {
        return compatibilityRepository.save(compatibility);
    }

    public ProcessorSocketCompatibility updateCompatibility(Integer pscId, ProcessorSocketCompatibility updatedCompatibility) {
        return compatibilityRepository.findById(pscId)
                .map(existingCompatibility -> {
                    existingCompatibility.setCpu(updatedCompatibility.getCpu());
                    existingCompatibility.setSocket(updatedCompatibility.getSocket());

                    return compatibilityRepository.save(existingCompatibility);
                }).orElseThrow(() -> new RuntimeException("Compatibility not found"));
    }

    public void deleteCompatibility(Integer pscId) {
        compatibilityRepository.deleteById(pscId);
    }

}
