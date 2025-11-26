package org.example.explorador_de_cosmos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.example.explorador_de_cosmos.models.Planet;
import org.example.explorador_de_cosmos.services.PlanetService;
import org.example.explorador_de_cosmos.services.ApodService;

public class PlanetController {

    @FXML private ImageView imgApod;
    @FXML private Label lblTitle;
    @FXML private Label lblDate;
    @FXML private TextArea txtExplanation;

    private Planet apodActual;                     // Guarda el APOD cargado actualmente
    private final ApodService apodService = new ApodService();

    // ----------------------- CARGAR APOD -----------------------
    @FXML
    private void cargarApod() {
        apodActual = PlanetService.obtenerApod();

        if (apodActual != null) {

            lblTitle.setText(apodActual.getTitle());
            lblDate.setText(apodActual.getDate());
            txtExplanation.setText(apodActual.getExplanation());

            if (apodActual.getUrl() != null && apodActual.getUrl().endsWith(".jpg")) {
                imgApod.setImage(new Image(apodActual.getUrl()));
            } else {
                imgApod.setImage(null);
            }

        } else {
            mostrarAlerta("No se pudo obtener la imagen del día.");
        }
    }

    // ----------------------- GUARDAR APOD EN BD -----------------------
    @FXML
    private void guardarApod() {
        if (apodActual == null) {
            mostrarAlerta("Primero carga un APOD.");
            return;
        }

        boolean ok = apodService.guardarApod(apodActual);

        if (ok)
            mostrarAlerta("APOD guardado exitosamente en la base de datos.");
        else
            mostrarAlerta("Error al guardar APOD.");
    }

    // ----------------------- DESCARGAR IMAGEN -----------------------
    @FXML
    private void descargarImagen() {
        if (apodActual == null || apodActual.getUrl() == null) {
            mostrarAlerta("No hay imagen para descargar.");
            return;
        }

        try {
            String destino = "apod_" + apodActual.getDate().replace("-", "_") + ".jpg";
            boolean ok = apodService.descargarImagen(apodActual.getUrl(), destino);

            if (ok)
                mostrarAlerta("Imagen descargada en el archivo: " + destino);
            else
                mostrarAlerta("Error al descargar imagen.");

        } catch (Exception e) {
            mostrarAlerta("Error: " + e.getMessage());
        }
    }

    // ----------------------- PDF SEMANA (placeholder) -----------------------
    @FXML
    private void generarPdfSemana() {
        mostrarAlerta("PDF de la última semana será implementado en el siguiente paso 🚀.");
    }

    // ----------------------- ALERTAS -----------------------
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
