package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Budget;

public interface BudgetRepository extends JpaRepository<Budget,Long>{
    
}
