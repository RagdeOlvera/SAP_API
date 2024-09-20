package org.banxico.dds.proyectoweb;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
 
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
@ApplicationScoped
public class DatabaseUtil {
 
    @Resource(lookup = "jdbc/ds/bpm_htp")
    DataSource dataSource;
 
    public String getVersion() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM process_ WHERE processName = 'SancionesV4'");) {
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
    
    public String updateName() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE process_ SET description = 'SANCIONESV4' WHERE processName = 'SancionesV4'");) {
            int resultSet = ps.executeUpdate();
            
            return "Registros actualizadoS:"+resultSet;
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }
}