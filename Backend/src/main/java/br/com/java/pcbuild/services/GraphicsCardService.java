package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.GraphicsCard;
import br.com.java.pcbuild.repositories.GraphicsCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GraphicsCardService {

    private final GraphicsCardRepository graphicsCardRepository;

    public List<GraphicsCard> findAllGraphicsCards() {
        return graphicsCardRepository.findAll();
    }

    public Optional<GraphicsCard> findGraphicsCardById(Integer gpuId) {
        return graphicsCardRepository.findById(gpuId);
    }

    public GraphicsCard createGraphicsCard(GraphicsCard graphicsCard) {
        return graphicsCardRepository.save(graphicsCard);
    }

    public GraphicsCard updateGraphicsCard(Integer gpuId, GraphicsCard updatedGraphicsCard) {
        return graphicsCardRepository.findById(gpuId)
                .map(graphicsCard -> {
                    graphicsCard.setName(updatedGraphicsCard.getName());
                    graphicsCard.setChipset(updatedGraphicsCard.getChipset());
                    graphicsCard.setManufacturer(updatedGraphicsCard.getManufacturer());
                    graphicsCard.setBrand(updatedGraphicsCard.getBrand());
                    graphicsCard.setBaseClock(updatedGraphicsCard.getBaseClock());
                    graphicsCard.setBoostClock(updatedGraphicsCard.getBoostClock());
                    graphicsCard.setMemorySize(updatedGraphicsCard.getMemorySize());
                    graphicsCard.setPrice(updatedGraphicsCard.getPrice());
                    graphicsCard.setComponentRanking(updatedGraphicsCard.getComponentRanking());
                    graphicsCard.setGpuPerformance(updatedGraphicsCard.getGpuPerformance());

                    return graphicsCardRepository.save(graphicsCard);
                }).orElseThrow(() -> new RuntimeException("GraphicsCard not found"));
    }

    public void deleteGraphicsCard(Integer gpuId) {
        graphicsCardRepository.deleteById(gpuId);
    }

}
