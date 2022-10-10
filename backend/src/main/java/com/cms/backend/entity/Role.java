package com.cms.backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Nivel")
@Getter
@Setter
@NoArgsConstructor
public class Role{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nvl_id")
    private Long id;

    @Column(name = "nvl_nivel")
    private String nivel;
    
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> users;

}
