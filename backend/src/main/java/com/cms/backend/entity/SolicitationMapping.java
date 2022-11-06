package com.cms.backend.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Chamado_Mapeamento")
@Getter
@Setter
@NoArgsConstructor
public class SolicitationMapping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chd_id")
    private Solicitation solicitation;

    @ManyToOne
    @JoinColumn(name = "map_id")
    private Mapping mapping;

    @OneToMany(mappedBy = "solicitationMapping")
    private Set<Layer> layers;

}
