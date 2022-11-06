package com.cms.backend.SummaryModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LayerSummaryModel {
    
    private Long id;
    private Double x;
    private Double y;
    private String color;
    private Long size;
    private String type;

}
