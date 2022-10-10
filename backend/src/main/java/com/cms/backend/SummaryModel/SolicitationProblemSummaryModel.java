package com.cms.backend.SummaryModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolicitationProblemSummaryModel {
    
    private Long id;
    private ProblemSummaryModel problem;
    private Double width;
    private Double height;
    private Double depth;

}
