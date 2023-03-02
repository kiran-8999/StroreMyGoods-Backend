package com.example.storemygoods.service.impl;

import com.example.storemygoods.model.Bill;
import com.example.storemygoods.repository.IBillRepository;
import com.example.storemygoods.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService implements IBillService {

    @Autowired
    private IBillRepository billRepository;

    @Override
    public List<Bill> all() {
        return billRepository.findAll();
    }

    @Override
    public Bill getById(Long id) {
        return billRepository.findById(id).get();
    }

    @Override
    public Bill add(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill update(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void delete(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public List<Bill> getByUserName(String userName) {
        return billRepository.findByUserNameIgnoreCase(userName);
    }

    @Override
    public Bill getByUserEmail(String userEmail) {
        return billRepository.findByUserEmail(userEmail);
    }

}
