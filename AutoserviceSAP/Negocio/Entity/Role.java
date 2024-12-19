package org.banxico.dds.proyectoweb.entity;

public class Role{

	private String activityId;
	private String role;
	private String cve_empleado;
	private String processInstanceUuId;
	private String SID;
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}	
	public String getCve_empleado() {
		return cve_empleado;
	}
	public void setCve_empleado(String cve_empleado) {
		this.cve_empleado = cve_empleado;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public String getFolio() {
		return processInstanceUuId;
	}
	public void setFolio(String processInstanceUuId) {
		this.processInstanceUuId = processInstanceUuId;
	}
}
