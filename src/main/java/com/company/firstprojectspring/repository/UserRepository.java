package com.company.firstprojectspring.repository;

import com.company.firstprojectspring.module.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUserId(Integer userId);

}
