package org.banxico.dds.proyectoweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.banxico.dds.proyectoweb.entity.Empleado;
import org.banxico.dds.proyectoweb.entity.Task;

import jakarta.inject.Inject;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TaskDao {

	@Inject
	ConexionUtil conexionUtil;

	public List<Task> getLike(String processInstanceUuId) { // Metodo para hacer un Select de las tareas por el folio

		String query = "SELECT taskId, processInstanceUuId, name, actualOwner, completionTime, TaskStatus FROM task WHERE ProcessInstanceUuId = ? ORDER BY taskid ASC";

		List<Task> tareas = new ArrayList<>();
		Task tarea = null; // Inicializa actor en null

		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);

			statement.setString(1, processInstanceUuId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				tarea = new Task();
				tarea.setTaskId(rs.getInt("taskId"));
				tarea.setProcessInstanceUuId(rs.getString("processInstanceUuId"));
				tarea.setName(rs.getString("name"));
				
				//Cambia la vista de SID a cve_Empleado
				String sid = rs.getString("actualOwner");
				String cve = obtenerCve(sid).getCve_empleado();
				tarea.setActualOwner(cve); 
				
				tarea.setTaskStatus(rs.getInt("TaskStatus"));

				tareas.add(tarea);
			}

			rs.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tareas;
	}
	
	public Empleado obtenerCve(String sid_empleado) {

		String query = "SELECT cve_empleado FROM identidad WHERE sid = ?";
		Empleado empleado = new Empleado();

		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);

			statement.setString(1, sid_empleado);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				empleado.setCve_empleado(rs.getString("cve_empleado"));
				empleado.setSID(sid_empleado);
			}

			rs.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empleado;
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
		return empleado;
	}

	public void actualizarTarea(Task tarea)// cambiar a void una vez terminado las pruebas
	{
		Integer id = tarea.getTaskId();
		Integer validar = 2;

		if (validar == tarea.getTaskStatus() && id != null && tarea.getTaskStatus() != null) { // status = 1

			String query = "UPDATE task SET actualOwner = null, taskStatus = ? WHERE taskId = ?";

			try {
				Connection conexion = conexionUtil.obtenerConexion();
				PreparedStatement statement = conexion.prepareStatement(query);

				statement.setInt(1, tarea.getTaskStatus());
				statement.setInt(2, id);

				statement.executeUpdate();

				statement.close();
				conexion.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Error al actualizar la tarea con ID " + id);
				e.printStackTrace();
			}

		} else if (id != null && tarea.getActualOwner() != null && !tarea.getActualOwner().isEmpty()
				&& tarea.getTaskStatus() != null) {

			String query = "UPDATE task SET actualOwner = ?, taskStatus = ? WHERE taskId = ?";

			try {
				Connection conexion = conexionUtil.obtenerConexion();
				PreparedStatement statement = conexion.prepareStatement(query);

				statement.setString(1, tarea.getActualOwner());
				statement.setInt(2, tarea.getTaskStatus());
				statement.setInt(3, id);

				statement.executeUpdate();

				statement.close();
				conexion.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Error al actualizar la tarea con ID " + id);
				e.printStackTrace();
			}
		} 
	}
	
	public String obtenerTaskIds(String cadenaFolios) { //Obtiene TaskIds de todos los Folios como String
		
		String query = "SELECT min(taskId) as taskId FROM task WHERE processInstanceUuId in (?) group by processInstanceUuId";
		String taskId = "";
		StringBuilder taskIds = new StringBuilder();

		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);

			statement.setString(1, cadenaFolios);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				taskId = rs.getString("taskId");
				if(taskId != null) {
			        if (taskIds.length() > 0) { // Si ya hay elementos en el StringBuilder
			            taskIds.append(", ");   // Añadimos la coma antes del siguiente elemento
			        }
					taskIds.append("'").append(taskId).append("'");
				}
			}			
			
			rs.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return taskIds.toString();
		
	}
	
	public String verificarPermisos(String taskIds, String sid) { //Devuelve TaskIds que ya tienen permisos como String
		
		String query = "SELECT activityId FROM role_cache rc "
					+ "WHERE activityId in (?) "
					+ "AND roleName = 'recipients' "
					+ "AND SID = ?;";
		
		String taskId = "";
		StringBuilder idsDescartar = new StringBuilder();
		
		 try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);

			statement.setString(1, taskIds);
			statement.setString(2, sid);
			
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				taskId = rs.getString("taskId");
				if(taskId != null) {
			        if (idsDescartar.length() > 0) { // Si ya hay elementos en el StringBuilder
			        	idsDescartar.append(", ");   // Añadimos la coma antes del siguiente elemento
			        }
			        idsDescartar.append("'").append(taskId).append("'");
				}
			}			
			
			rs.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idsDescartar.toString();
		
	}

	public List<String> verificarInserts(String taskIds, String sid, String idsDescartar) { // Devuelve TaskIds que NO tienen permisos como arreglo

		String query = "SELECT activityId "
				+ "FROM role_cache rc " 
				+ "WHERE activityId in (?) "
				+ "AND roleName = 'recipients' " 
				+ "AND SID = ?;" 
				+ "AND activityId NOT IN (?)";

		String taskId = "";
		List<String> idsFiltrados = new ArrayList<String>();

		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);

			statement.setString(1, taskIds);
			statement.setString(2, sid);
			statement.setString(3, idsDescartar);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				taskId = rs.getString("taskId");
				if (taskId != null) {
					idsFiltrados.add(taskId); // Agregamos directamente al arreglo
				}
			}
			rs.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idsFiltrados;

	}

	public void asignarPermisos(String taskId, String sid) { // Inserta los permisos que faltan

		String query = "INSERT INTO role_cache (activityId, roleName, SID) values (?, recipients, ?)";
		
		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);

			statement.setString(1, taskId);
			statement.setString(2, sid);

			statement.executeUpdate();

			statement.close();
			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Error al actualizar la tarea con ID " + taskId);
			e.printStackTrace();
		}
	
	}
}
