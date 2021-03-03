package mercado.UI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaLoginController implements Initializable {
        
    @FXML
    private void handleButtonAction(ActionEvent event){
        logar();       
    }
    
    private void logar()
    {
        try
        {            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TelaMenu.fxml"));        
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("BEM VINDO : SISTEMA GERENCIADOR DE MERCADO");
            stage.setResizable(false);
            stage.show();            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }     
}