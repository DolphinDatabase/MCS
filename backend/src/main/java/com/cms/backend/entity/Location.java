package com.cms.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Localizacao")
@Getter
@Setter
@NoArgsConstructor
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_id")
    private Long id;

    @Column(name = "loc_cep")
    private String cep;

    @Column(name = "loc_bairro")
    private String bairro;

    @Column(name = "loc_cidade")
    private String cidade;
    
    @Column(name = "loc_complemento")
    private String complemento;

    @Column(name = "loc_logradouro")
    private String logradouro;
  
    @Column(name = "loc_uf")
    private String uf;

}
