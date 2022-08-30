package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
