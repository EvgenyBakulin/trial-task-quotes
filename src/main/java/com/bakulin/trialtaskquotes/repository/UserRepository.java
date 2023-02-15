package com.bakulin.trialtaskquotes.repository;

import com.bakulin.trialtaskquotes.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUsersByPassword(String password);
}
