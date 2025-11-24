package org.example.explorador_de_cosmos.services;

import org.example.explorador_de_cosmos.database.Conexion;
import org.example.explorador_de_cosmos.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioService {

    public Usuario verificarCredenciales(String username, String password) {
        String sql = "SELECT id_usuario, nombre_usuario, username, rol FROM usuarios WHERE username = ? AND password = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre_usuario"),
                            rs.getString("username"),
                            rs.getString("rol")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar credenciales: " + e.getMessage());
        }
        return null;
    }

    public boolean crearUsuario(String nombreUsuario, String username, String password) {
        String sql = "INSERT INTO usuarios (nombre_usuario, username, password) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, username);
            pstmt.setString(3, password);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }
}
