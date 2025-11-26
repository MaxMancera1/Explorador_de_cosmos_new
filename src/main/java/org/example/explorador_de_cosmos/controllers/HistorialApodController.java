package org.example.explorador_de_cosmos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.explorador_de_cosmos.models.Planet;
import org.example.explorador_de_cosmos.services.PlanetService;

public class HistorialApodController {

    @FXML private ListView<String> listaApods;

    @FXML
    public void initialize() {
        listaApods.getItems().clear();

        for (Planet apod : PlanetService.obtenerApodsGuardados()) {
            listaApods.getItems().add(apod.getDate() + " — " + apod.getTitle());
        }
    }
}
