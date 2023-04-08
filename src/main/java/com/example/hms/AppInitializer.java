package com.example.hms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.IOException;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = new Scene(new FXMLLoader(AppInitializer.class.getResource("view/MainForm.fxml")).load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("D24 - Hostel Management System");
        primaryStage.getIcons().add(new Image("com/example/hms/assets/logo.png"));
        primaryStage.show();
    }
}