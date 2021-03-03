package mercado;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import mercado.Banco.Banco;

public class Mercado extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UI/TelaMenu.fxml"));        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.setTitle("BEM VINDO : SISTEMA GERENCIADOR DE MERCADO");        
        stage.show();
    }

    public static void main(String[] args) {
        if(!Banco.conectar())
            JOptionPane.showMessageDialog(null,Banco.getCon().getMensagemErro());
        else
            launch(args);
    }    
}