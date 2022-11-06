package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Mapping;

public interface MappingRepository extends JpaRepository<Mapping,Long>{
    
}
