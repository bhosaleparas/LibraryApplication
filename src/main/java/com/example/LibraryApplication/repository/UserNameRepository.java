package com.example.LibraryApplication.repository;

import com.example.LibraryApplication.entity.UserName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserNameRepository extends JpaRepository<UserName, Long> {

    Optional<UserName> findByUsername(String username);
}