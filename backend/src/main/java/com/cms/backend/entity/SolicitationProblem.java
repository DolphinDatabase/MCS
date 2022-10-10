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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Chamado_Problema")
public class SolicitationProblem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chd_id")
    private Solicitation solicitation;

    @ManyToOne
    @JoinColumn(name = "prb_id")
    private Problem problem;

    @Column(name = "largura")
    private Double width;

    @Column(name = "altura")
    private Double height;

    @Column(name = "profundidade")
    private Double depth;

}