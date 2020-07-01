/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Inventory;
import project.Part;
import project.Product;

/**
 * FXML Controller class
 *
 * @author Tom
 */
public class ModifyProductController implements Initializable {

    @FXML
    private AnchorPane modifyProductScreen;
    @FXML
    private Button searchModifyProductButton;
    @FXML
    private TextField searchModifyProductTextField;
    @FXML
    private Button addModifyProductButton;
    @FXML
    private Button saveModifyProductButton;
    @FXML
    private Button cancelModifyProductButton;
    @FXML
    private TextField idModifyProductTextField;
    @FXML
    private TextField nameModifyProductTextField;
    @FXML
    private TextField invModifyProductTextField;
    @FXML
    private TextField priceModifyProductTextField;
    @FXML
    private TextField minModifyProductTextField;
    @FXML
    private TextField maxModifyProductTextField;
  
    private int retrievedIndex;
    @FXML
    private TableView<Part> productTable1;
    @FXML
    private TableColumn<Part, Integer> partID1;
    @FXML
    private TableColumn<Part, String> partName1;
    @FXML
    private TableColumn<Part, Integer> partStock1;
    @FXML
    private TableColumn<Part, Double> partPrice1;
    @FXML
    private TableView<Part> productTable2;
    @FXML
    private TableColumn<Part, Integer> partID2;
    @FXML
    private TableColumn<Part, String> partName2;
    @FXML
    private TableColumn<Part, Integer> partStock2;
    @FXML
    private TableColumn<Part, Double> partPrice2;
    
    private Product product;
    @FXML
    private Button deleteModifyProductButton1;
    
      @FXML
     private void updateProduct(ActionEvent event) throws IOException{
       
      if (productTable2.getItems().isEmpty()){
          Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("No parts found");
           alert.setContentText("You must have atleast 1 part to create a product.");

           alert.showAndWait();
          }
      else if (nameModifyProductTextField.getText().isEmpty() || invModifyProductTextField.getText().isEmpty() || priceModifyProductTextField.getText().isEmpty() )
        {
         Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Empty fields");
           alert.setContentText("Name, inventory, and price must have a value!");

           alert.showAndWait();
        }
      else{
            int id = Integer.parseInt(idModifyProductTextField.getText());
       String name = nameModifyProductTextField.getText();
       int stock = Integer.parseInt(invModifyProductTextField.getText());
       Double price = Double.parseDouble(priceModifyProductTextField.getText());
       int min = Integer.parseInt(minModifyProductTextField.getText());
       int max = Integer.parseInt(maxModifyProductTextField.getText());
       Product product = new Product(id, name, price, stock, min, max);
       for (Part part: tempList){
       product.addAssociatedPart(part);
       }
       

     
   Inventory.updateProduct(retrievedIndex, product);
     
     Parent addPartParent = FXMLLoader.load(getClass().getResource("/project/screen1.fxml"));
      Scene addPartScene = new Scene(addPartParent);
      
      //get stage info
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
     window.setScene(addPartScene);
     window.show();
      }
    } 
     
     private ObservableList<Part> associatedParts;
     
      public void retrieveData(Product product){
        retrievedIndex = Inventory.getAllProducts().indexOf(product);
        associatedParts = product.getAllAssociatedParts();
      }
      
      public void populateTable2(){
      productTable2.setItems(associatedParts);
         partName2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
             partID2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
            partStock2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
            partPrice2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
      }
      
      public void setProduct(Product product){
      idModifyProductTextField.setText(String.valueOf(product.getId()));
      nameModifyProductTextField.setText(product.getName());
      invModifyProductTextField.setText(String.valueOf(product.getStock()));
      priceModifyProductTextField.setText(String.valueOf(product.getPrice()));
      minModifyProductTextField.setText(String.valueOf(product.getMin()));
      maxModifyProductTextField.setText(String.valueOf(product.getMax()));
      this.product = product;
      }
       public Part selectedPart(){
       Part part=productTable1.getSelectionModel().getSelectedItem();
       return part;
   }
   private ObservableList<Part> tempList = FXCollections.observableArrayList();
    
      @FXML
    private void addProductToTable2(){
    tempList.add(selectedPart()); 
     this.productTable2.setItems(tempList);
     partName2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
     partID2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
     partStock2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
     partPrice2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
     
    }
    
    @FXML
   private void delete (){
    ObservableList<Part> selected;
    selected = productTable2.getSelectionModel().getSelectedItems();
    for (Part part: selected){
        tempList.remove(part);
    }
         this.productTable2.setItems(tempList);
     partName2.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
     partID2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
     partStock2.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
     partPrice2.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
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
        private void searchProduct(){
     String s = searchModifyProductTextField.getText();
           int x = Integer.parseInt(searchModifyProductTextField.getText());
            productTable1.getSelectionModel().select((Inventory.lookupPart(x)));
   }
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        
             this.productTable1.setItems(Inventory.getAllParts());
             partName1.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
             partID1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
            partStock1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
            partPrice1.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
           
 
            
    }    

    
}
