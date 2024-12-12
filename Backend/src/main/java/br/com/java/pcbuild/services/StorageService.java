package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Storage;
import br.com.java.pcbuild.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {

    private final ModelMapper modelMapper;
    private final StorageRepository storageRepository;

    public List<Storage> findAllStorages() {
        return storageRepository.findAllByOrderByPriceAsc();
    }

    public Optional<Storage> findStorageById(Long storageId) {
        return storageRepository.findById(storageId);
    }

    public Storage createStorage(Storage storage) {
        return storageRepository.save(storage);
    }

    public Storage updateStorage(Long storageId, Storage updatedStorage) {
        return storageRepository.findById(storageId)
                .map(storageEntity -> {
                    modelMapper.map(updatedStorage, storageEntity);
                    return storageRepository.save(storageEntity);
                }).orElseThrow(() -> new RuntimeException("Storage not found"));
    }

    public void deleteStorage(Long storageId) {
        storageRepository.deleteById(storageId);
    }

}
