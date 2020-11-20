package Controller;

import Model.Audio.AudioThread;
import Model.Products.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * controlls the product fxmls
 */
public class ProductController {

    @FXML
    private TextField l_borrowedSum;

    @FXML
    private TextField l_percent;

    @FXML
    private TextField l_time;

    @FXML
    private TextField c_name;

    @FXML
    private TextField c_payment;

    @FXML
    private TextField c_time;

    @FXML
    private Button ProductButton;

    @FXML
    private TextField i_capital;

    @FXML
    private TextField i_payment;

    @FXML
    private TextField i_APR;

    @FXML
    private TextField i_time;

    @FXML
    private TextField leasing_cost;

    @FXML
    private TextField leasing_depositPercent;

    @FXML
    private TextField leasing_time;

    @FXML
    private TextField leasing_coeff;

    static Account product_temp;

    /**
     * handles the product, uses the RTTI to find out which product it is and then show new window corresponding to
     * passed parameter product
     * @param product product passed by the person controller
     */
    public void handleProduct(Account product)  {
        product_temp=product;
        PersonController personController = new PersonController();
       if(product instanceof Loan)
       {
           personController.newWindow("Loan setup","../View/Loan_input.fxml");
       }
       if(product instanceof Investment)
       {
           if(product instanceof Commodity)
           {
               personController.newWindow("Commodity setup","../View/commodity_input.fxml");
           }
           else {
               personController.newWindow("Investment setup", "../View/Investment_input.fxml");
           }
       }

       if(product instanceof Leasing)
       {
           personController.newWindow("Leasing setup","../View/Leasing_input.fxml");
       }
    }

    /**
     * adds the product to the client, which was in personController created as a local static variable.
     * Because of the use of single controller for products, the current stage is needed in order to close the window
     * another use of RTTI where the product is set up by their respective methods
     */
    public void addProduct()
    {
        MainController.mouseClicked.run();
        //find out the stage
        Stage stage = (Stage) ProductButton.getScene().getWindow();
        if(product_temp instanceof Loan)
        {
            ((Loan)product_temp).setBorrowedSum(Double.parseDouble(l_borrowedSum.getText()));
            ((Loan)product_temp).setInterest(Double.parseDouble(l_percent.getText()));
            ((Loan)product_temp).setTotalYears(Integer.parseInt(l_time.getText()));
        }
        if(product_temp instanceof Investment)
        {
            if(product_temp instanceof Commodity)
            {
                ((Commodity)product_temp).setCommodity_name(c_name.getText());
                ((Commodity)product_temp).setMonthlyDeposit(Double.parseDouble(c_payment.getText()));
                ((Commodity)product_temp).setYearsInvesting(Integer.parseInt(c_time.getText()));
            }
            else
            {
                ((Investment)product_temp).setYearsInvesting(Integer.parseInt(i_time.getText()));
                ((Investment)product_temp).setDeposit(Double.parseDouble(i_capital.getText()));
                ((Investment)product_temp).setMonthlyDeposit(Double.parseDouble(i_payment.getText()));
                ((Investment)product_temp).setYield(Double.parseDouble(i_APR.getText()));
            }
        }
        if(product_temp instanceof Leasing)
        {
            ((Leasing)product_temp).setCarPrice(Double.parseDouble(leasing_cost.getText()));
            ((Leasing)product_temp).setInitialDepositPercent(Double.parseDouble(leasing_depositPercent.getText()));
            ((Leasing)product_temp).setCoefficientIncrease(Double.parseDouble(leasing_coeff.getText()));
            ((Leasing)product_temp).setTimePeriod(Integer.parseInt(leasing_time.getText()));
        }
        //add product to the owners portfolio and close the window
        PersonController.clientt.addProduct(product_temp);
        stage.close();
    }


}
