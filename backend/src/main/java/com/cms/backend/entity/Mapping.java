package com.cms.backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Mapeamento")
@Getter
@Setter
@NoArgsConstructor
public class Mapping {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "map_id")
    private Long id;

    @Column(name = "map_nome")
    private String name;

    @Column(name = "map_link")
    private String link;

    @OneToMany(mappedBy = "mapping")
    private Set<SolicitationMapping> solicitations;

}
