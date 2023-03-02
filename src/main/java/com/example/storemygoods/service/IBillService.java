package com.example.storemygoods.service;

import com.example.storemygoods.model.Bill;

import java.util.List;

public interface IBillService {
    List<Bill> all();
    Bill getById(Long id);
    Bill add(Bill bill);
    Bill update(Bill bill);
    void delete(Long id);

    List<Bill> getByUserName(String userName);
    Bill getByUserEmail(String userEmail);
}
