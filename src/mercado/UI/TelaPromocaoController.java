package mercado.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mercado.DAL.DALProduto;
import mercado.DAL.DALPromocao;
import mercado.Entidade.Produto;
import mercado.Entidade.Promocao;
import mercado.Util.MaskFieldUtil;

public class TelaPromocaoController implements Initializable {

    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXDatePicker txtDataInicial;
    @FXML
    private JFXDatePicker txtDataFinal;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXButton btnAdicionar;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> colCodigo;
    @FXML
    private TableColumn<Produto, String> colReferencia;
    @FXML
    private TableColumn<Produto, String> colDescricao;
    @FXML
    private JFXTextField txtDesconto;
    @FXML
    private JFXButton btnConfirmar;

    private List<Produto> prod = new ArrayList();
    
    @FXML
    private TableColumn<Produto, Double> colPreco;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colReferencia.setCellValueFactory(new PropertyValueFactory("referencia"));
        colDescricao.setCellValueFactory(new PropertyValueFactory("descricao"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        txtNome.requestFocus();
        
        TelaBuscaProdController b = new TelaBuscaProdController();
        //pegarEmail(b.);
    }
    
    private void fadeout()
    {         
        MaskFieldUtil.monetaryField(txtDesconto);
        MaskFieldUtil.numericField(txtCodigo);
        MaskFieldUtil.maxField(txtDesconto, 6);           
        MaskFieldUtil.maxField(txtCodigo, 5);
        MaskFieldUtil.maxField(txtNome, 30);       
    }

    @FXML
    private void clkAdicionar(ActionEvent event) {
        Alert b = new Alert(Alert.AlertType.ERROR);
        int cod = Integer.parseInt(txtCodigo.getText());
        int aux = 0;
        Produto p = new Produto();
        
        DALProduto dal = new DALProduto();
        for (Produto produto : prod) {
            if (produto.getCodigo() == cod) {
                aux = 1;
                b.setContentText("Produto ja adicionado");
                b.showAndWait();
                txtCodigo.setText("");
                txtCodigo.requestFocus();
            }
        }
        if (aux == 0) {
            p = dal.get(cod);
            
            if (p != null) {
                prod.add(p);
                ObservableList<Produto> modelo;
                modelo = FXCollections.observableArrayList(prod);
                tabela.setItems(modelo);
            } else {
                b.setContentText("Produto não existe");
                b.showAndWait();
                txtCodigo.setText("");
                txtCodigo.requestFocus();
            }
        }
    }

    @FXML
    private void clkConfirmar(ActionEvent event) { 
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert b = new Alert(Alert.AlertType.ERROR);
        Date dtInicio = null;
        Date dtFim = null;        
               
        
        int erro = 0;
        double desc = 0;
        
        if(txtNome.getText().isEmpty())
        {
            b.setContentText("Nome da promoção vazio");
            b.showAndWait();
            txtNome.requestFocus();
            erro++;
        }
        
        if(txtDataInicial.getValue() == null)
        {
            b.setContentText("Data inicial não pode ser vazia");
            b.showAndWait();
            txtDataInicial.requestFocus();
            erro++;
        }
        else
        {
            dtInicio = java.sql.Date.valueOf(txtDataInicial.getValue());
            if(txtDataFinal.getValue() == null)
            {
                b.setContentText("Data final não pode ser vazia");
                b.showAndWait();
                txtDataFinal.requestFocus();
                erro++;
            }
            else
            {
                dtFim = java.sql.Date.valueOf(txtDataFinal.getValue());
                
                if(dtInicio.after(dtFim))
                {
                    b.setContentText("Data inicial não pode ser maior que a final");
                    b.showAndWait();
                    txtDataInicial.requestFocus();
                    erro++;
                }
                
                if(dtInicio.before(java.sql.Date.valueOf(LocalDate.now())))
                {
                    b.setContentText("Data inicial não pode ser anterior ao dia de hoje");
                    b.showAndWait();
                    txtDataInicial.requestFocus();
                    erro++;
                }
                
                if(dtFim.before(dtInicio))
                {
                    b.setContentText("Data final não pode ser anterior a inicial");
                    b.showAndWait();
                    txtDataFinal.requestFocus();
                    erro++;
                }
            }                
        }         
        
        if(prod.isEmpty())
        {
            b.setContentText("Insira um produto antes de confirmar");
            b.showAndWait();
            txtCodigo.requestFocus();
            erro++;
        }
        
        if(txtDesconto.getText().isEmpty())
        {
            b.setContentText("O desconto não pode ser vazio");
            b.showAndWait();
            txtDesconto.requestFocus();
            erro++;
        } 
        else
        {
            desc = Double.parseDouble(txtDesconto.getText().replace(",", "."));
            if(desc > 70 && desc <= 0)
            {
                b.setContentText("O desconto não pode ser maior que 70 nme menor que 0");
                b.showAndWait();
                txtDesconto.requestFocus();
                erro++;
            }
        }
        
        if(erro == 0)
        {
            Promocao p = new Promocao(txtNome.getText().toUpperCase(),dtInicio,dtFim,desc,true);
            DALPromocao  dal = new DALPromocao();
            if(dal.salvar(p, prod))
            {
                a.setContentText("Promoção gravada com sucesso");
                a.showAndWait();
            }
            else
            {
                b.setContentText("Erro ao gravar promoção");
                b.showAndWait();
            }
        }
    }

    @FXML
    private void clkBuscar(KeyEvent event) {
        if(event.getCode().equals(KeyCode.F10))
        {
            try
            {            
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("TelaBuscaProd.fxml"));        
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("BUSCA DE PRODUTO");
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();           
            }
            catch(IOException e)
            {
                System.out.println(e);
            } 
        }
    }
    
    public void pegarEmail(int cod){
	  this.txtCodigo.setText(Integer.toString(cod));
    }
}