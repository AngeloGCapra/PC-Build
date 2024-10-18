package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Processor;
import br.com.java.pcbuild.repositories.ProcessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcessorService {

    private final ProcessorRepository processorRepository;

    public List<Processor> findAllProcessors() {
        return processorRepository.findAll();
    }

    public Optional<Processor> findProcessorById(Integer processorId) {
        return processorRepository.findById(processorId);
    }

    public Processor createProcessor(Processor processor) {
        return processorRepository.save(processor);
    }

    public Processor updateProcessor(Integer processorId, Processor updatedProcessor) {
        return processorRepository.findById(processorId)
                .map(processor -> {
                    processor.setName(updatedProcessor.getName());
                    processor.setBrand(updatedProcessor.getBrand());
                    processor.setCores(updatedProcessor.getCores());
                    processor.setThreads(updatedProcessor.getThreads());
                    processor.setBaseClock(updatedProcessor.getBaseClock());
                    processor.setTurboClock(updatedProcessor.getTurboClock());
                    processor.setTdp(updatedProcessor.getTdp());
                    processor.setPrice(updatedProcessor.getPrice());
                    processor.setComponentRanking(updatedProcessor.getComponentRanking());
                    processor.setSocket(updatedProcessor.getSocket());
                    processor.setCpuPerformance(updatedProcessor.getCpuPerformance());

                    return processorRepository.save(processor);
                }).orElseThrow(() -> new RuntimeException("Processor not found"));
    }

    public void deleteProcessor(Integer processorId) {
        processorRepository.deleteById(processorId);
    }

}
