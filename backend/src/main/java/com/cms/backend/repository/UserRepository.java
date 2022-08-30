package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
