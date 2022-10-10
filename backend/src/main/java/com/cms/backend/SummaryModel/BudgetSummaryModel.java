package com.cms.backend.SummaryModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BudgetSummaryModel {

    private Long id;
    private String description;
    private String total;
    
}
