package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.RamModule;
import br.com.java.pcbuild.repositories.RamModuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RamModuleService {

    private final ModelMapper modelMapper;
    private final RamModuleRepository ramModuleRepository;

    public List<RamModule> findAllRamModules() {
        return ramModuleRepository.findAll();
    }

    public Optional<RamModule> findRamModuleById(Long ramId) {
        return ramModuleRepository.findById(ramId);
    }

    public RamModule createRamModule(RamModule ramModule) {
        return ramModuleRepository.save(ramModule);
    }

    public RamModule updateRamModule(Long ramId, RamModule updatedRamModule) {
        return ramModuleRepository.findById(ramId)
                .map(ramModuleEntity -> {
                    modelMapper.map(updatedRamModule, ramModuleEntity);
                    return ramModuleRepository.save(ramModuleEntity);
                }).orElseThrow(() -> new RuntimeException("RamModule not found"));
    }

    public void deleteRamModule(Long ramId) {
        ramModuleRepository.deleteById(ramId);
    }

}
