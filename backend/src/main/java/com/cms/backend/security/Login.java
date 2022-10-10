package com.cms.backend.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Login {
    
    private String login;
    private String password;
    private String role;
    private String token;

}
