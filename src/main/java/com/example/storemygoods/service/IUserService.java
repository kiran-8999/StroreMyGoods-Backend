package com.example.storemygoods.service;

import com.example.storemygoods.model.User;

import java.util.List;

public interface IUserService {
    List<User> all();
    User getById(Long id);
    User add(User user);
    User update(User user);
    void delete(Long id);
    User getByEmail(String email);
}
