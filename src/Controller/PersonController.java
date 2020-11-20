package Controller;

import Model.Products.ProductFactory;
import Model.People.*;
import Model.Products.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;

/**
 * controller for each of three main person classes, it is not recommended to use one controller for two or more fxml files
 * , but I thought it might not be a bad idea
 */
public class PersonController {
    ProductController productController = new ProductController();
    @FXML
    private TextField incomePTJ;

    @FXML
    private CheckBox education;

    @FXML
    private Button ClientButton;
    @FXML
    private TextField dormitory_price;

    @FXML
    private TextField food_cost;

    @FXML
    private TextField adult_children;

    @FXML
    private CheckBox adult_education;

    @FXML
    private TextField adult_income;

    @FXML
    private TextField old_children;

    @FXML
    private CheckBox old_education;

    ArrayList<Person> model;
    static Person clientt;

    /**
     * a simple RTTI to determine what type of class got passed into this method, based on the type of the object
     * a newWindow is created with respective title and fxml file
     * @param model client list
     * @param client new client that was created in main controller
     */
    public void handleClient(ArrayList<Person> model,Person client){
        this.model=model;
        if (client instanceof Adult)
        {
            newWindow("Adult setup","./View/Adult_input.fxml");
        }
        if(client instanceof Youth)
        {
            newWindow("Youth setup","../View/youth_input.fxml");
        }
        if(client instanceof Old)
        {
            newWindow("Old setup","../View/Old_input.fxml");
        }
        clientt=client;
    }

    /**
     * actionEvent handler, if a button was clicked on one of 4 different products inside person input window, then
     * the name of the button is catched and passed into the product factory
     * @param event button clicked
     */
    public void handleProduct(ActionEvent event){
        MainController.mouseClicked.run();
        String name = ((Button)event.getSource()).getText();
        ProductFactory productFactory = new ProductFactory();
        Account product = productFactory.newProduct(name);
        productController.handleProduct(product);

    }

    /**
     * the setup of the client, another demonstration of RTTI, for each class respective methods are called that are
     * needed in order for the class to wark properly
     */
    public void addClient()
    {

        MainController.mouseClicked.run();
        Stage stage = (Stage) ClientButton.getScene().getWindow();
        if(clientt instanceof Adult)
        {
            ((Adult)clientt).setNumChildren(Integer.parseInt(adult_children.getText()));
            ((Adult)clientt).setEducation(adult_education.isSelected());
            ((Adult)clientt).setIncome(Double.parseDouble(adult_income.getText()));
        }
        if(clientt instanceof Old)
        {
            ((Old) clientt).setNumChildren(Integer.parseInt(old_children.getText()));
            ((Old) clientt).setEducation(old_education.isSelected());
        }
        if(clientt instanceof Youth)
        {
            clientt.setIncome(Double.parseDouble(incomePTJ.getText()));
            clientt.setEducation(education.isSelected());
            if(clientt.getEducation()) {
                ((Youth) clientt).setDormitoryPrice(Double.parseDouble(dormitory_price.getText()));
                ((Youth) clientt).setFoodPrice(Double.parseDouble(food_cost.getText()));
            }
        }

        stage.close();
        //add the client to the database
        MainController.clients.add(clientt);
    }

    /**
     * makes a new window where we input information for different classes such as Old, Adult, Youth.
     * @param title the title of the window
     * @param location location of the FXML file
     */
    void newWindow(String title, String location)
    {
        {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource(location));
            } catch (IOException e) {
                e.printStackTrace();
                MainController.warning("FXML location is invalid");
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }
    }
}
