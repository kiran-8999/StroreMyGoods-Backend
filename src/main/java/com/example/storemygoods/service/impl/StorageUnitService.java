package com.example.storemygoods.service.impl;

import com.example.storemygoods.model.StorageUnit;
import com.example.storemygoods.repository.IStorageUnitRepository;
import com.example.storemygoods.service.IStorageUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageUnitService implements IStorageUnitService {
    @Autowired
    private IStorageUnitRepository unitRepository;


    @Override
    public List<StorageUnit> all() {
        return unitRepository.findAll();
    }

    @Override
    public StorageUnit getById(Long id) {
        return unitRepository.findById(id).get();
    }

    @Override
    public StorageUnit add(StorageUnit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public StorageUnit update(StorageUnit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public void delete(Long id) {
            unitRepository.deleteById(id);
    }
}
