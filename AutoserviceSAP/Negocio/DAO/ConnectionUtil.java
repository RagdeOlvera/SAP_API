package org.Banxico.Proyecto1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase utilitaria para gestionar la conexion a la BDD
**/

@Resource(lookup = "jdbc/ds/bpm_htp")
DataSource dataSource;

public class ConnectionUtil {

    private static Connection obtenerConexion() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            throw new RuntimeException("Error al conectarse a la BDD");
        }
    }
