package com.vortexbird.sentinel.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;
import com.vortexbird.sentinel.presentation.businessDelegate.IBusinessDelegatorView;
import com.vortexbird.sentinel.utilities.FacesUtils;

@Scope("singleton")
@Component("VortexAuthenticationProvider")
public class VortexAuthenticationProvider implements AuthenticationProvider {

	/**
	 * Implementacion de la seguridad propia
	 */
	@Autowired
	private IBusinessDelegatorView businessDelegatorView;
	
	org.primefaces.sentinel.component.menu.SentinelMenuRenderer g;

	@Override
	public Authentication authenticate(Authentication authentication)throws AuthenticationException {
		
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        String dominio = (String) FacesUtils.getHttpSession(true).getAttribute("dominio");
        
        
        if(name == null || name.trim().equals("")){
        	throw new AuthenticationServiceException("Porfavor digite su usuario.");
        }
        
        if(password == null || password.trim().equals("")){
        	throw new AuthenticationServiceException("Porfavor digite su clave.");
        }
        
        if(dominio == null || dominio.trim().equals("")){
        	throw new AuthenticationServiceException("Porfavor digite el dominio.");
        }

        UsuarioDTO usuarioDTO=null;
        
        final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuths.add(new SimpleGrantedAuthority("Admin"));

		final UserDetails principal = new User(name, password, grantedAuths);
		final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
		
		return auth;
        
	}

	/**
	 * Este metodo se llama primero cuando se autentica un usuario
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}