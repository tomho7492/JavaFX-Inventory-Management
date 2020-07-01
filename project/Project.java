
package project;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tom
 */
public class Project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("screen1.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show(); 
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //int id, String name, double price, int stock, int min, int max, int machineId
       
        launch(args);
    }
    
}
