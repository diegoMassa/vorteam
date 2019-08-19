package com.vortexbird.vorteam.view.lazydatamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vortexbird.vorteam.dto.VtActividadDTO;
import com.vortexbird.vorteam.view.BusinessDelegator;

public class MisActividadesDataModeler extends LazyDataModel<VtActividadDTO>  { 									

	private static final long serialVersionUID = 1L;

	private String usuario, activo, sprint, casoSoporte, controlCambios;
	private Long proyId, estaId, tiacId; 
	
	private List<VtActividadDTO> dataSource;
	private int rowCount = 0;
	
	private static final Logger log = LoggerFactory.getLogger(MisActividadesDataModeler.class);
	private BusinessDelegator businessDelegator;
	
	public MisActividadesDataModeler(String usuario, String activo, Long proyId, Long estaId, Long tiacId, String sprint, String casoSoporte, String controlCambios, BusinessDelegator businessDelegator) {
		super();
		this.businessDelegator = businessDelegator;
		this.usuario = usuario;
		this.activo = activo;
		this.sprint = (sprint == null || sprint.trim().equals("")) ? "-1" : sprint;
		this.casoSoporte = (casoSoporte == null || casoSoporte.trim().equals("")) ? "-1" : casoSoporte;
		this.controlCambios = (controlCambios == null || controlCambios.trim().equals("")) ? "-1" : controlCambios;
		this.proyId = (proyId == null ) ? -1 : proyId;
		this.estaId = (estaId == null ) ? -1 : estaId;
		this.tiacId = (tiacId == null ) ? -1 : tiacId;
	}
	
	@Override
	public VtActividadDTO getRowData(String rowKey) {
		for (VtActividadDTO dto : dataSource) {
			if (dto.getActiId().toString().equals(rowKey))
				return dto;
		}
		return null;
	}
	
	@Override
	public Object getRowKey(VtActividadDTO dto) {
		return dto.getActiId();
	}
	
	@Override
	public int getRowCount() {
		try {
			return rowCount;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return 0;
	}
	
	@Override
	public List<VtActividadDTO> load(int first, int pageSize,
			String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		dataSource = new ArrayList<VtActividadDTO>();
		try {
			rowCount = businessDelegator.consultaTotalMisActividadesLazy(usuario, activo, proyId, estaId, tiacId, sprint, casoSoporte, controlCambios).intValue();
			dataSource = businessDelegator.consultaMisActividadesLazy(usuario, activo, proyId, estaId, tiacId, sprint, casoSoporte, controlCambios, first, pageSize);
			
			if (dataSource == null) {
				this.setRowCount(0);
			}

			int dataSize = dataSource.size();
			this.setRowCount(dataSize);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return dataSource;
	}
	
}
