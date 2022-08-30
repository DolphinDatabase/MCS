package com.cms.backend.SummaryModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequirementSummaryModel {
    
    private Long id;
    private String name;
    private String description;
    private Integer quantity;

}
