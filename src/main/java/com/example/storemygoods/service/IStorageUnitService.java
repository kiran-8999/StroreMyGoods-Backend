package com.example.storemygoods.service;

import com.example.storemygoods.model.StorageUnit;

import java.util.List;

public interface IStorageUnitService {

    List<StorageUnit> all();
    StorageUnit getById(Long id);
    StorageUnit add(StorageUnit unit);
    StorageUnit update(StorageUnit unit);
    void delete(Long id);
}
