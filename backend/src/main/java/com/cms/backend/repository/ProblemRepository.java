package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem,Long>{
    
}
