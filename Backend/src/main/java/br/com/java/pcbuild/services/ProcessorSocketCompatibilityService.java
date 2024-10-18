package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.ProcessorSocketCompatibility;
import br.com.java.pcbuild.repositories.ProcessorSocketCompatibilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessorSocketCompatibilityService {

    private final ModelMapper modelMapper;
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
                .map(existingCompatibilityEntity -> {
                    modelMapper.map(updatedCompatibility, existingCompatibilityEntity);
                    return compatibilityRepository.save(existingCompatibilityEntity);
                }).orElseThrow(() -> new RuntimeException("Compatibility not found"));
    }

    public void deleteCompatibility(Integer pscId) {
        compatibilityRepository.deleteById(pscId);
    }

}
