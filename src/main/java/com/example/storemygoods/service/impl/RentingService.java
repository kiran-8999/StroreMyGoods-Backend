package com.example.storemygoods.service.impl;

import com.example.storemygoods.model.Renting;
import com.example.storemygoods.repository.IRentingRepository;
import com.example.storemygoods.service.IRentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentingService implements IRentingService {

    @Autowired
    private IRentingRepository rentingRepository;

    @Override
    public List<Renting> all() {
        return rentingRepository.findAll();
    }

    @Override
    public Renting getById(Long id) {
        return rentingRepository.findById(id).get();
    }

    @Override
    public Renting add(Renting user) {
        return rentingRepository.save(user);
    }

    @Override
    public Renting update(Renting user) {
        return rentingRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        rentingRepository.deleteById(id);
    }
}
