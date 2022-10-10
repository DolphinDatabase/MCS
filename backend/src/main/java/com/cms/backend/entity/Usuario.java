package com.cms.backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_nome")
    private String name;

    @Column(name = "usr_email")
    private String email;

    @Column(name = "usr_doc")
    private String document;
    
    @Column(name = "usr_senha")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Solicitation> solicitations;

    @ManyToMany
    @JoinTable(name = "Usuario_Nivel", 
    joinColumns = @JoinColumn(name = "usr_id"), 
    inverseJoinColumns = @JoinColumn(name = "nvl_id"))
    private Set<Role> roles;

}
