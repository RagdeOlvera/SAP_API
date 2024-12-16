package org.banxico.dds.proyectoweb.entity;

import java.time.LocalDateTime;

import jakarta.ejb.Local;

public class Process {

	private Integer processInstanceId;
	private String processInstanceUuId;
	private String processName;
	
	private Integer version;
	private String startedBy;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	private Integer status;

	public Integer getProcessInstanceId() {
		return processInstanceId;
	}
	
	public void setProcessInstanceId(Integer processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	public String getProcessInstanceUuId() {
		return processInstanceUuId;
	}

	public void setProcessInstanceUuId(String processInstanceUuId) {
		this.processInstanceUuId = processInstanceUuId;
	}

	public String getProcessName() {
		return processName;
	}
	
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getStartedBy() {
		return startedBy;
	}
	
	public void setStartedBy(String startedBy) {
		this.startedBy = startedBy;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public void setStartTime(LocalDateTime startTime) {
		this.startTime= startTime ;
	}
	
	public LocalDateTime getEndTime() {
		return endTime;
	}
	
	public void setEndTime(LocalDateTime endTime) {
		this.endTime= endTime ;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}