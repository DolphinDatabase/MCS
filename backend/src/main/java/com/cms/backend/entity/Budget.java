package com.cms.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orcamento")
@Getter
@Setter
@NoArgsConstructor
public class Budget {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orc_id")
    private Long id;

    @Column(name = "orc_descricao")
    private String description;

    @Column(name = "orc_total")
    private Double total;

    @OneToOne(mappedBy = "budget", fetch = FetchType.LAZY)
    private Solicitation solicitation;

}
