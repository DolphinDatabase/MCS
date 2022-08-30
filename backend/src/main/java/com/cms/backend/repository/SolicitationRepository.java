package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Solicitation;

public interface SolicitationRepository extends JpaRepository<Solicitation,Long>{
    
}
