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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Inventory;
import project.Part;
import project.InhousePart;
import project.OutsourcedPart;

/**
 * FXML Controller class
 *
 * @author Tom
 */
public class ModifyPartScreenController implements Initializable {

    @FXML
    private RadioButton inhouseModifyPartRadioButton;
    @FXML
    private RadioButton outsourcedModifyPartRadioButton;
    @FXML
    private TextField idModifyPartTextField;
    @FXML
    private TextField nameModifyPartTextField;
    @FXML
    private TextField invModifyPartTextField;
    @FXML
    private TextField priceCostModifyPartTextField;
    @FXML
    private TextField maxModifyPartTextField;
    @FXML
    private TextField companyNameModifyPartTextField;
    @FXML
    private TextField minModifyPartTextField;
    @FXML
    private Button saveModifyPartButton;
    @FXML
    private Button cancelModifyPartButton;
    @FXML
    private AnchorPane modifyPartScreen;
    @FXML
    private ToggleGroup inhouseoutsource;
    @FXML
    private Label companyNameLabel;
 
    @FXML
    public void setInhousePart(InhousePart part){
        System.out.println(part.getMachineId());
        inhouseModifyPartRadioButton.setSelected(true);
           outsourcedModifyPartRadioButton.setSelected(false);

        companyNameLabel.setText("Machine ID");
       companyNameModifyPartTextField.setText(String.valueOf(part.getMachineId()));

   
    idModifyPartTextField.setText(String.valueOf(part.getId()));
    nameModifyPartTextField.setText(part.getName());
    invModifyPartTextField.setText(String.valueOf(part.getStock()));
    priceCostModifyPartTextField.setText(String.valueOf(part.getPrice()));
    maxModifyPartTextField.setText(String.valueOf(part.getMax()));
    minModifyPartTextField.setText(String.valueOf( part.getMin()));   
    
    
    }
    
    @FXML
    public void setOutsourcedPart(OutsourcedPart part){
   
        inhouseModifyPartRadioButton.setSelected(false);
                outsourcedModifyPartRadioButton.setSelected(true);

   companyNameLabel.setText("Company Name");
    
    idModifyPartTextField.setText(String.valueOf(part.getId()));
    nameModifyPartTextField.setText(part.getName());
    invModifyPartTextField.setText(String.valueOf(part.getStock()));
    priceCostModifyPartTextField.setText(String.valueOf(part.getPrice()));
    maxModifyPartTextField.setText(String.valueOf(part.getMax()));
    minModifyPartTextField.setText(String.valueOf( part.getMin()));   
    companyNameModifyPartTextField.setText(String.valueOf(part.getCompanyName()));

    
    }
    
    
    
    
    
    @FXML
   private void outsourcedIsSelected(){
   companyNameLabel.setText("Company Name");
   }
    @FXML
   private void inhouseIsSelected(){
   companyNameLabel.setText("Machine ID");
   }
   
    
    private void modifyPart(){
        int id = Integer.parseInt(idModifyPartTextField.getText());
        String name = nameModifyPartTextField.getText();
        int stock = Integer.parseInt(invModifyPartTextField.getText());
        Double price = Double.parseDouble(priceCostModifyPartTextField.getText());
        int max = Integer.parseInt(maxModifyPartTextField.getText());
        int min = Integer.parseInt(minModifyPartTextField.getText());
        if (inhouseModifyPartRadioButton.isSelected()){
                    int machineID = Integer.parseInt(companyNameModifyPartTextField.getText());
        InhousePart newPart = new InhousePart(id, name, price, stock, min, max, machineID);
                Inventory.updatePart(retrievedIndex, newPart);

        }
        else
        {
                            String companyName = companyNameModifyPartTextField.getText();
        OutsourcedPart newPart = new OutsourcedPart(id, name, price, stock, min, max, companyName);
                Inventory.updatePart(retrievedIndex, newPart);
        }
    }
    
    private int retrievedIndex;
    private boolean inhousePartTypeCheck;
    public void retrieveData(Part part){
        retrievedIndex = Inventory.getAllParts().indexOf(part);
        inhousePartTypeCheck = part instanceof InhousePart;
    }
 @FXML     //switch part and switch to main screen
 
   private void saveModifiedPart(ActionEvent event) throws IOException{
        
       modifyPart();
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
   
  
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
