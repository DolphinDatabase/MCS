package com.cms.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cms.backend.entity.Role;
import com.cms.backend.repository.RoleRepository;

@Configuration
public class RoleConfig {
    
    @Autowired
    RoleRepository repository;

    Logger logger = LoggerFactory.getLogger(RoleConfig.class);

    @Bean
    public void setRoles(){
        Role adm = new Role();
        adm.setId(Long.valueOf(1));
        adm.setNivel("ROLE_ADM");
        repository.save(adm);
        logger.info("Role ADM created");

        Role financeiro = new Role();
        financeiro.setId(Long.valueOf(2));
        financeiro.setNivel("ROLE_FNC");
        repository.save(financeiro);
        logger.info("Role FNC created");

        Role suporte = new Role();
        suporte.setId(Long.valueOf(3));
        suporte.setNivel("ROLE_SUP");
        repository.save(suporte);
        logger.info("Role SUP created");

        Role cliente = new Role();
        cliente.setId(Long.valueOf(4));
        cliente.setNivel("ROLE_CLT");
        repository.save(cliente);
        logger.info("Role CLT created");
    }

}
