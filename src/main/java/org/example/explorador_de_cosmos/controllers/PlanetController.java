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

    @FXML
    private void cargarApod() {
        Planet apod = PlanetService.obtenerApod();
        if (apod != null) {
            lblTitle.setText(apod.getTitle());
            lblDate.setText(apod.getDate());
            txtExplanation.setText(apod.getExplanation());

            if (apod.getUrl() != null && apod.getUrl().endsWith(".jpg")) {
                imgApod.setImage(new Image(apod.getUrl()));
            } else {
                imgApod.setImage(null);
            }
        } else {
            lblTitle.setText("Error al obtener la imagen del día.");
        }
    }
}
