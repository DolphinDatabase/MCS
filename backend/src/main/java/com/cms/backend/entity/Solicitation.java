package com.cms.backend.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "chd_data")
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "chd_status")
    private Status status;

    @OneToMany(mappedBy = "solicitation")
    private Set<Requirement> requirements;

    @PrePersist
    public void OnCreate(){
        this.date = new Date();
    }

}
