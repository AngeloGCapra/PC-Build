package br.com.java.pcbuild.services;

import br.com.java.pcbuild.models.entities.Storage;
import br.com.java.pcbuild.repositories.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    public List<Storage> findAllStorages() {
        return storageRepository.findAll();
    }

    public Optional<Storage> findStorageById(Integer storageId) {
        return storageRepository.findById(storageId);
    }

    public Storage createStorage(Storage storage) {
        return storageRepository.save(storage);
    }

    public Storage updateStorage(Integer storageId, Storage updatedStorage) {
        return storageRepository.findById(storageId)
                .map(storage -> {
                    storage.setName(updatedStorage.getName());
                    storage.setManufacturer(updatedStorage.getManufacturer());
                    storage.setCapacity(updatedStorage.getCapacity());
                    storage.setCache(updatedStorage.getCache());
                    storage.setType(updatedStorage.getType());
                    storage.setFormFactor(updatedStorage.getFormFactor());
                    storage.setStorageInterface(updatedStorage.getStorageInterface());
                    storage.setPrice(updatedStorage.getPrice());
                    storage.setComponentRanking(updatedStorage.getComponentRanking());

                    return storageRepository.save(storage);
                }).orElseThrow(() -> new RuntimeException("Storage not found"));
    }

    public void deleteStorage(Integer storageId) {
        storageRepository.deleteById(storageId);
    }

}
