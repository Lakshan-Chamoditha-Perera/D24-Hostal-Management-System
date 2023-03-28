package com.example.hms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch();
    }
//    /home/shan/Documents/JavaFX/openjfx-19.0.2.1_linux-x64_bin-sdk/javafx-sdk-17.0.6/lib

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(new FXMLLoader(AppInitializer.class.getResource("view/MainForm.fxml")).load()));
        primaryStage.setTitle("Hostal Management System");
        primaryStage.show();
    }
}