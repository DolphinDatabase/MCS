package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Material;

public interface MaterialRepository extends JpaRepository<Material,Long>{
    
}
