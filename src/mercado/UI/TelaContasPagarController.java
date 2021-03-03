package mercado.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import mercado.DAL.DALContasPagar;
import mercado.DAL.DALFornecedor;
import mercado.DAL.DALPagamento;
import mercado.Entidade.ContasPagar;
import mercado.Entidade.Fornecedor;
import mercado.Entidade.Pagamento;
import mercado.Entidade.ParcelasPagar;
import mercado.Util.MaskFieldUtil;

public class TelaContasPagarController implements Initializable {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtFantasia;
    @FXML
    private JFXDatePicker txtInicial;
    @FXML
    private JFXDatePicker txtFinal;    
    @FXML
    private JFXRadioButton rbtTodos;
    @FXML
    private ToggleGroup status;
    @FXML
    private JFXRadioButton rbtAberto;
    @FXML
    private JFXRadioButton rbtFechado;
    @FXML
    private JFXButton btnListar;  
    @FXML
    private JFXTextField txtMov;
    @FXML
    private TableView<ParcelasPagar> tabela;
    @FXML
    private TableColumn<ParcelasPagar, Integer> colCompra;
    @FXML
    private TableColumn<ParcelasPagar, Integer> colFor;
    @FXML
    private TableColumn<ParcelasPagar, String> colDesc;
    @FXML
    private TableColumn<ParcelasPagar, Integer> colParc;
    @FXML
    private TableColumn<ParcelasPagar, Double> colValor;
    @FXML
    private TableColumn<ParcelasPagar, Date> colPag;
    @FXML
    private TableColumn<ParcelasPagar, Date> colVenc;
    @FXML
    private JFXButton btnPagar;
    @FXML
    private JFXButton btnExt;
    @FXML
    private JFXComboBox<Pagamento> cmbForm;
    
    public void initialize(URL url, ResourceBundle rb) { 
        colCompra.setCellValueFactory(new PropertyValueFactory("com_codigo"));
        colFor.setCellValueFactory(new PropertyValueFactory("for_codigo"));
        colDesc.setCellValueFactory(new PropertyValueFactory("descricao"));
        colParc.setCellValueFactory(new PropertyValueFactory("parcela"));
        colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        colPag.setCellValueFactory(new PropertyValueFactory("pagamento"));
        colVenc.setCellValueFactory(new PropertyValueFactory("vencimento"));
        MaskFieldUtil.maxField(txtCodigo, 3);
        MaskFieldUtil.maxField(txtFantasia, 50);  
        txtMov.setDisable(true);
        btnPagar.setDisable(true);
        btnExt.setDisable(true);
        cmbForm.setDisable(true);
        carregaTabela("","","");
    } 
    
