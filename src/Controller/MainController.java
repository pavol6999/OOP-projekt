package Controller;

import Model.Audio.AudioThread;
import Model.People.HumanFactory;
import Model.Main.Calculator;
import Model.People.Person;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


/** main controller of the main fxml file */
public class MainController {


    protected static ArrayList<Person> clients = new ArrayList<>();
    private final PersonController personController = new PersonController();
    private final Calculator calculator = new Calculator();
    private int years;
    protected static AudioThread mouseClicked = new AudioThread();

    @FXML
    private TextArea Output;

    @FXML
    private TextField RokyField;

    @FXML
    private TextField AgeField;

    @FXML
    private TextField NameField;



    /**
     * Button handler that handles the add client button. Gathers data from ageField and nameField and passes it into
     * HumanFactory, in factory it is determined what type of class the method should return.  After a client was created
     * successfully, pass the client into PersonController where further actions are handled.
     * @throws NumberFormatException if the age of the client is empty or incorrect
     * @throws Exception if the personController cant handle the client
     *
     */
    @FXML
    private void HandleButtonClient(){
        mouseClicked.run();
        Person temp_client = null;
        try {
            temp_client = HumanFactory.createBeing(NameField.getText(), Integer.parseInt(AgeField.getText()));
            if(temp_client==null)
            {
                Output.appendText("Deti nemôžu mať svoje vlastné produkty\n");
            }
        }
        catch(NumberFormatException e) {
            warning("invalid input in client age");
        }
        try {
            personController.handleClient(clients,temp_client);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NameField.clear();
        AgeField.clear();
    }

    /**
     * main button handler, first we initialize customers, get the integer that represents years and thanks to runnableI
     * + lambda +
     * thread print the output with a delay of 100ms onto the TextArea in the middle
     */
    @FXML
    private void calculate() {
        mouseClicked.run();
        calculator.initializeCustomers(clients);
        try {
            this.years = Integer.parseInt(RokyField.getText());
        }
        catch (RuntimeException e){
            warning("invalid input in number of simulated years");

        }
        // new runnable with lambda
        Runnable out = () -> {
            try {

                for (int i = 1; i <= years; i++) {
                    int finalI = i;
                    //need to update TextArea (gui component) from a nonGUI thread
                    //handled by gui thread asap
                    Platform.runLater(
                            () -> {
                                Output.appendText("Po "+ finalI +". roku: \n");
                                Output.appendText(calculator.simulateYear(clients));
                            });
                    //delay
                    Thread.sleep(100);
                }
                //after 100 ms, catch error and interrupt the thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();}


        };
        Thread delayed = new Thread(out);
        delayed.start();
    }

    /**
     * if a consumer does not know what a Commodity is, he can choose to display more information from to drop menu located
     * in the bottom left corner
     * @throws NullPointerException if fxml was not loaded properly
     */
    @FXML
    private void infoCommodity()
    {
        mouseClicked.run();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/Commodity.fxml"));
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            warning("FXML location is invalid");
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * if a consumer does not know what an Investment is, he can choose to display more information from to drop menu located
     * in the bottom left corner
     * @throws NullPointerException if fxml was not loaded properly
     */
    @FXML
    private void infoInvestment()  {
        mouseClicked.run();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/Investment.fxml"));
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            warning("FXML location is invalid");
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * if a consumer does not know what a Leasing is, he can choose to display more information from to drop menu located
     * in the bottom left corner
     * @throws NullPointerException if fxml was not loaded properly
     */
    @FXML
    private void infoLeasing() {
        mouseClicked.run();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/Leasing.fxml"));
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            warning("FXML location is invalid");
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * if a consumer does not know what a Loan is, he can choose to display more information from to drop menu located
     * in the bottom left corner
     * @throws NullPointerException if fxml was not loaded properly
     */
    @FXML
    private void infoLoan()  {
        mouseClicked.run();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../View/Loan.fxml"));
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            warning("FXML location is invalid");
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


    /** method that is called when an error is catched, create new AlertBox and display error
     * @param error error message
     * */
    protected static void warning(String error)
    {
        Alert warning = new Alert(Alert.AlertType.ERROR,error, ButtonType.CLOSE);
        warning.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        warning.show();
    }


}
