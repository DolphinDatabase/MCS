package com.cms.backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Estoque")
@Getter
@Setter
@NoArgsConstructor
public class Material {

    @Id
    @Column(name = "est_nserie")
    private Long num;

    @Column(name = "est_modelo")
    private String model;

    @Column(name = "est_descricao")
    private String description;

    @Column(name = "est_quantidade")
    private Long quantity;
    
    @Column(name = "est_estoque")
    private Long inventory;

    @ManyToMany(mappedBy = "materials")
    private Set<Solicitation> solicitations;
    
}
