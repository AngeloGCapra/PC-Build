package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Motherboard;
import br.com.java.pcbuild.repositories.MotherboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MotherboardService {

    private final ModelMapper modelMapper;
    private final MotherboardRepository motherboardRepository;

    public List<Motherboard> findAllMotherboards() {
        return motherboardRepository.findAllByOrderByPriceAsc();
    }

    public Optional<Motherboard> findMotherboardById(Long moboId) {
        return motherboardRepository.findById(moboId);
    }

    public Motherboard createMotherboard(Motherboard motherboard) {
        return motherboardRepository.save(motherboard);
    }

    public Motherboard updateMotherboard(Long moboId, Motherboard updatedMotherboard) {
        return motherboardRepository.findById(moboId)
                .map(motherboardEntity -> {
                    modelMapper.map(updatedMotherboard, motherboardEntity);
                    return motherboardRepository.save(motherboardEntity);
                }).orElseThrow(() -> new RuntimeException("Motherboard not found"));
    }

    public void deleteMotherboard(Long moboId) {
        motherboardRepository.deleteById(moboId);
    }

}
