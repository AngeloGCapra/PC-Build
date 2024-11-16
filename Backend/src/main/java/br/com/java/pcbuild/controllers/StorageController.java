package br.com.java.pcbuild.controllers;

import br.com.java.pcbuild.models.entities.Storage;
import br.com.java.pcbuild.services.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/storage")
public class StorageController {

    private final StorageService storageService;

    @GetMapping(value = "/getAllStorages")
    public ResponseEntity<List<Storage>> getAllStorages() {
        List<Storage> storages = storageService.findAllStorages();
        return ResponseEntity.ok(storages);
    }

    @GetMapping("/getStorageById/{storageId}")
    public ResponseEntity<Optional<Storage>> getStorageById(@PathVariable("storageId") Integer storageId) {
        Optional<Storage> storage = storageService.findStorageById(storageId);
        return ResponseEntity.ok(storage);
    }

    @PostMapping(value = "/createStorage")
    public ResponseEntity<Storage> createStorage(@RequestBody Storage storage) {
        Storage newStorage = storageService.createStorage(storage);
        return new ResponseEntity<>(newStorage, HttpStatus.CREATED);
    }

    @PutMapping("/updateStorage/{storageId}")
    public ResponseEntity<Storage> updateStorage(@PathVariable("storageId") Integer storageId,
                                                 @RequestBody Storage updatedStorage) {
        Storage storage = storageService.updateStorage(storageId, updatedStorage);
        return ResponseEntity.ok(storage);
    }

    @DeleteMapping("/deleteStorage/{storageId}")
    public ResponseEntity<Void> deleteStorage(@PathVariable("storageId") Integer storageId) {
        storageService.deleteStorage(storageId);
        return ResponseEntity.noContent().build();
    }

}
