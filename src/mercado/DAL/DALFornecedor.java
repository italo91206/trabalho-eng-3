package mercado.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import mercado.Banco.Banco;
import mercado.Entidade.Fornecedor;

public class DALFornecedor {
    
    public Fornecedor getPesquisa (int cod)
    {        
        String sql = "select * from fornecedor where for_codigo = "+cod;
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                Fornecedor f = new Fornecedor(rs.getInt("for_codigo"),rs.getString("for_nome_fantasia"));
                
                return f;
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return null;
    }
    
    public Fornecedor getPesquisa (String nome)
    {        
        String sql = "select * from fornecedor where for_nome_fantasia like '%"+nome+"%'";
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                Fornecedor f = new Fornecedor(rs.getInt("for_codigo"),rs.getString("for_nome_fantasia"));
                
                return f;
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return null;
    }
}
