package com.example.storemygoods.repository;

import com.example.storemygoods.model.Renting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentingRepository extends JpaRepository<Renting,Long> {
}
