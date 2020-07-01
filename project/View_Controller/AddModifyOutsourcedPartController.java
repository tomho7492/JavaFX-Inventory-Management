/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.View_Controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.InhousePart;
import project.Inventory;
import project.OutsourcedPart;

/**
 * FXML Controller class
 *
 * @author Tom
 */
public class AddModifyOutsourcedPartController implements Initializable {

    @FXML
    private AnchorPane addPartScreen;
    @FXML
    private RadioButton outsourcedAddPartRadioButton;
    @FXML
    private TextField idAddPartTextField;
    @FXML
    private TextField nameAddPartTextField;
    @FXML
    private TextField invAddPartTextField;
    @FXML
    private TextField priceCostAddPartTextField;
    @FXML
    private TextField maxAddPartTextField;
    @FXML
    private TextField minAddPartTextField;
    @FXML
    private TextField companyNameAddPartTextField;
    @FXML
    private Button saveAddPartButton;
    @FXML
    private Button cancelAddPartTextField;
    @FXML
    private RadioButton inhouseButton;
    @FXML
    private ToggleGroup inhouseoutsource;
    @FXML
    private Label companyNameLabel;

    
    @FXML
      private void savePart(ActionEvent event) throws IOException{
        
       addPartToList();
     Parent addPartParent = FXMLLoader.load(getClass().getResource("/project/screen1.fxml"));
      Scene addPartScene = new Scene(addPartParent);
      
      //get stage info
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
     window.setScene(addPartScene);
     window.show();
 
    } 
    @FXML
   private void cancel(ActionEvent event) throws IOException{
        
     
     Parent addPartParent = FXMLLoader.load(getClass().getResource("/project/screen1.fxml"));
      Scene addPartScene = new Scene(addPartParent);
      
      //get stage info
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
     window.setScene(addPartScene);
     window.show();
 
    } 
   
   @FXML 
   private void outsourcedIsSelected(){
   companyNameAddPartTextField.setText("Comp Nm");
   companyNameLabel.setText("Company Name");
   }
   @FXML 
   private void inhouseIsSelected(){
    companyNameAddPartTextField.setText("Mach ID");
   companyNameLabel.setText("Machine ID");
   }
   
    //method to add part to the observablelist
    private void addPartToList(){
    int id = Integer.parseInt(idAddPartTextField.getText());
    String name = nameAddPartTextField.getText();
    int stock = Integer.parseInt(invAddPartTextField.getText());
    double price = Double.parseDouble(priceCostAddPartTextField.getText());
    int max = Integer.parseInt(maxAddPartTextField.getText());
    int min = Integer.parseInt(minAddPartTextField.getText());
    if (inhouseButton.isSelected()){
        System.out.println("Adding machine id");
            int machineID = Integer.parseInt(companyNameAddPartTextField.getText());
    Inventory.addPart(new InhousePart(id, name, price, stock, min, max, machineID));
    }
    else {
            String companyName = companyNameAddPartTextField.getText();
    Inventory.addPart(new OutsourcedPart(id, name, price, stock, min, max, companyName));
    }
    
    
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
