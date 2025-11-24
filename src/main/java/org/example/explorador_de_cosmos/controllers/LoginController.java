package org.example.explorador_de_cosmos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.explorador_de_cosmos.launcher.UsuarioService;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String user = txtUsername.getText();
        String pass = txtPassword.getText();

        boolean valido = usuarioService.validarLogin(user, pass);

        if (valido) {
            abrirPantallaPrincipal();
        } else {
            mostrarAlerta("Usuario o contraseña incorrectos");
        }
    }

    @FXML
    private void registrarse() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RegistroExplorador.xml"));
            Parent root = loader.load();

            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Crear cuenta - Explorador del Cosmos");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al abrir la pantalla de registro.");
        }
    }

    private void abrirPantallaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/planet.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Explorador del Cosmos");
            stage.setScene(scene);
            stage.show();

            // Cerrar login
            Stage actual = (Stage) txtUsername.getScene().getWindow();
            actual.close();

        } catch (Exception e) {
            System.out.println("❌ Error al abrir pantalla principal: " + e.getMessage());
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
