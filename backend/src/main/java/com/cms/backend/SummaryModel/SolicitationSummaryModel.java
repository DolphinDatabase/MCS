package com.cms.backend.SummaryModel;

import java.util.Date;
import java.util.Set;

import com.cms.backend.util.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SolicitationSummaryModel {
    
    private Long id;
    private String name;
    private String description;
    private Date date;
    private Status status;
    private LocationSummaryModel location;
    private UserSummaryModel user;
    private Set<ProblemSummaryModel> problems; 

}
