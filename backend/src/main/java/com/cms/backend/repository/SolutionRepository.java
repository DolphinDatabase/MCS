package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Solution;

public interface SolutionRepository extends JpaRepository<Solution,Long>{
    
}
