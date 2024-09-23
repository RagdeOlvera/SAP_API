package org.Banxico.Proyecto1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.inject.Inject;

import org.Banxico.Proyecto1.entity.Process;

public class ProcessDao {
	
	@Inject
    ConexionUtil conexionUtil;
	
	public List<Process> getLike(String processInstanceUuId) { //Metodo para hacer un Select

		String query = "SELECT * FROM Process_Instance WHERE ProcessInstanceUuId like ? ";


		List<Process> procesos = new ArrayList<>();
		Process proceso = null; //Inicializa actor en null

		try {
			Connection conexion = conexionUtil.obtenerConexion();
			PreparedStatement statement = conexion.prepareStatement(query);
		
			statement.setString(1, "%" + processInstanceUuId + "%");
		
			ResultSet rs = statement.executeQuery();


			while (rs.next()) {
				proceso = new Process();
				proceso.setProcessInstanceUuId(rs.getString("processInstanceUuId"));
				proceso.setProcessName(rs.getString("processName"));
				proceso.setStatus(rs.getInt("status"));

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
}
