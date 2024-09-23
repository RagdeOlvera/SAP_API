package org.Banxico.Proyecto1.entity;

public class Process {


	private String processInstanceUuId;
	private String processName;
	private Integer status;

  public String getProcessInstanceUuId(){
    return processInstanceUuId;
  }
  public void setProcessInstanceUuId(String processInstanceUuId){
      this.processInstanceUuId = processInstanceUuId;
  }
  public String getProcessName(){
    return processName;
  }
  public void setProcessName(String processName){
      this.processName = processName;
  }
  public Integer getStatus(){
    return status;
  }
  public void setStatus(Integer status){
      this.status = status;
  }
}
