package com.vortexbird.comprobantepago.modelo.control.jobs;

import java.util.Map;

import com.vortexbird.sentinel.controller.AuthenticationResponse;

public interface IEliminarTokensExpiradosLogic {

	public abstract void eliminarTokensExpirados();
	
	public Map<String, AuthenticationResponse> getObjetosensesion();
	
}