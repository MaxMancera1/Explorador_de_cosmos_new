package org.example.explorador_de_cosmos.launcher;

import javafx.application.Application;
import org.example.explorador_de_cosmos.database.Conexion;
import org.example.explorador_de_cosmos.HelloApplication;

public class Launcher {
    public static void main(String[] args) {
        Conexion.getConexion(); // Prueba conexión
        Application.launch(HelloApplication.class, args);
    }
}
