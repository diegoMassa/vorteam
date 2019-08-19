package com.vortexbird.vorteam.security;

import org.springframework.context.annotation.Scope;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;

import com.vortexbird.sentinel.dto.UsuarioDTO;
import com.vortexbird.vorteam.utility.FacesUtils;

import java.util.ArrayList;
import java.util.List;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Component("zathuraCodeAuthenticationProvider")
public class ZathuraCodeAuthenticationProvider implements AuthenticationProvider {
    /**
     * Security Implementation
     */
    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
    	
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        UsuarioDTO usuarioDTO1 = (UsuarioDTO) FacesUtils.getfromSession("usuarioDTOLogin");

        final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        if (usuarioDTO1 != null) {
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        
        final UserDetails principal = new User(name, password, grantedAuths);
        final Authentication auth = new UsernamePasswordAuthenticationToken(principal,
                password, grantedAuths);

        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
