package com.cms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.backend.entity.Usuario;

public interface UserRepository extends JpaRepository<Usuario,Long>{
    public Usuario findByEmail(String email);
}
