package com.vortexbird.sentinel.modelo.control;

import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;

public interface ILoginLogic {

	public abstract UsuarioDTO autenticar(String login, String pass,
			String dominio) throws Exception;

}