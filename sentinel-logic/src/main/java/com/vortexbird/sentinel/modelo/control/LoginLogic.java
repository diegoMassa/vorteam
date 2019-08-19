package com.vortexbird.sentinel.modelo.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.vortexbird.sentinel.modelo.SegParametro;
import com.vortexbird.sentinel.modelo.SegRol;
import com.vortexbird.sentinel.modelo.SegRolUsuario;
import com.vortexbird.sentinel.modelo.SegUsuario;
import com.vortexbird.sentinel.modelo.dto.UsuarioDTO;
import com.vortexbird.sentinel.utilities.Fechas;
import com.vortexbird.sentinel.utilities.Utilities;

@Scope("singleton")
@Service("LoginLogic")
public class LoginLogic implements ILoginLogic {

	private Logger logger = LoggerFactory.getLogger(LoginLogic.class);
	
	@Autowired
	private ISegUsuarioLogic segUsuarioLogic;
	
	@Autowired
	private ISegRolLogic segRolLogic;
	
	
	@Autowired
	private ISegParametroLogic segParametroLogic;

	/* (non-Javadoc)
	 * @see com.vortexbird.sentinel.modelo.control.ILoginLogic#autenticar(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public UsuarioDTO autenticar(String login, String pass, String dominio) throws Exception{

		UsuarioDTO usuDto = new UsuarioDTO();
		List<SegRol> rol = null;
		List<SegUsuario> usuario = new ArrayList<SegUsuario>();
		try {

			SegUsuario u = null;
			String passEncriptado = "";
			boolean existeUsuarioEnBD = false;
			boolean coincidePwd = false;
			
			logger.info("Autenticando el usuario Login = " + login + " Password = " + pass + " Dominio = " + dominio);

			//	Se consulta el usuario en la BD de seguridad
			usuario = segUsuarioLogic.findByCriteria(new Object[]{
					"usuLogin",true,login.toUpperCase().trim(),"=",
					"usuConstrasena",true, Utilities.convertirMD5(pass).trim(),"=",
					"usuCodigoInterno",true,dominio.toUpperCase().trim(),"=",
					"usuEstadoRegistro",true,"A","="},null, null);
			
			existeUsuarioEnBD = (usuario!=null && usuario.size()>0) ? true : false;
			
			logger.info("Usuario encontrado en la BD de seguridad? " + existeUsuarioEnBD);

			//Se valida su contrase�a en la BD
			if(existeUsuarioEnBD){
				u = usuario.get(0);
				passEncriptado = Utilities.convertirMD5(pass);
				coincidePwd = (u.getUsuConstrasena().equals(passEncriptado)) ? true : false;
			}

			//Se crea el DTO del usuario a partir del usuario consultado
			if(u!=null) {
				String todosLosRolles ="";
				String dias_caducidad_pwd = "dias_caducidad_pwd";

				boolean esSuperAdministradorDelSistemaDeSeguridad = false;
				
				Set<SegRolUsuario> arrayRoles = u.getSegRolUsuariosForSegUsuarioUsuCodigo();
				//List<SegRolUsuario> listRoles = new ArrayList<SegRolUsuario>(arrayRoles);
				
				//Si tiene 1 solo rol y el rol es codigo 0, se trata del super administrador del sistema de seguridad
				if (arrayRoles.size()==1){
					//Consulto el Nombre del rol para Repuestos
					if (arrayRoles.iterator().next().getSegRol().getRolCodigo().equals(0L)){
						//Se trata del super administrador del sistema de seguridad
						//Al super administrador, no le caduca el password
						usuDto.setDias_caducidad("*");
						esSuperAdministradorDelSistemaDeSeguridad = true;
						usuDto.setEmailFueCertificado("S");
					}
				}
				
				for (SegRolUsuario segRolUsuario : arrayRoles) {
					//Realizado por joselito
					if (todosLosRolles.equals("") ) {
						todosLosRolles = segRolUsuario.getSegRol().getRolNombre();
					}else {
						todosLosRolles=todosLosRolles+"-"+segRolUsuario.getSegRol().getRolNombre();
					}
				}
				
				//Si no es super administrador del sistema de seguridad, se valida los días de caducidad del password
				if (!esSuperAdministradorDelSistemaDeSeguridad){
					
					int dias = -1;
					List<SegParametro> parametros = segParametroLogic.findByCriteria(new Object[]{"parNombre",true,dias_caducidad_pwd.trim(),"="},null, null);
					if (parametros != null && parametros.size()>0){
						dias = parametros.get(0).getParValorNumerico().intValue();
					}
					
					Date hoy = new Date();

					Date ultima_fecha_mod_pass = u.getUsuUltmimaModificacionPass();
					ultima_fecha_mod_pass = ultima_fecha_mod_pass == null? new Date() : ultima_fecha_mod_pass;
					ultima_fecha_mod_pass = Fechas.sumar(ultima_fecha_mod_pass, dias, 0,0,0);
					Double diasEntreFechas = Fechas.diasEntreFechas(hoy,ultima_fecha_mod_pass);
					usuDto.setDias_caducidad(diasEntreFechas.intValue()+"");
					
					if (u.getUsuCorreo()!=null && !u.getUsuCorreo().endsWith("?")){
						usuDto.setEmailFueCertificado("S");
						usuDto.setUsu_correo(u.getUsuCorreo());
					}else if (u.getUsuCorreo()!=null && u.getUsuCorreo().endsWith("?")){
						usuDto.setEmailFueCertificado("N");
						String correo = u.getUsuCorreo().substring(0, u.getUsuCorreo().length()-1);
						usuDto.setUsu_correo(correo);
					}
					
				}
				
				usuDto.setUsu_activo(u.getUsuEstadoRegistro());
				usuDto.setUsu_apellidos(u.getUsuApellidos());
				usuDto.setUsu_codigo(u.getUsuCodigo().longValue()+"");
				usuDto.setUsu_codigo_interno(u.getUsuCodigoInterno());
				usuDto.setUsu_login(u.getUsuLogin());
				usuDto.setUsu_nombres(u.getUsuNombres());
				usuDto.setUsuValidarPrimeraVez(u.getUsuPrimerLoginSso());
				usuDto.setUsu_intentos_fallidos(u.getUsuIntentosFallidos()!=null?u.getUsuIntentosFallidos().toString():"0");
				usuDto.setUsu_ultmima_modificacion_pass(
						u.getUsuUltmimaModificacionPass()!=null?
						Fechas.dateToStr(u.getUsuUltmimaModificacionPass(), "yyyy-MM-dd") 
						:
						null);
				usuDto.setContrasenaMD5(u.getUsuConstrasena());
				usuDto.setNombre_rol(todosLosRolles);
				
			}

			Long intentosFallidos = 0L;

			logger.info("existeUsuarioEnBD && coincidePwd = " + existeUsuarioEnBD + " - " + coincidePwd);
			
			if (existeUsuarioEnBD && coincidePwd){
				usuDto.setEstado("2");//no es usuario ldap pero es usuario de algun sistema (puede ser un vendedor o porteria). Debe permitir el acceso
				
				u.setUsuIntentosFallidos(intentosFallidos);
				segUsuarioLogic.updateSegUsuario(u);
				
				usuDto.setUsu_intentos_fallidos(intentosFallidos.toString());
				
			}else if (existeUsuarioEnBD && !coincidePwd){
				
				usuDto.setEstado("4");//no es usuario ldap y no coincide la contrasena
				intentosFallidos = u.getUsuIntentosFallidos() + 1;
				
				u.setUsuIntentosFallidos(intentosFallidos);
				segUsuarioLogic.updateSegUsuario(u);
				
				usuDto.setUsu_intentos_fallidos(intentosFallidos.toString());
				
			}else if(!existeUsuarioEnBD){
				
				usuDto.setEstado("4");//no es usuario ldap y tampoco es usuario de algun sistema
			}


		} catch (Exception e) {
			logger.error("Error autenticando el usuario", e);
			throw e;
		}
		
		return usuDto;
	}

	public ISegUsuarioLogic getSegUsuarioLogic() {
		return segUsuarioLogic;
	}

	public void setSegUsuarioLogic(ISegUsuarioLogic segUsuarioLogic) {
		this.segUsuarioLogic = segUsuarioLogic;
	}

	public ISegParametroLogic getSegParametroLogic() {
		return segParametroLogic;
	}

	public void setSegParametroLogic(ISegParametroLogic segParametroLogic) {
		this.segParametroLogic = segParametroLogic;
	}
	
}