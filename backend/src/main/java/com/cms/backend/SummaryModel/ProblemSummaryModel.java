package com.cms.backend.SummaryModel;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProblemSummaryModel {
    private Long id;
    private String name;
    private String nivel;
    private String color;
    private Set<SolutionSummaryModel> solutions;
}
