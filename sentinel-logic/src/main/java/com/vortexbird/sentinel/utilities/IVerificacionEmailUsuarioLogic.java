package com.vortexbird.sentinel.utilities;

import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;

public interface IVerificacionEmailUsuarioLogic {

	public abstract void notificarCambioContrasena(SegUsuario usuario, String nuevaConstrasena)
			throws Exception;

}