package com.cms.backend.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cms.backend.entity.Role;
import com.cms.backend.entity.User;
import com.cms.backend.repository.RoleRepository;
import com.cms.backend.repository.UserRepository;

@Configuration
public class RoleConfig {
    
    @Autowired
    RoleRepository repository;

    @Autowired
    UserRepository uRepository;

    Logger logger = LoggerFactory.getLogger(RoleConfig.class);

    @Bean
    public void setRoles(){
        Role adm = new Role();
        adm.setId(Long.valueOf(1));
        adm.setNivel("ROLE_ADM");
        repository.save(adm);
        User admUser = new User();
        admUser.setId(Long.valueOf(1));
        admUser.setName("ADM");
        admUser.setEmail("adm@email.com");
        admUser.setPassword("123456");
        Set<Role> admRole = new HashSet<>();
        admRole.add(adm);
        admUser.setRoles(admRole);
        uRepository.save(admUser);
        logger.info("Role ADM created");

        Role suporte = new Role();
        suporte.setId(Long.valueOf(2));
        suporte.setNivel("ROLE_SUP");
        repository.save(suporte);
        logger.info("Role SUP created");

        Role cliente = new Role();
        cliente.setId(Long.valueOf(3));
        cliente.setNivel("ROLE_CLT");
        repository.save(cliente);
        logger.info("Role CLT created");
    }

}
