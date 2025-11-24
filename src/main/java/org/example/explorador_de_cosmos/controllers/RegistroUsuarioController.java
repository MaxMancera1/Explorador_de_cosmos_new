package org.example.explorador_de_cosmos.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.explorador_de_cosmos.launcher.UsuarioService;

public class RegistroUsuarioController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private void registrarUsuario() {

        String nombre = txtNombre.getText().trim();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        if (nombre.isEmpty() || username.isEmpty() || password.isEmpty()) {
            lblError.setText("Todos los campos son obligatorios.");
            return;
        }

        boolean exito = usuarioService.registrarUsuario(nombre, username, password);

        if (exito) {
            volverAlLogin();
        } else {
            lblError.setText("Error al registrar. El usuario podría ya existir.");
        }
    }

    @FXML
    private void volverAlLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Explorador del Cosmos - Login");

        } catch (Exception e) {
            lblError.setText("Error al volver al login.");
            e.printStackTrace();
        }
    }
}
