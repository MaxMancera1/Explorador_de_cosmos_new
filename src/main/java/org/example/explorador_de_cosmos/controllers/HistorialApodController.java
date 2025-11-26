package org.example.explorador_de_cosmos.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.explorador_de_cosmos.models.Planet;
import org.example.explorador_de_cosmos.services.PlanetService;

import java.util.List;

public class HistorialApodController {

    @FXML private TableView<Planet> tablaApod;
    @FXML private TableColumn<Planet, String> colFecha;
    @FXML private TableColumn<Planet, String> colTitulo;
    @FXML private TableColumn<Planet, String> colTipo;

    @FXML
    public void initialize() {
        colFecha.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDate()));
        colTitulo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTitle()));
        colTipo.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getMediaType()));

        recargarTabla();
    }

    @FXML
    private void recargarTabla() {
        List<Planet> lista = PlanetService.obtenerApodsGuardados();
        ObservableList<Planet> datos = FXCollections.observableArrayList(lista);
        tablaApod.setItems(datos);
    }

    @FXML
    private void verDetalles() {
        Planet apod = tablaApod.getSelectionModel().getSelectedItem();
        if (apod == null) {
            mostrarAlerta("Selecciona un elemento primero");
            return;
        }

        mostrarAlerta(
                "Título: " + apod.getTitle() + "\n" +
                        "Fecha: " + apod.getDate() + "\n\n" +
                        apod.getExplanation()
        );
    }

    @FXML
    private void generarPDFSemana() {
        mostrarAlerta("Esta función se implementará en el PASO 2 (PDF semana).");
    }

    @FXML
    private void volver() {
        mostrarAlerta("Pantalla anterior aún no conectada (lo hacemos si quieres).");
    }

    private void mostrarAlerta(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
