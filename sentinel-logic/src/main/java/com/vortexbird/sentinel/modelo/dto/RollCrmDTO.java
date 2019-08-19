package com.vortexbird.sentinel.modelo.dto;

import java.io.Serializable;
import java.util.List;

public class RollCrmDTO implements Serializable {

	
	private static final long serialVersionUID = 3700496186215743116L;
	
	private String status;
    private String status_message;
    private List<RollCrmDTO> data;
    
    private String id;
    private String name;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_message() {
		return status_message;
	}
	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
	public List<RollCrmDTO> getData() {
		return data;
	}
	public void setData(List<RollCrmDTO> data) {
		this.data = data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "RollCrmDTO ["
				+ (status != null ? "status=" + status + ", " : "")
				+ (status_message != null ? "status_message=" + status_message
						+ ", " : "")
				+ (data != null ? "data=" + data + ", " : "")
				+ (id != null ? "id=" + id + ", " : "")
				+ (name != null ? "name=" + name : "") + "]";
	}
    
    

    
	
}
