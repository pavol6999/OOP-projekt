package Model.Main;

import Controller.MainController;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;


public class CalculatorGUI extends Application {


    /** start of the application, if no fxml was found, show warning */
    public void start(Stage stage) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/View/MainGUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,640,400);
        stage.setTitle("Financial calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
