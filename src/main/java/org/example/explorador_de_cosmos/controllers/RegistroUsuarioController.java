package org.example.explorador_de_cosmos.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.explorador_de_cosmos.services.UsuarioService;

import java.io.IOException;

public class RegistroUsuarioController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private void registrarUsuario() {
        String nombre = txtNombre.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (nombre.isEmpty() || username.isEmpty() || password.isEmpty()) {
            lblError.setText("Todos los campos son obligatorios.");
            return;
        }

        boolean exito = usuarioService.crearUsuario(nombre, username, password);

        if (exito) {
            System.out.println("Usuario registrado con éxito.");
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Explorador del Cosmos - Login");
        } catch (IOException e) {
            e.printStackTrace();
            lblError.setText("Error al volver al login.");
        }
    }
}
