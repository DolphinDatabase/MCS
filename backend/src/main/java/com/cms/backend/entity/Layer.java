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
@Table(name = "Layer")
@Getter
@Setter
@NoArgsConstructor
public class Layer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lyr_id")
    private Long id;

    @Column(name = "lyr_x")
    private Double x;

    @Column(name = "lyr_y")
    private Double y;

    @Column(name = "lyr_color")
    private String color;
    
    @Column(name = "lyr_tamanho")
    private Long size;

    @Column(name = "lyr_forma")
    private String type;

    @ManyToOne
    @JoinColumn(name = "map_id")
    private SolicitationMapping solicitationMapping;

}
