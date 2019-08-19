package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;

public class CrmUsuarioDTO implements Serializable{

	private static final long serialVersionUID = 6874716656033531763L;
	private Integer status;
	private String status_message;
	private String data;
	
	public CrmUsuarioDTO() {
		super();
	}
	
	public CrmUsuarioDTO(Integer status, String status_message, String data) {
		super();
		this.status = status;
		this.status_message = status_message;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CrmUsuarioDTO ["+
				(status != null ? "status=" + status + ", " : "")+
				(status_message != null ? "status_message=" + status_message+ ", " : "") +
				(data != null ? "data=" + data : "")+
				"]";
	}
	
	
}
