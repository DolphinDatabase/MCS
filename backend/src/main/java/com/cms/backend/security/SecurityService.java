package com.cms.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cms.backend.entity.Usuario;
import com.cms.backend.repository.UserRepository;
import com.cms.backend.util.RoleUtil;

@Service("securityService")
public class SecurityService implements UserDetailsService{

    @Autowired
    private UserRepository uRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = uRepository.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return User.builder().username(usuario.getEmail()).password(usuario.getPassword()).authorities(RoleUtil.parseAuthority(usuario.getRoles())).build();
    }
    
}
