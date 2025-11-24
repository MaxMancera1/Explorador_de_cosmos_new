package org.example.explorador_de_cosmos.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.explorador_de_cosmos.models.Usuario;
import org.example.explorador_de_cosmos.services.UsuarioService;
import org.example.explorador_de_cosmos.utils.SessionManager;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblError;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private void iniciarSesion() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        Usuario usuario = usuarioService.verificarCredenciales(username, password);

        if (usuario != null) {
            lblError.setText("");
            System.out.println("Login exitoso para: " + usuario.getNombreUsuario());
            SessionManager.getInstance().login(usuario); // Guardar sesión
            try {
                // Cargar la vista de planetas
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/planet.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Visor de Planetas");
            } catch (IOException e) {
                e.printStackTrace();
                lblError.setText("Error al cargar la siguiente vista.");
            }
        } else {
            lblError.setText("Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void registrarse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registro.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Registro de Nuevo Explorador");
        } catch (IOException e) {
            e.printStackTrace();
            lblError.setText("Error al abrir la vista de registro.");
        }
    }
}