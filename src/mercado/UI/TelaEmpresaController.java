package mercado.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mercado.Banco.Banco;
import mercado.DAL.DALEmpresa;
import mercado.Entidade.Empresa;
import mercado.Util.MaskFieldUtil;
import mercado.Util.Messages;

public class TelaEmpresaController implements Initializable {

    @FXML
    private JFXButton btnNovo;
    @FXML
    private JFXButton btnAlterar;
    @FXML
    private JFXButton btnConfirmar;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXTextField txtCNPJ;
    @FXML
    private JFXTextField txtNomeFant;
    @FXML
    private JFXTextField txtRazao;
    @FXML
    private JFXTextField txtIE;
    @FXML
    private JFXTextField txtEndereco;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXTextField txtCEP;
    @FXML
    private JFXTextField txtCidade;
    @FXML
    private JFXTextField txtUF;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private ImageView imgLogo; 
    
    @FXML
    private AnchorPane pndados;
    @FXML
    private TableView<Empresa> tabela;
    @FXML
    private TableColumn<Empresa, Integer> colCodigo;
    @FXML
    private TableColumn<Empresa, String> colCNPJ;
    @FXML
    private TableColumn<Empresa, String> colNome;
    @FXML
    private TableColumn<Empresa, String> colRazao;    
    @FXML
    private AnchorPane pnEmpresa;
    @FXML
    private JFXTextField txtCodigo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeout();
        MaskFieldUtil.cnpjField(txtCNPJ);
        MaskFieldUtil.cepField(txtCEP);
        MaskFieldUtil.foneField(txtTelefone);
        MaskFieldUtil.numericField(txtNumero);
        colCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colCNPJ.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colNome.setCellValueFactory(new PropertyValueFactory("nome_fant"));
        colRazao.setCellValueFactory(new PropertyValueFactory("razao_social"));
        estadoOriginal();       
    }   
    
    private void fadeout()
    {           
        MaskFieldUtil.numericField(txtNumero);
        MaskFieldUtil.maxField(txtNumero,6);           
        MaskFieldUtil.maxField(txtCNPJ, 20);
        MaskFieldUtil.maxField(txtNomeFant, 40);   
        MaskFieldUtil.maxField(txtRazao, 40);
        MaskFieldUtil.maxField(txtIE, 40);
        MaskFieldUtil.maxField(txtEndereco, 40);
        MaskFieldUtil.maxField(txtCEP, 9);
        MaskFieldUtil.maxField(txtBairro, 30);
        MaskFieldUtil.maxField(txtCidade, 40);
        MaskFieldUtil.maxField(txtUF, 2);
        MaskFieldUtil.maxField(txtEmail, 40);
        MaskFieldUtil.maxField(txtTelefone, 20);
    }
    
    private void estadoOriginal()
    {
        btnNovo.setDisable(false);        
        btnConfirmar.setDisable(true);
        btnCancelar.setDisable(false);
        imgLogo.setImage(null);
        pndados.setDisable(true);
        pnEmpresa.setDisable(false);
        ObservableList<Node> componentes = pndados.getChildren();//”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof TextInputControl)//textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }            
        }
        carregaTabela("");
    }   
    
    private int carregaTabela(String filtro) {
        DALEmpresa dal = new DALEmpresa();
        List<Empresa> res = dal.get(filtro);
        ObservableList <Empresa> modelo;
        modelo = FXCollections.observableArrayList(res);
        tabela.setItems(modelo);
        return res.size();
    }
    
    private void estadoEdicao() {       
        pndados.setDisable(false);
        pnEmpresa.setDisable(true);
        btnNovo.setDisable(true);
        btnAlterar.setDisable(true);
        btnConfirmar.setDisable(false);        
        txtCNPJ.requestFocus();
        txtCodigo.setDisable(true);
    }
    
    private boolean isCNPJ(String CNPJ)
    {
        CNPJ = CNPJ.replace(".","").replace("/","").replace("-","");
        
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
            CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
            CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
            CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
            CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
            (CNPJ.length() != 14))
        return(false);
        
        char dig13, dig14;
        int sm, i, r, num, peso;
        
        try
        {
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--)
            {

                num = (int)(CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig13 = '0';
      else dig13 = (char)((11-r) + 48);
      sm = 0;
      peso = 2;
      for (i=12; i>=0; i--) {
        num = (int)(CNPJ.charAt(i)- 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }

        r = sm % 11;
        if ((r == 0) || (r == 1))
           dig14 = '0';
        else dig14 = (char)((11-r) + 48);

        if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
           return(true);
        else return(false);
      } catch (InputMismatchException erro) {
          return(false);
      }
  }   
    
    @FXML
    private void clkNovo(ActionEvent event) throws SQLException {
        Messages msg = new Messages();        
        Banco.conectar();
        ResultSet rs = Banco.getCon().consultar("SELECT  * FROM empresa");
        int rows = 0;
        rows = rs.getRow();
        try {

            if (rs.next()) {
                rows = rs.getRow();
            }

        } catch (SQLException ex) {

        }
        if (rows >= 1) {
            msg.Error("Erro!", "A empresa ja cadastrada, apenas pode ser alterada");

        } else {
            estadoEdicao();            
        }
    }
    
    @FXML
    private void clkAlterar(ActionEvent event) {
        if(tabela.getSelectionModel().getSelectedItem()!=null)
        {
            Empresa e = (Empresa)tabela.getSelectionModel().getSelectedItem(); 
            txtCodigo.setText(""+e.getCodigo());
            txtCNPJ.setText(e.getCnpj());
            txtNomeFant.setText(e.getNome_fant());
            txtIE.setText(e.getIe());
            txtRazao.setText(e.getRazao_social());
            txtEndereco.setText(e.getEndereco());           
            txtNumero.setText(""+e.getNumero());
            txtBairro.setText(e.getBairro());
            txtCEP.setText(e.getCep());
            txtCidade.setText(e.getCidade());
            txtUF.setText(e.getUf());
            txtTelefone.setText(e.getTelefone());
            txtEmail.setText(e.getTelefone());            
            estadoEdicao();            
        }
        
    }
    
    @FXML
    private void clkConfirmar(ActionEvent event) {        
       
        int erro = 0;
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        Alert b = new Alert(Alert.AlertType.ERROR);
        
        if(txtCNPJ.getText().equals(""))
            erro++;
        if(txtNomeFant.getText().equals(""))
            erro++;
        if(txtIE.getText().equals(""))
            erro++;
        if(txtRazao.getText().equals(""))
            erro++;
        if(txtEndereco.getText().equals(""))
            erro++;
        if(txtNumero.getText().equals(""))
            erro++;
        if(txtBairro.getText().equals(""))
            erro++;
        if(txtCEP.getText().equals(""))
            erro++;
        if(txtCidade.getText().equals(""))
            erro++;
        if(txtUF.getText().equals(""))
            erro++;
        if(txtTelefone.getText().equals(""))
            erro++;
        if(txtEmail.getText().equals(""))
            erro++;
        
        if(erro == 0 && isCNPJ(txtCNPJ.getText()))
        {
            int num = Integer.parseInt(txtNumero.getText());
            
            Empresa e = new Empresa(txtCNPJ.getText().toUpperCase(), txtNomeFant.getText().toUpperCase(), txtRazao.getText().toUpperCase(), txtIE.getText().toUpperCase(),
                                    txtEndereco.getText().toUpperCase(), txtCEP.getText().toUpperCase(), num, txtBairro.getText().toUpperCase(),
                                    txtCidade.getText().toUpperCase(), txtUF.getText().toUpperCase(), txtEmail.getText().toUpperCase(), txtTelefone.getText().toUpperCase(),null);
            DALEmpresa dal = new DALEmpresa();
            if(txtCodigo.getText().equals(""))
            {
                if(dal.salvar(e))
                {
                    a.setContentText("Empresa gravada com sucesso");
                    a.showAndWait();
                }
                else
                {
                    b.setContentText("Erro ao gravar o empresa");
                    b.showAndWait();
                }                
            }
            else
            {
                if(dal.alterar(e))
                {
                    a.setContentText("Empresa alterada com sucesso");
                    a.showAndWait();
                }
                else
                {
                    b.setContentText("Erro ao alterar a empresa");
                    b.showAndWait();
                }                
            }
            estadoOriginal();  
        } 
        else if(erro != 0)
        {
           b.setContentText("Alguns campos estão vazios!");
           b.showAndWait();
        }
        else
        {
            b.setContentText("CNPJ digitado é invalido");
            b.showAndWait();
        }
    }        
    
    @FXML
    private void clkCancelar(ActionEvent event) {
        if (!pndados.isDisabled())//encontra em estado de edição
        {
            estadoOriginal();
        } else {
            btnNovo.getScene().getWindow().hide();//fecha a janela
        }
    }   

    @FXML
    private void clkTabela(MouseEvent event) {
        if(tabela.getSelectionModel().getSelectedIndex()>=0)        
           btnAlterar.setDisable(false);        
    }
}