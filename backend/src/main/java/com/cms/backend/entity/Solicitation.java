package com.cms.backend.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cms.backend.util.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Chamado")
@Getter
@Setter
@NoArgsConstructor
public class Solicitation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chd_id")
    private Long id;

    @Column(name = "chd_nome")
    private String name;

    @Column(name = "chd_descricao")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "chd_data")
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "chd_servico")
    private Date service;

    @Enumerated(EnumType.STRING)
    @Column(name = "chd_status")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loc_id", referencedColumnName = "loc_id")
    private Location location;

    @OneToOne
    @JoinColumn(name = "orc_id", referencedColumnName = "orc_id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "usr_id", nullable = false)
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "responsible_id", nullable = true)
    private Usuario responsible;

    @OneToMany(mappedBy = "solicitation")
    private Set<SolicitationProblem> problems;

    @OneToMany(mappedBy = "solicitation")
    private Set<SolicitationMapping> mappings;
    
    @ManyToMany
    @JoinTable(name = "Chamado_Estoque",
    joinColumns = @JoinColumn(name = "chd_id"),
    inverseJoinColumns = @JoinColumn(name = "est_nserie"))
    private Set<Material> materials;

    @PrePersist
    public void OnCreate(){
        this.date = new Date();
    }

}
