package com.cms.backend.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cms.backend.util.Nivel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Problema")
@Getter
@Setter
@NoArgsConstructor
public class Problem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prb_id")
    private Long id;

    @Column(name = "prb_nome")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "prb_nvl")
    private Nivel nivel;

    @Column(name = "prb_cor")
    private String color;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.REMOVE)
    private Set<Solution> solutions;

    @OneToMany(mappedBy = "problem")
    private Set<SolicitationProblem> solicitations;

}
