package com.vortexbird.vorteam.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.dto.VtFestivosDTO;
import com.vortexbird.vorteam.repository.IVtFestivosRepository;
import com.vortexbird.vorteam.repository.VtFestivosRepository;
import com.vortexbird.vorteam.utility.Fechas;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Scope("singleton")
@Service
public class VtFestivosServiceImpl implements VtFestivosService {
    private static final Logger log = LoggerFactory.getLogger(VtFestivosServiceImpl.class);

    @Autowired
    private VtFestivosRepository vtFestivosRepository;
    @Autowired
    private IVtFestivosRepository vtFestivosRepositoryImpl;
    
	/**
	 * @author Daniel Pareja Londoño
	 * @version sept. 19, 2018
	 *
	 * (non-Javadoc)
	 * @see com.vortexbird.vorteam.service.VtFestivosService#esFestivo(java.util.Date)
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean esFestivo(Date fecha) throws Exception {
		try {
			Date fechaPrimeraHoraDelDia = Fechas.moverHastaUnaHoraMinutoSegundo(fecha, 0, 0, 0);
			List<VtFestivosDTO> festivos = vtFestivosRepositoryImpl.buscarEnFestivosPorFecha(fechaPrimeraHoraDelDia);
			if(!festivos.isEmpty()) {
				return true;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return false;
	}

}
