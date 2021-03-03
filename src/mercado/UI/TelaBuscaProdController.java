package mercado.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mercado.DAL.DALProduto;
import mercado.Entidade.Produto;
import mercado.Util.MaskFieldUtil;

public class TelaBuscaProdController implements Initializable {

    @FXML
    private JFXTextField txtBusca;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> colCodigo;
    @FXML
    private TableColumn<Produto, String> ColDesc;
    @FXML
    private TableColumn<Produto, Double> ColPreco;
    @FXML
    private JFXButton btnBuscat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));        
        ColDesc.setCellValueFactory(new PropertyValueFactory("descricao"));
        ColPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        MaskFieldUtil.maxField(txtBusca, 30);
        txtBusca.requestFocus();
    }    

    @FXML
    private void clkBuscar(ActionEvent event) {
        if(!txtBusca.getText().isEmpty())
        {
            DALProduto dal = new DALProduto();
            List<Produto> res = dal.getAtivo("prod_descricao like '%"+txtBusca.getText().toUpperCase()+"%'");
            ObservableList<Produto> modelo;
            modelo = FXCollections.observableArrayList(res);
            tabela.setItems(modelo);
        }
        else
        {
            DALProduto dal = new DALProduto();
            List<Produto> res = dal.getAtivo();
            ObservableList<Produto> modelo;
            modelo = FXCollections.observableArrayList(res);
            tabela.setItems(modelo);
        }          
    }

    
    @FXML
    private int clkEnvia(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER))
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Confirma o produto");
            if (a.showAndWait().get() == ButtonType.OK)
            {                
                Produto mv;
                mv = tabela.getSelectionModel().getSelectedItem();
                int cod = mv.getCodigo();
                return cod;
            }
        }
        return 0;
    }
}
