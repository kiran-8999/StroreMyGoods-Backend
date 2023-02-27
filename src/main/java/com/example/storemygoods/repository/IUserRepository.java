package com.example.storemygoods.repository;

import com.example.storemygoods.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    UserDetails findByUserName(String username);
}
