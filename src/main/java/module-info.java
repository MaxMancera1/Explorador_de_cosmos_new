module org.example.explorador_de_cosmos {
    // Módulos externos que el proyecto necesita
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires mysql.connector.j;

    // Permite que JavaFX FXML acceda a los controladores
    opens org.example.explorador_de_cosmos.controllers to javafx.fxml;
    opens org.example.explorador_de_cosmos to javafx.fxml;

    // Expone los paquetes para que sean utilizados por la aplicación
    exports org.example.explorador_de_cosmos;
    exports org.example.explorador_de_cosmos.launcher;
    exports org.example.explorador_de_cosmos.models;
    exports org.example.explorador_de_cosmos.services;
    exports org.example.explorador_de_cosmos.utils;
}