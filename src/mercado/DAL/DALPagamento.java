package mercado.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mercado.Banco.Banco;
import mercado.Entidade.Pagamento;

public class DALPagamento
{
    public List<Pagamento> get ()
    {
        List<Pagamento> p = new ArrayList();
        String sql = "select * from pagamento";
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                p.add(new Pagamento(rs.getInt("pag_codigo"), rs.getString("pag_descricao")));
            }
        } 
        catch (SQLException ex) 
        {

        } 
        return p;
    }
    
    
    public Pagamento get (String f1)
    {
        
        String sql = "select * from pagamento where pag_descricao like '%"+f1+"%'";
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                Pagamento p = new Pagamento(rs.getInt("pag_codigo"), rs.getString("pag_descricao"));
                return p;
            }
        } 
        catch (SQLException ex) 
        {

        } 
        return null;
    }
}
