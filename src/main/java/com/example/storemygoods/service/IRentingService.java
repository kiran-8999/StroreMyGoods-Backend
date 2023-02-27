package com.example.storemygoods.service;

import com.example.storemygoods.model.Renting;
import com.example.storemygoods.model.User;

import java.util.List;

public interface IRentingService {
    List<Renting> all();
    Renting getById(Long id);
    Renting add(Renting user);
    Renting update(Renting user);
    void delete(Long id);
}
