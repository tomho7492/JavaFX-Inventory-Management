/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Tom
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    
    public static void addPart(Part newPart)
    {
    allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct){
    allProducts.add(newProduct);
    }
    
    
    public static Part lookupPart(int partId){
         boolean found = false;
         for (Part p: getAllParts())
             {
                if(p.getId() == partId)
                {  
                    return p;
                }
             }
         return null;
    }
    
    public static Product lookupProduct(int productId){
         boolean found = false;
         for (Product p: getAllProducts())
             {
                if(p.getId() == productId)
                {  
                    return p;
                }
             }
         return null;
}
   public static ObservableList<Part> lookupPart(String partName)
   {
       return null;
   }
   
   public static ObservableList<Product> lookupProduct(String productName){return null;}
    
   public static void updatePart(int index, Part selectedPart)
   {
       getAllParts().set(index, selectedPart);
 
   }
   public static void updateProduct(int index, Product newProduct){
          getAllProducts().set(index, newProduct);
   }
   
   public static boolean deletePart(Part selectedPart){
       getAllParts().remove(selectedPart);
       return true;
}
   
   public static boolean deleteProduct (Product selectedProduct){
   getAllProducts().remove(selectedProduct);
   return true;
   }
   
   public static ObservableList<Part> getAllParts(){return allParts;
}
   
   public static ObservableList<Product> getAllProducts(){return allProducts;}
   
   
}
