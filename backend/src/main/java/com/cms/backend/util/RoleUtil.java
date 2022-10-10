package com.cms.backend.util;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.cms.backend.entity.Role;

public class RoleUtil {
    
    public static ArrayList<SimpleGrantedAuthority> parseAuthority(Set<Role> r){
        ArrayList<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        for(Role role:r){
            roles.add(new SimpleGrantedAuthority(role.getNivel()));
        }
        return roles;
    }

}
