package mercado.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mercado.Banco.Banco;
import mercado.Entidade.Empresa;

public class DALEmpresa
{
    
    public boolean salvar (Empresa e)
    {
        String sql = "insert into empresa (emp_cnpj, emp_nome_fantasia, emp_razao_social, emp_ie, emp_endereco, emp_cep, emp_numero, emp_bairro, emp_cidade, emp_uf, emp_email, emp_telefone, emp_logo)"
                   + "values('#A', '#B', '#C', '#D', '#E', '#F', #G, '#H', '#I', '#J', '#K', '#L', '#M')";
        sql = sql.replace("#A", e.getCnpj());
        sql = sql.replace("#B", e.getNome_fant());
        sql = sql.replace("#C", e.getRazao_social());
        sql = sql.replace("#D", e.getIe());
        sql = sql.replace("#E", e.getEndereco());
        sql = sql.replace("#F", e.getCep());
        sql = sql.replace("#G", "" + e.getNumero());
        sql = sql.replace("#H", e.getBairro());
        sql = sql.replace("#I", e.getCidade());
        sql = sql.replace("#J", e.getUf());
        sql = sql.replace("#K", e.getEmail());
        sql = sql.replace("#L", e.getTelefone());
        sql = sql.replace("#M", "" + e.getLogo());
        
        return Banco.getCon().manipular(sql);        
    }
    
    public boolean alterar (Empresa e)
    {
        String sql = "update empresa set emp_cnpj = '#M', emp_nome_fantasia = '#A', emp_razao_social = '#B', emp_ie = '#C', emp_endereco = '#D', emp_cep = '#E', emp_numero = '#F',"
                   + "emp_bairro = '#G', emp_cidade = '#H', emp_uf = '#I', emp_email = '#J', emp_telefone = '#K', emp_logo = '#L'";
        sql = sql.replace("#A", e.getNome_fant());
        sql = sql.replace("#B", e.getRazao_social());
        sql = sql.replace("#C", e.getIe());
        sql = sql.replace("#D", e.getEndereco());
        sql = sql.replace("#E", e.getCep());
        sql = sql.replace("#F", "" + e.getNumero());
        sql = sql.replace("#G", e.getBairro());
        sql = sql.replace("#H", e.getCidade());
        sql = sql.replace("#I", e.getUf());
        sql = sql.replace("#J", e.getEmail());
        sql = sql.replace("#K", e.getTelefone());
        sql = sql.replace("#L", "" + e.getLogo());
        sql = sql.replace("#M", e.getCnpj());
        
        return Banco.getCon().manipular(sql);
    }
    
    public List<Empresa> get ()
    {
        List<Empresa> e = new ArrayList();
        String sql = "select * from empresa";
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            while(rs.next())
            {
                e.add(new Empresa(rs.getString("emp_cnpj"), rs.getString("emp_nome_fantasia"),
                rs.getString("emp_razao_social"), rs.getString("emp_ie"), rs.getString("emp_endereco"), rs.getString("emp_cep"),
                rs.getInt("emp_numero"), rs.getString("emp_bairro"),rs.getString("emp_cidade"), rs.getString("emp_uf"),
                rs.getString("emp_email"), rs.getString("emp_telefone "), rs.getBytes("emp_logo")));
            }
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
        }        
        return e;
    }
    
    public List<Empresa> get(String filtro) {
        List<Empresa> empre = new ArrayList();        
        String sql = "select * from empresa";
        if (!filtro.isEmpty()) {
            sql += " where " + filtro;
        }
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            while (rs.next()) {

                empre.add(new Empresa(rs.getInt("emp_codigo"), rs.getString("emp_cnpj"), rs.getString("emp_nome_fantasia"),
                rs.getString("emp_razao_social"), rs.getString("emp_ie"), rs.getString("emp_endereco"), rs.getString("emp_cep"),
                rs.getInt("emp_numero"), rs.getString("emp_bairro"),rs.getString("emp_cidade"), rs.getString("emp_uf"),
                rs.getString("emp_email"), rs.getString("emp_telefone"), rs.getBytes("emp_logo")));
            }
        } catch (SQLException ex) {

        }
        return empre;
    }
    
    public boolean exist ()
    {        
        int cont = 0;        
        String sql = "select * from empresa";        
        ResultSet rs = Banco.getCon().consultar(sql);        
        try
        {
            cont = rs.getRow();
            if(rs.next())
            {
                cont = rs.getRow();
            }
        }
        catch (SQLException ex)
        {
            System.out.print(ex);
        }
        return cont > 0;
    }
}