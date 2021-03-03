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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaMenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
    }    

    @FXML
    private void clkProduto(ActionEvent event) {
        try
        {            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));        
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("CADASTRO DE PRODUTO");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();           
        }
        catch(IOException e)
        {
            System.out.println(e);
        }       
    }

    @FXML
    private void clkEmpresa(ActionEvent event) {
        try
        {            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TelaEmpresa.fxml"));        
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("CADASTRO DE EMPRESA");             
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();             
        }
        catch(IOException e)
        {
            System.out.println(e);
        }       
    }

    @FXML
    private void clkPromocao(ActionEvent event) {
        try
        {            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TelaPromocao.fxml"));        
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("CADASTRO DE PROMOÇÃO");              
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();             
        }
        catch(IOException e)
        {
            System.out.println(e);
        } 
    }    

    @FXML
    private void clkPagar(ActionEvent event) {
        try
        {            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("TelaContasPagar.fxml"));        
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("CONTAS A PAGAR");              
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();             
        }
        catch(IOException e)
        {
            System.out.println(e);
        } 
    }

    @FXML
    private void clkReceber(ActionEvent event) {
    }
}