package org.example.explorador_de_cosmos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.explorador_de_cosmos.models.Planet;
import org.example.explorador_de_cosmos.services.PlanetService;

public class PlanetController {

    @FXML private ImageView imgApod;
    @FXML private Label lblTitle;
    @FXML private Label lblDate;
    @FXML private TextArea txtExplanation;

    private Planet apodActual;

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
            mostrarMensaje("Error al obtener el APOD.");
        }
    }

    @FXML
    private void guardarApodEnBD() {
        if (apodActual == null) {
            mostrarMensaje("Primero carga un APOD.");
            return;
        }

        boolean guardado = PlanetService.guardarApodEnBD(apodActual);

        if (guardado) {
            mostrarMensaje("APOD guardado en base de datos.");
        } else {
            mostrarMensaje("No se pudo guardar el APOD.");
        }
    }

    @FXML
    private void descargarImagen() {
        if (apodActual == null) {
            mostrarMensaje("Primero carga un APOD.");
            return;
        }

        String ruta = PlanetService.descargarImagen(apodActual);

        if (ruta != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Imagen descargada");
            alert.setHeaderText("La imagen fue guardada correctamente.");
            alert.setContentText(ruta);

            ButtonType abrir = new ButtonType("Abrir carpeta");
            ButtonType ok = new ButtonType("Aceptar", ButtonBar.ButtonData.OK_DONE);

            alert.getButtonTypes().setAll(abrir, ok);

            alert.showAndWait().ifPresent(response -> {
                if (response == abrir) {
                    try {
                        String folder = ruta.substring(0, ruta.lastIndexOf("/"));
                        Runtime.getRuntime().exec(new String[]{"open", folder});
                    } catch (Exception e) {
                        mostrarMensaje("No se pudo abrir la carpeta.");
                    }
                }
            });

        } else {
            mostrarMensaje("Error al descargar la imagen.");
        }
    }

    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void abrirHistorial() {
        mostrarMensaje("Pantalla de historial en construcción.");
    }
}
