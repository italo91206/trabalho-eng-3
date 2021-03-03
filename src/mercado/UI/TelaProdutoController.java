package mercado.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mercado.DAL.DALProduto;
import mercado.Entidade.Produto;
import mercado.Util.MaskFieldUtil;

public class TelaProdutoController implements Initializable {

    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnExcluir;
    @FXML
    private JFXButton btnConfirmar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField txtRef;
    @FXML
    private JFXTextField txtDesc;
    @FXML
    private JFXTextField txtPreco;
    @FXML
    private JFXTextField txtCusto;
    @FXML
    private JFXTextField txtPromo;
    @FXML
    private JFXTextField txtMinimo;
    @FXML
    private JFXTextField txtAtual;
    @FXML
    private JFXTextField txtCod;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> colCod;
    @FXML
    private TableColumn<Produto, String> colRef;
    @FXML
    private TableColumn<Produto, String> colDesc;
    @FXML
    private TableColumn<Produto, Double> colPreco;
    @FXML
    private TableColumn<Produto, Integer> colEstoque;
    @FXML
    private AnchorPane pnDados;    
    @FXML
    private JFXRadioButton rbtRef;
    @FXML
    private ToggleGroup Grupo;
    @FXML
    private JFXRadioButton rbtDesc;
    @FXML
    private JFXTextField txtPesquisa;
    @FXML
    private JFXButton btnPesquisa;
    @FXML
    private VBox pnPesquisa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
        colCod.setCellValueFactory(new PropertyValueFactory("codigo"));
        colRef.setCellValueFactory(new PropertyValueFactory("referencia"));
        colDesc.setCellValueFactory(new PropertyValueFactory("descricao"));
        colPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        colEstoque.setCellValueFactory(new PropertyValueFactory("estoque"));
        estadoOriginal();
    }    
    
    private void fadeout()
    {         
        MaskFieldUtil.monetaryField(txtPreco);
        MaskFieldUtil.maxField(txtPreco, 8);
        
        MaskFieldUtil.monetaryField(txtCusto);
        MaskFieldUtil.maxField(txtCusto, 8);
        
        MaskFieldUtil.numericField(txtMinimo);
        MaskFieldUtil.maxField(txtMinimo, 6);        
            
        MaskFieldUtil.maxField(txtDesc, 50);
        MaskFieldUtil.maxField(txtRef, 20);       
    }

    private void estadoOriginal()
    {       
        pnDados.setDisable(true);
        pnPesquisa.setDisable(false);
        btnNovo.setDisable(false); 
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
        btnConfirmar.setDisable(true);
        btnCancelar.setDisable(false);       
               
        ObservableList <Node> componentes=pnDados.getChildren(); //”limpa” os componentes
        for(Node n : componentes)
        {
            if (n instanceof TextInputControl)  // textfield, textarea e htmleditor
                ((TextInputControl)n).setText("");            
        }      
        carregaTabela("");
    }
    
    private void carregaTabela(String filtro)
    {
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.getAtivo();
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);        
    }
    
     private void carregaTabela2(String filtro)
    {
        DALProduto dal = new DALProduto();
        List<Produto> res = dal.getAtivo(filtro);
        ObservableList<Produto> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);        
    }
    
    private void estadoEdicao() { 
        pnPesquisa.setDisable(true);
        pnDados.setDisable(false);
        btnNovo.setDisable(true);
        btnConfirmar.setDisable(false);
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
        txtCod.setDisable(true);
        txtRef.requestFocus(); 
        txtPromo.setDisable(true);
        txtAtual.setDisable(true);
    }
    
    private void estadoEdicao2() { 
        pnPesquisa.setDisable(true);
        pnDados.setDisable(false);
        btnNovo.setDisable(true);
        btnConfirmar.setDisable(false);
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
        txtCod.setDisable(true);
        txtRef.requestFocus(); 
        txtPromo.setDisable(true);
        txtAtual.setDisable(false);
    }
    
    @FXML
    private void clkNovo(ActionEvent event) {
        estadoEdicao2();
    }

    @FXML
    private void clkAlterar(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedItem()!=null)
        {
            Produto p = (Produto)tabela.getSelectionModel().getSelectedItem(); 
            txtCod.setText(""+p.getCodigo());
            txtRef.setText(p.getReferencia());
            txtDesc.setText(p.getDescricao());
            txtPreco.setText(String.format("%10.2f",p.getPreco()));   
            txtCusto.setText(String.format("%10.2f",p.getPreco_custo())); 
            txtPromo.setText(String.format("%10.2f",p.getPreco_promo())); 
            txtMinimo.setText(""+p.getEstoque_minimo());
            txtAtual.setText(""+p.getEstoque());
            estadoEdicao();            
        }
    }

    @FXML
    private void clkExcluir(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Confirma a exclusão");
        if (a.showAndWait().get() == ButtonType.OK) {
            DALProduto dal = new DALProduto();
            Produto mv;
            mv = tabela.getSelectionModel().getSelectedItem();
            dal.apagar(mv.getCodigo());
            estadoOriginal();
        }
    }

    @FXML
    private void clkConfirmar(ActionEvent event) {
        
        int erro = 0;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert b = new Alert(Alert.AlertType.ERROR);
        
        if(txtDesc.getText().equals(""))
            erro++;
        if(txtPreco.getText().equals(""))
            erro++;
        if(txtCusto.getText().equals(""))
            erro++;
        if(txtAtual.getText().equals(""))
            erro++;
        if(txtMinimo.getText().equals(""))
            erro++;
        
        if(erro == 0)
        {
            double preco = Double.parseDouble(txtPreco.getText().replace(".","").replace(",", "."));
            double custo = Double.parseDouble(txtCusto.getText().replace(".","").replace(",", "."));
            double promo = 0;
            int atual = Integer.parseInt(txtAtual.getText());
            int min = Integer.parseInt(txtMinimo.getText());
            Produto p = new Produto(txtRef.getText().toUpperCase(), txtDesc.getText().toUpperCase(), preco, custo, promo, atual, min,true);
            DALProduto dal = new DALProduto();
            
            
            if(txtCod.getText().equals(""))
            {
                if(dal.salvar(p))
                {
                    a.setContentText("Produto gravado com sucesso");
                    a.showAndWait();
                }
                else
                {
                    b.setContentText("Erro ao gravar o produto");
                    b.showAndWait();
                }                
            }
            else
            {
                if(dal.alterar(p,Integer.parseInt(txtCod.getText())))
                {
                    a.setContentText("Produto alterado com sucesso");
                    a.showAndWait();
                }
                else
                {
                    b.setContentText("Erro ao alterar o produto");
                    b.showAndWait();
                }                
            }
            estadoOriginal();             
        }
        else
        {
            b.setContentText("Alguns campos estão vazios!");
            b.showAndWait();
        }                 
    }

    @FXML
    private void clkCancelar(ActionEvent event) {
        if (!pnDados.isDisabled()) // encontra em estado de edição
        {
            estadoOriginal();
        } else {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void clkTabela(MouseEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex()>=0)
        {
           btnAlterar.setDisable(false);
           btnExcluir.setDisable(false);
        }
    }    

    @FXML
    private void clkPesquisar(ActionEvent event) {
        
        if(rbtRef.isSelected())
        {
            carregaTabela2("prod_referencia like '%"+txtPesquisa.getText().toUpperCase()+"%'");
        }
        else
        {
            carregaTabela2("prod_descricao like '%"+txtPesquisa.getText().toUpperCase()+"%'");
        }
    }
}