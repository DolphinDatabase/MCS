package com.cms.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Requisito")
@Getter
@Setter
@NoArgsConstructor
public class Requirement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Long id;

    @Column(name = "req_nome")
    private String name;

    @Column(name = "req_desricaoc")
    private String description;

    @Column(name = "req_quantidade")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "req_chd",nullable = false)
    private Solicitation solicitation;

}
