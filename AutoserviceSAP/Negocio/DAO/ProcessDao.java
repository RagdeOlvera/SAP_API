package org.Banxico.Proyecto1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Banxico.Proyecto1.entity.Actor;

public class ProcessDao {


/*
	public static void save(Actor actor) { //metodo para hacer INSERT

		String query = "INSERT INTO actor("
				+ "first_name, last_name)"
				+ "values(?, ?)"; //se mandan a llamar a los statements que estan mas abajo en el codigo...


		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(query); //statements se solicictan arriba en el codigo en los values...
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());

			statement.executeUpdate();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	public static List<Process> getLike(String processInstanceUuId) { //Metodo para hacer un Select

		String query = "SELECT * FROM Process_Instance WHERE ProcessInstanceUuId like ? ";


		List<Process> procesos = new ArrayList<>();
		Process proceso = null; //Inicializa actor en null

		try {
			Connection connection = ConnectionUtil.getConnection();
      PreparedStatement statement = connection.prepareStatement(query);

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
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return procesos;

	}
	/*
	public static void update(Actor actor) { //Metodo para hacer un Update con una referencia de id

		String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";

		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setInt(3, actor.getActorId());

			statement.executeUpdate();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void delete(Integer actor_id) { //Metodo para hacer un Delete con una referencia de actor_id

		String query = "DELETE FROM actor WHERE actor_id = ?";


		Connection connection = ConnectionUtil.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, actor_id);

			statement.executeUpdate();

			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public static Actor findById(Integer actor_id) {

		String query = "SELECT * FROM actor WHERE actor_id = ?";
		Actor actor = new Actor();

		try {

			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, actor_id);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				actor.setActorId(rs.getInt("actor_id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}

			statement.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;


	}*/

}
