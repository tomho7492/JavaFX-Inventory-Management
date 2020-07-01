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
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class AddProductController implements Initializable {

    @FXML
    private AnchorPane addProductScreen;
    @FXML
    private Button searchAddProductButton;
    @FXML
    private TextField searchAddProductTextField;
    @FXML
    private Button addAddProductButton;
    @FXML
    private Button deleteAddProductButton;
    @FXML
    private Button saveAddProductButton;
    @FXML
    private Button cancelAddProductButton;
    @FXML
    private TextField idAddProductTextField;
    @FXML
    private TextField nameAddProductTextField;
    @FXML
    private TextField invAddProductTextField;
    @FXML
    private TextField priceAddProductTextField;
    @FXML
    private TextField minAddProductTextField;
    @FXML
    private TextField maxAddProductTextField;
    @FXML
    private TableColumn<Part, Integer> partID2;
    @FXML
    private TableColumn<Part, String> partName2;
    @FXML
    private TableColumn<Part, Integer> partStock2;
    @FXML
    private TableColumn<Part, Double> partPrice2;
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
    private TableView<Part> productTable1;
    private ObservableList<Part> tempList = FXCollections.observableArrayList();
      public Part selectedPart(){
       Part part=productTable1.getSelectionModel().getSelectedItem();
       return part;
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
        private void searchProduct(){
     String s = searchAddProductTextField.getText();
           int x = Integer.parseInt(searchAddProductTextField.getText());
            productTable1.getSelectionModel().select((Inventory.lookupPart(x)));
   }
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
    private void addProduct(ActionEvent event) throws IOException{
        if (nameAddProductTextField.getText().isEmpty() || invAddProductTextField.getText().isEmpty() || priceAddProductTextField.getText().isEmpty() )
        {
         Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Empty fields");
           alert.setContentText("Name, inventory, and price must have a value!");

           alert.showAndWait();
        }
        else {
     int id = Integer.parseInt(idAddProductTextField.getText());
    String name = nameAddProductTextField.getText();
    int stock = Integer.parseInt(invAddProductTextField.getText());
    double price = Double.parseDouble(priceAddProductTextField.getText());
    int max = Integer.parseInt(maxAddProductTextField.getText());
    int min = Integer.parseInt(minAddProductTextField.getText());
    Product product = new Product(id, name, price, stock, min, max);
   for (Part part: tempList){
           product.addAssociatedPart(part);
    }
   if (product.getAllAssociatedParts().isEmpty()){
    Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("No parts found");
           alert.setContentText("You must have atleast 1 part to create a product.");

           alert.showAndWait();
   }
   else {
    Inventory.addProduct(product);
    //switch to main screen
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/project/screen1.fxml"));
       
      Scene addPartScene = new Scene(addPartParent);
      
      //get stage info
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
     window.setScene(addPartScene);
     window.show(); 
    }
    }
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
     this.productTable1.setItems(Inventory.getAllParts());
     partName1.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
     partID1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
     partStock1.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
     partPrice1.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

    }    
    
}
