package com.jaroso;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Opción 2: utilizando plantillas FXML (Recomendable)
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("index-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Proyecto Hibernate");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        System.out.println("init() method: " + Thread.currentThread().getName());
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop() method: " + Thread.currentThread().getName());
    }


    public static void main(String[] args) {


        //JAVAFX
        launch();


    }


}
