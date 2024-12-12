package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.GraphicsCard;
import br.com.java.pcbuild.repositories.GraphicsCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GraphicsCardService {

    private final ModelMapper modelMapper;
    private final GraphicsCardRepository graphicsCardRepository;

    public List<GraphicsCard> findAllGraphicsCards() {
        return graphicsCardRepository.findAllByOrderByPriceAsc();
    }

    public Optional<GraphicsCard> findGraphicsCardById(Long gpuId) {
        return graphicsCardRepository.findById(gpuId);
    }

    public GraphicsCard createGraphicsCard(GraphicsCard graphicsCard) {
        return graphicsCardRepository.save(graphicsCard);
    }

    public GraphicsCard updateGraphicsCard(Long gpuId, GraphicsCard updatedGraphicsCard) {
        return graphicsCardRepository.findById(gpuId)
                .map(graphicsCardEntity -> {
                    modelMapper.map(updatedGraphicsCard, graphicsCardEntity);
                    return graphicsCardRepository.save(graphicsCardEntity);
                }).orElseThrow(() -> new RuntimeException("GraphicsCard not found"));
    }

    public void deleteGraphicsCard(Long gpuId) {
        graphicsCardRepository.deleteById(gpuId);
    }

}
