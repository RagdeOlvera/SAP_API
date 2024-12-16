package org.banxico.dds.proyectoweb.entity;

import java.time.LocalDateTime;

import jakarta.ejb.Local;

public class Process_History {

	private Integer processInstanceId;
	private String eventType;
	private String processName;
	private LocalDateTime eventTime;
	private String user;
	private String data;

	public Integer getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(Integer processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public void setEventTime(LocalDateTime eventTime) {
		this.eventTime = eventTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}