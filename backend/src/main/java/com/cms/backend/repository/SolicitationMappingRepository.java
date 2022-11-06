package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.SolicitationMapping;

public interface SolicitationMappingRepository extends JpaRepository<SolicitationMapping,Long>{
    
}
