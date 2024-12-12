package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Processor;
import br.com.java.pcbuild.repositories.ProcessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessorService {

    private final ModelMapper modelMapper;
    private final ProcessorRepository processorRepository;

    public List<Processor> findAllProcessors() {
        return processorRepository.findAllByOrderByPriceAsc();
    }

    public Optional<Processor> findProcessorById(Long processorId) {
        return processorRepository.findById(processorId);
    }

    public Processor createProcessor(Processor processor) {
        return processorRepository.save(processor);
    }

    public Processor updateProcessor(Long processorId, Processor updatedProcessor) {
        return processorRepository.findById(processorId)
                .map(processorEntity -> {
                    modelMapper.map(updatedProcessor, processorEntity);
                    return processorRepository.save(processorEntity);
                }).orElseThrow(() -> new RuntimeException("Processor not found"));
    }

    public void deleteProcessor(Long processorId) {
        processorRepository.deleteById(processorId);
    }

}
