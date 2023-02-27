package com.example.storemygoods.repository;

import com.example.storemygoods.model.StorageUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStorageUnitRepository extends JpaRepository<StorageUnit,Long> {
}
