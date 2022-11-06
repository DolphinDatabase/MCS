package com.cms.backend.SummaryModel;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolicitationMappingSummaryModel {
    
    private Long id;
    private MappingSummaryModel mapping;
    private Set<LayerSummaryModel> layers;

}
