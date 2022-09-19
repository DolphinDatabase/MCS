package com.cms.backend.SummaryModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationSummaryModel {
 
    private Long id;
    private String cep;
    private String bairro;
    private String cidade;
    private String complemento;
    private String logradouro;
    private String uf;
    private Long num;

}
