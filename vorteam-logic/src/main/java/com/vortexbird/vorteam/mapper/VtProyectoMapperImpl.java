package com.vortexbird.vorteam.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.domain.VtCliente;
import com.vortexbird.vorteam.domain.VtLineaNegocio;
import com.vortexbird.vorteam.domain.VtProyecto;
import com.vortexbird.vorteam.dto.VtProyectoDTO;
import com.vortexbird.vorteam.service.VtClienteService;
import com.vortexbird.vorteam.service.VtLineaNegocioService;

/**
 * @author Zathura Code Generator http://zathuracode.org/ www.zathuracode.org
 *
 */
@Component
@Scope("singleton")
public class VtProyectoMapperImpl implements VtProyectoMapper {
	private static final Logger log = LoggerFactory.getLogger(VtProyectoMapperImpl.class);

	/**
	 * Service injected by Spring that manages VtCliente entities
	 *
	 */
	@Autowired
	VtClienteService serviceVtCliente1;
	@Autowired
    VtLineaNegocioService serviceVtLineaNegocio1;
	

	@Transactional(readOnly = true)
	public VtProyectoDTO vtProyectoToVtProyectoDTO(VtProyecto vtProyecto) throws Exception {
		try {
			VtProyectoDTO vtProyectoDTO = new VtProyectoDTO();

			vtProyectoDTO.setProyId(vtProyecto.getProyId());
			vtProyectoDTO.setActivo((vtProyecto.getActivo() != null) ? vtProyecto.getActivo() : null);
			vtProyectoDTO.setCostoTotal((vtProyecto.getCostoTotal() != null) ? vtProyecto.getCostoTotal() : null);
			vtProyectoDTO.setFechaCreacion(vtProyecto.getFechaCreacion());
			vtProyectoDTO.setFechaModificacion(vtProyecto.getFechaModificacion());
			vtProyectoDTO.setNombreProyecto(
					(vtProyecto.getNombreProyecto() != null) ? vtProyecto.getNombreProyecto() : null);
			vtProyectoDTO.setUsuaCreador((vtProyecto.getUsuaCreador() != null) ? vtProyecto.getUsuaCreador() : null);
			vtProyectoDTO.setUsuaModificador(
					(vtProyecto.getUsuaModificador() != null) ? vtProyecto.getUsuaModificador() : null);
			vtProyectoDTO.setClieId_VtCliente(
					(vtProyecto.getVtCliente().getClieId() != null) ? vtProyecto.getVtCliente().getClieId() : null);
			vtProyectoDTO.setLineId_VtLineaNegocio(
					(vtProyecto.getVtLineaNegocio().getLineId() != null) ? vtProyecto.getVtLineaNegocio().getLineId()
							: null);

			return vtProyectoDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public VtProyecto vtProyectoDTOToVtProyecto(VtProyectoDTO vtProyectoDTO) throws Exception {
		try {
			VtProyecto vtProyecto = new VtProyecto();

			vtProyecto.setProyId(vtProyectoDTO.getProyId());
			vtProyecto.setActivo((vtProyectoDTO.getActivo() != null) ? vtProyectoDTO.getActivo() : null);
			vtProyecto.setCostoTotal((vtProyectoDTO.getCostoTotal() != null) ? vtProyectoDTO.getCostoTotal() : null);
			vtProyecto.setFechaCreacion(vtProyectoDTO.getFechaCreacion());
			vtProyecto.setFechaModificacion(vtProyectoDTO.getFechaModificacion());
			vtProyecto.setNombreProyecto(
					(vtProyectoDTO.getNombreProyecto() != null) ? vtProyectoDTO.getNombreProyecto() : null);
			vtProyecto.setUsuaCreador((vtProyectoDTO.getUsuaCreador() != null) ? vtProyectoDTO.getUsuaCreador() : null);
			vtProyecto.setUsuaModificador(
					(vtProyectoDTO.getUsuaModificador() != null) ? vtProyectoDTO.getUsuaModificador() : null);

			VtCliente vtCliente = new VtCliente();

			if (vtProyectoDTO.getClieId_VtCliente() != null) {
				vtCliente = serviceVtCliente1.getVtCliente(vtProyectoDTO.getClieId_VtCliente());
			}

			if (vtCliente != null) {
				vtProyecto.setVtCliente(vtCliente);
			}
			
			VtLineaNegocio vtLineaNegocio = new VtLineaNegocio();

            if (vtProyectoDTO.getLineId_VtLineaNegocio() != null) {
                vtLineaNegocio = serviceVtLineaNegocio1.getVtLineaNegocio(vtProyectoDTO.getLineId_VtLineaNegocio());
            }

            if (vtLineaNegocio != null) {
                vtProyecto.setVtLineaNegocio(vtLineaNegocio);
            }

			return vtProyecto;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<VtProyectoDTO> listVtProyectoToListVtProyectoDTO(List<VtProyecto> listVtProyecto) throws Exception {
		try {
			List<VtProyectoDTO> vtProyectoDTOs = new ArrayList<VtProyectoDTO>();

			for (VtProyecto vtProyecto : listVtProyecto) {
				VtProyectoDTO vtProyectoDTO = vtProyectoToVtProyectoDTO(vtProyecto);

				vtProyectoDTOs.add(vtProyectoDTO);
			}

			return vtProyectoDTOs;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public List<VtProyecto> listVtProyectoDTOToListVtProyecto(List<VtProyectoDTO> listVtProyectoDTO) throws Exception {
		try {
			List<VtProyecto> listVtProyecto = new ArrayList<VtProyecto>();

			for (VtProyectoDTO vtProyectoDTO : listVtProyectoDTO) {
				VtProyecto vtProyecto = vtProyectoDTOToVtProyecto(vtProyectoDTO);

				listVtProyecto.add(vtProyecto);
			}

			return listVtProyecto;
		} catch (Exception e) {
			throw e;
		}
	}
}
