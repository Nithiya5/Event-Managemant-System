package com.example.demo1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loginfxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Signup.fxml"));

        Scene firstscene = new Scene(loginfxmlLoader.load());

        stage.setTitle("SignUp");
        stage.setScene(firstscene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}