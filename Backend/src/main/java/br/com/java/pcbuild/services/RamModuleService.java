package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.RamModule;
import br.com.java.pcbuild.repositories.RamModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RamModuleService {

    private final RamModuleRepository ramModuleRepository;

    public List<RamModule> findAllRamModules() {
        return ramModuleRepository.findAll();
    }

    public Optional<RamModule> findRamModuleById(Integer ramId) {
        return ramModuleRepository.findById(ramId);
    }

    public RamModule createRamModule(RamModule ramModule) {
        return ramModuleRepository.save(ramModule);
    }

    public RamModule updateRamModule(Integer ramId, RamModule updatedRamModule) {
        return ramModuleRepository.findById(ramId)
                .map(ramModule -> {
                    ramModule.setName(updatedRamModule.getName());
                    ramModule.setManufacturer(updatedRamModule.getManufacturer());
                    ramModule.setType(updatedRamModule.getType());
                    ramModule.setSpeed(updatedRamModule.getSpeed());
                    ramModule.setModules(updatedRamModule.getModules());
                    ramModule.setPrice(updatedRamModule.getPrice());
                    ramModule.setComponentRanking(updatedRamModule.getComponentRanking());

                    return ramModuleRepository.save(ramModule);
                }).orElseThrow(() -> new RuntimeException("RamModule not found"));
    }

    public void deleteRamModule(Integer ramId) {
        ramModuleRepository.deleteById(ramId);
    }

}
