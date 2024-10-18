package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Motherboard;
import br.com.java.pcbuild.repositories.MotherboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MotherboardService {

    private final MotherboardRepository motherboardRepository;

    public List<Motherboard> findAllMotherboards() {
        return motherboardRepository.findAll();
    }

    public Optional<Motherboard> findMotherboardById(Integer moboId) {
        return motherboardRepository.findById(moboId);
    }

    public Motherboard createMotherboard(Motherboard motherboard) {
        return motherboardRepository.save(motherboard);
    }

    public Motherboard updateMotherboard(Integer moboId, Motherboard updatedMotherboard) {
        return motherboardRepository.findById(moboId)
                .map(motherboard -> {
                    motherboard.setName(updatedMotherboard.getName());
                    motherboard.setManufacturer(updatedMotherboard.getManufacturer());
                    motherboard.setChipset(updatedMotherboard.getChipset());
                    motherboard.setFormFactor(updatedMotherboard.getFormFactor());
                    motherboard.setRamSlots(updatedMotherboard.getRamSlots());
                    motherboard.setMemoryMax(updatedMotherboard.getMemoryMax());
                    motherboard.setPcieSlots(updatedMotherboard.getPcieSlots());
                    motherboard.setPrice(updatedMotherboard.getPrice());
                    motherboard.setComponentRanking(updatedMotherboard.getComponentRanking());
                    motherboard.setSocket(updatedMotherboard.getSocket());

                    return motherboardRepository.save(motherboard);
                }).orElseThrow(() -> new RuntimeException("Motherboard not found"));
    }

    public void deleteMotherboard(Integer moboId) {
        motherboardRepository.deleteById(moboId);
    }

}
