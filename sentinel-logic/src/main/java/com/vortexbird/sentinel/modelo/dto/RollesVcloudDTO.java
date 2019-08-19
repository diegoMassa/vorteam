package com.vortexbird.sentinel.modelo.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "registros")
@XmlAccessorType(XmlAccessType.FIELD)
public class RollesVcloudDTO  {

	@XmlElement(name = "row-perfiles")
	List<RollVcloudDTO> registros = null;

	public List<RollVcloudDTO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RollVcloudDTO> registros) {
		this.registros = registros;
	}
	
	

}
