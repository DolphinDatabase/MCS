package com.cms.backend.SummaryModel;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSummaryModel {
    
    private Long id;
    private String name;
    private String email;
    private String document;
    private String password;
    private Set<RoleSummaryModel> roles;

}
