package com.example.storemygoods.repository;

import com.example.storemygoods.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserNameIgnoreCase(String userName);
    Bill findByUserEmail(String userEmail);
}
