/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.Inventory;
import project.Part;
import javafx.util.Callback;
import project.InhousePart;
import project.OutsourcedPart;
import project.Product;

/**
 * FXML Controller class
 *
 * @author Tom
 */
public class Screen1Controller implements Initializable {

    @FXML
    private Button addPartButton;
    @FXML
    private AnchorPane mainScreen;
    @FXML
    private Button searchPartButton;
    @FXML
    private Button modifyPartButton;
    @FXML
    private Button deletePartButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button modifyProductButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button searchProductButton;
    @FXML
    private Button exitButton;
    @FXML
    private TableColumn<Part, String> partsName;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partsID;
    @FXML
    private TableColumn<Part, Integer> partsStock;
    @FXML
    private TableColumn<Part, Double> partsCost;
    @FXML
    private TextField searchPartTextField;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productID;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productStock;
    @FXML
    private TableColumn<Product, Double> productPrice;
    @FXML
    private TextField searchProductTextField;
    @FXML
   private void addScene(ActionEvent event) throws IOException{
        System.out.println(getClass().getResource("/project"));
     Parent addPartParent = FXMLLoader.load(getClass().getResource("/project/addModifyOutsourcedPart.fxml"));
       
      Scene addPartScene = new Scene(addPartParent);
      
      //get stage info
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
     window.setScene(addPartScene);
     window.show();
 
    } 
    @FXML
   private void switchToAddProductScreen(ActionEvent event) throws IOException{
                     System.out.println(getClass().getResource("/project"));
     Parent addPartParent = FXMLLoader.load(getClass().getResource("/project/addProduct.fxml"));
       
      Scene addPartScene = new Scene(addPartParent);
      
      //get stage info
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
     window.setScene(addPartScene);
     window.show();
 
    } 
   
   public Part selectedPart(){
       Part part=partsTable.getSelectionModel().getSelectedItem();
       return part;
   }
     public Product selectedProduct(){
       Product product=productTable.getSelectionModel().getSelectedItem();
       return product;
   }
   
   //switches to modify part screen
    @FXML
   private void switchToModifyPart(ActionEvent event) throws IOException{
       
     Stage stage;   
     Parent modifyPartParent;
     stage =(Stage) modifyPartButton.getScene().getWindow();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/modifyPartScreen.fxml"));
     modifyPartParent = loader.load();
     ModifyPartScreenController controller = loader.getController();
   
     
    
    if (partsTable.getSelectionModel().getSelectedItem() instanceof InhousePart) {  //is the selected item a InhousePart?
        InhousePart inhouse = (InhousePart) partsTable.getSelectionModel().getSelectedItem(); //if so, typecast the part to a InhousePart
              controller.retrieveData(inhouse); //stores data from this controller, in the other controller
              if (inhouse!= null){ // if the part exists
                       controller.setInhousePart(inhouse); // populate modify screen view with selected item data
              }

    }
    else {
        OutsourcedPart outsourced = (OutsourcedPart) partsTable.getSelectionModel().getSelectedItem();
              controller.retrieveData(outsourced);
              if (outsourced!= null){
                       controller.setOutsourcedPart(outsourced);
              }
    }

    
     Scene scene = new Scene(modifyPartParent);
     stage.setScene(scene);
     stage.show();

     
     }
  

   @FXML
   private void Searching(ActionEvent event){

          String s = searchPartTextField.getText();
           int x = Integer.parseInt(searchPartTextField.getText());
            partsTable.getSelectionModel().select((Inventory.lookupPart(x)));
       }
   @FXML
   private void searchProduct(ActionEvent event){
     String s = searchProductTextField.getText();
           int x = Integer.parseInt(searchProductTextField.getText());
            productTable.getSelectionModel().select((Inventory.lookupProduct(x)));
   }
   //}
   @FXML
   public void exit(){
   System.exit(0);
   }
   
   @FXML
   public void delete(){
   Part part = partsTable.getSelectionModel().getSelectedItem();
   Inventory.deletePart(part);
   }
   @FXML
   public void deleteProduct(){
   Product product = productTable.getSelectionModel().getSelectedItem();
   Inventory.deleteProduct(product);
   }
   
     @FXML
     private void modifyProductScreen(ActionEvent event) throws IOException{
     Stage stage;   
     Parent modifyProductParent;
     stage =(Stage) modifyProductButton.getScene().getWindow();
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/modifyProduct.fxml"));
     modifyProductParent = loader.load();
     ModifyProductController controller = loader.getController();
     Product product=productTable.getSelectionModel().getSelectedItem();
      controller.retrieveData(product);
        if (product != null){
         controller.setProduct(product);
         controller.populateTable2();
     }
     Scene scene = new Scene(modifyProductParent);
     stage.setScene(scene);
     stage.show();
 
    } 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            
    
     
     
     //parts
     partsName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
     partsID.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
     partsStock.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
     partsCost.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
     
     
     //products
     
      productName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
     productID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
     productStock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
     productPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
     
      this.partsTable.setItems(Inventory.getAllParts());
     this.productTable.setItems(Inventory.getAllProducts());
     
    }
}
