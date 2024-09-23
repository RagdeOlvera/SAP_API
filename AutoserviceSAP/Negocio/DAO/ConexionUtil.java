package org.Banxico.Proyecto1.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Clase utilitaria para gestionar la conexion a la BDD
**/

@ApplicationScoped

public class ConexionUtil {
	
	@Resource(lookup = "jdbc/ds/bpm_htp")
	private DataSource dataSource;

    public Connection obtenerConexion() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            throw new RuntimeException("Error al conectarse a la BDD");
        }
    }
}