    private void carregaTabela(String f1, String f2, String f3)
    {
        DALContasPagar dal = new DALContasPagar();
        List<ParcelasPagar> res = dal.get(f1,f2,f3);
        ObservableList<ParcelasPagar> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);        
    }
    
    private void carregaForma()
    {
        List<Pagamento> cp = new DALPagamento().get();
        cmbForm.setItems(FXCollections.observableArrayList(cp));
        cmbForm.getSelectionModel().select(0);
    }
    
    @FXML
    private void clkCodigo(KeyEvent event) {
        Alert b = new Alert(Alert.AlertType.ERROR);
        
        if(event.getCode().equals(KeyCode.ENTER))
        {
            if(!txtCodigo.getText().isEmpty())
            {
                int cod;
                cod = Integer.parseInt(txtCodigo.getText());
                DALFornecedor dal = new DALFornecedor();
                Fornecedor f;
                f = dal.getPesquisa(cod);
                
                if(f != null)
                {
                    txtFantasia.setText(f.getNome_fant());
                    txtMov.setDisable(false);
                    txtMov.requestFocus();
                }
                else
                {
                    b.setContentText("Código de fornecedor inexistente");
                    b.showAndWait();
                    txtCodigo.setText("");
                    txtFantasia.setText("");
                    txtCodigo.requestFocus();
                }
                
            }
            else
            {
                b.setContentText("Coloque o código antes de dar enter");
                b.showAndWait();
                txtCodigo.requestFocus();
            }            
        }
    }

    @FXML
    private void clkFantasia(KeyEvent event) {
        Alert b = new Alert(Alert.AlertType.ERROR);
        
        if(event.getCode().equals(KeyCode.ENTER))
        {
            if(!txtFantasia.getText().isEmpty())
            {
                String nome;
                nome = txtFantasia.getText().toUpperCase();
                DALFornecedor dal = new DALFornecedor();
                Fornecedor f;
                f = dal.getPesquisa(nome);
                
                if(f != null)
                {
                    txtCodigo.setText(Integer.toString(f.getCodigo()));
                    txtFantasia.setText(f.getNome_fant());
                    txtMov.setDisable(false);
                    txtMov.requestFocus();
                }
                else
                {
                    b.setContentText("Nome de fornecedor inexistente");
                    b.showAndWait();
                    txtCodigo.setText("");
                    txtFantasia.setText("");
                    txtFantasia.requestFocus();
                }
                
            }
            else
            {
                b.setContentText("Coloque o nome fantasia antes de dar enter");
                b.showAndWait();
                txtFantasia.requestFocus();
            }            
        }
    }
    
    @FXML
    private void clkListar(ActionEvent event) {
        String f1 = "", f2 ="" , f3 = "";
        int erro = 0;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert b = new Alert(Alert.AlertType.ERROR);
        Date dtInicio = null;
        Date dtFim = null;
        
        if(!txtCodigo.getText().isEmpty())
        {
            if(!txtMov.getText().isEmpty())
            {
                f1 += "where for_codigo = "+txtCodigo.getText()+" and com_codigo = "+txtMov.getText();
            }                
            else
                f1 += "where for_codigo = "+txtCodigo.getText();  
        } 
        
        if(txtInicial.getValue() == null && txtFinal.getValue() != null || txtInicial.getValue() != null && txtFinal.getValue() == null)
        {
            b.setContentText("Datas devem ser preenchidas ou ficar totalmente limpas");
            b.showAndWait();
            txtInicial.requestFocus();
            erro++;
        }
        else
        {
            if(txtInicial.getValue() != null && txtFinal.getValue() != null)
            {
                dtInicio = java.sql.Date.valueOf(txtInicial.getValue());
                dtFim = java.sql.Date.valueOf(txtFinal.getValue());
            
                if(dtInicio.after(dtFim))
                {
                    b.setContentText("Data inicial não pode ser maior que a final");
                    b.showAndWait();
                    txtInicial.requestFocus();
                    erro++;
                }
                else
                {
                    if(f1.isEmpty())
                        f2 += " where par_data_vencimento between '"+dtInicio+"' and '"+dtFim+"'";
                    else
                        f2 += " and par_data_vencimento between '"+dtInicio+"' and '"+dtFim+"'";
                } 
            }
        }
        
        if(rbtAberto.isSelected())
        {
            if(f1.isEmpty() && f2.isEmpty())
               f3 += "where par_status = false";
            else
                f3 += " and par_status = false";
        }
        
        if(rbtFechado.isSelected())
        {
            if(f1.isEmpty() && f2.isEmpty())
               f3 += "where par_status = true";
            else
                f3 += " and par_status = true";
        }
        carregaTabela(f1, f2, f3);
    }     

    @FXML
    private void clkTabela(MouseEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex()>=0)
        {
           btnPagar.setDisable(false);
           btnExt.setDisable(false);
        }
    }

    @FXML
    private void clkPagar(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert b = new Alert(Alert.AlertType.ERROR);
        Alert c = new Alert(Alert.AlertType.CONFIRMATION);
        if(cmbForm.isDisable())
        {
            cmbForm.setDisable(false);
            carregaForma();            
        }
        else
        {
           ParcelasPagar p = (ParcelasPagar)tabela.getSelectionModel().getSelectedItem();
           if(p.isStatus())
           {    
                b.setContentText("Parcela selecionada ja se encontra paga");
                b.showAndWait(); 
           }
           else
           {
               c.setContentText("Confirma o pagamento?");
               if (c.showAndWait().get() == ButtonType.OK)
               {
                    Pagamento pa = cmbForm.getValue();
                    Pagamento cp = new DALPagamento().get(pa.getDescricao());                    
                    int pag = cp.getCodigo();
                    DALContasPagar dal = new DALContasPagar();
                    Date data = new Date(System.currentTimeMillis());
                    if(dal.pagamento(p.getCom_codigo(), p.getParcela(), data,pag))
                    {
                        a.setContentText("Parcela paga com sucesso");
                        a.showAndWait();
                        carregaTabela("", "", "");
                    }
                    else
                    {
                        a.setContentText("Parcela não paga");
                        a.showAndWait();
                    }
                }
            }
        }
    }

    @FXML
    private void clkEst(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert b = new Alert(Alert.AlertType.ERROR);
        Alert c = new Alert(Alert.AlertType.CONFIRMATION);
        c.setContentText("Confirma o extorno?");
        ParcelasPagar p = (ParcelasPagar)tabela.getSelectionModel().getSelectedItem();
        if(!p.isStatus())
        {
            b.setContentText("Parcela selecionada ja se encontra em aberto");
            b.showAndWait();
        }
        else
        {
            if (c.showAndWait().get() == ButtonType.OK) {
                DALContasPagar dal = new DALContasPagar();
                if(dal.extorno(p.getCom_codigo(), p.getParcela()))
                {
                    a.setContentText("Parcela extornada com sucesso");
                    a.showAndWait();
                    carregaTabela("", "", "");
                }
                else
                {
                    a.setContentText("Parcela não extornada");
                    a.showAndWait();
                }
            }
        }
    }
}