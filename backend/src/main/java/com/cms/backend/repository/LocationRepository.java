package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Location;

public interface LocationRepository extends JpaRepository<Location,Long>{
    
}
