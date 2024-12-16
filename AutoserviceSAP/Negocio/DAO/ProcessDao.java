package org.banxico.dds.proyectoweb.dao;

import org.banxico.dds.proyectoweb.entity.Empleado;
import org.banxico.dds.proyectoweb.entity.Process;
import org.banxico.dds.proyectoweb.entity.Process_History;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class ProcessDao {
	
	@Inject
    ConexionUtil conexionUtil;
	
	public List<Process> getLike(String processInstanceUuId) { //Metodo para hacer un Select

		String query = "SELECT processInstanceId, processinstanceuuid, processname, status_ FROM Process_Instance WHERE status_ = 1 and ProcessInstanceUuId like ? ";


		List<Process> procesos = new ArrayList<>();
		Process proceso = null; //Inicializa actor en null

		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);
		
			statement.setString(1, "%" + processInstanceUuId + "%");
		
			ResultSet rs = statement.executeQuery();


			while (rs.next()) {
				proceso = new Process();
				proceso.setProcessInstanceId(rs.getInt("processInstanceId"));
				proceso.setProcessInstanceUuId(rs.getString("processinstanceuuid"));
				proceso.setProcessName(rs.getString("processname"));
				proceso.setStatus(rs.getInt("status_"));

				procesos.add(proceso);
			}

			rs.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return procesos;

	}


	public void actualizarEstado(Process proceso) { //Metodo para hacer un Update con una referencia de id
		
		String query = "UPDATE process_instance SET status_ = ? , endTime = ? WHERE processInstanceId = ?";
		
		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);
			
			
			statement.setInt(1, proceso.getStatus());
			
			
			statement.setTimestamp(2, java.sql.Timestamp.valueOf(proceso.getEndTime()));
			
			
			statement.setInt(3, proceso.getProcessInstanceId());
			
			statement.executeUpdate();
			
			statement.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	
	public void registrarCambio(Process_History history) { //Metodo para hacer un Update con una referencia de id
		
		String query = "INSERT INTO process_instance_history (processInstanceId, eventType, eventTime, user, data_) VALUES ( ?, ?, ?, ?, ?)";
		
		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);
			
			statement.setInt(1, history.getProcessInstanceId());
			statement.setString(2, history.getEventType());
			statement.setTimestamp(3, java.sql.Timestamp.valueOf(history.getEventTime()));
			statement.setString(4, history.getUser());
			statement.setString(5, history.getData());
			
			statement.executeUpdate();
			
			statement.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Empleado obtenerSID(String cve_empleado) {
		
		String query = "SELECT sid FROM identidad WHERE cve_empleado = ?";
		Empleado empleado = new Empleado();
		
		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);
	
			statement.setString(1, cve_empleado);
	
			ResultSet rs = statement.executeQuery();
	
			if (rs.next()) {
				empleado.setSID(rs.getString("sid"));
				empleado.setCve_empleado(cve_empleado);
			} 
			
			rs.close();
			conexion.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empleado ;
	}
}