package org.banxico.dds.proyectoweb.entity;

import java.time.LocalDateTime;

public class Task {

	private Integer taskId;
	private String processInstanceUuId;
	private String name;
	private String actualOwner;
	private LocalDateTime completionTime;
	private Integer taskStatus;
	
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getProcessInstanceUuId() {
		return processInstanceUuId;
	}
	public void setProcessInstanceUuId(String processInstanceUuId) {
		this.processInstanceUuId = processInstanceUuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActualOwner() {
		return actualOwner;
	}
	public void setActualOwner(String actualOwner) {
		this.actualOwner = actualOwner;
	}
	public LocalDateTime getCompletionTime() {
		return completionTime;
	}
	public void setCompletionTime(LocalDateTime completionTime) {
		this.completionTime = completionTime;
	}
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	
	
}
