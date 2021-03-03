package mercado.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mercado.Banco.Banco;
import mercado.Entidade.ParcelasPagar;

public class DALContasPagar
{
    public List<ParcelasPagar> get (String f1, String f2 ,String f3)
    {
        List<ParcelasPagar> p = new ArrayList();
        String sql = "select * from parcelaspag ";
        if(f1.isEmpty() && f2.isEmpty() && f3.isEmpty())
        {
            ResultSet rs = Banco.getCon().consultar(sql);
            try 
            {
                while(rs.next())
                {
                    p.add(new ParcelasPagar(rs.getInt("com_codigo"), rs.getInt("con_codigo"), rs.getInt("for_codigo"), rs.getString("par_descricao"), rs.getInt("par_parcela"),
                                      rs.getDouble("par_valor"), rs.getDate("par_data_pagamento"), rs.getDate("par_data_vencimento"), rs.getInt("for_pag_codigo"), rs.getBoolean("par_status")));
                }
            } 
            catch (SQLException ex) 
            {

            }            
        }
        else
        {
            sql += f1;
            sql += f2;
            sql += f3;
            ResultSet rs = Banco.getCon().consultar(sql);
            try 
            {
                while(rs.next())
                {
                    p.add(new ParcelasPagar(rs.getInt("com_codigo"), rs.getInt("con_codigo"), rs.getInt("for_codigo"), rs.getString("par_descricao"), rs.getInt("par_parcela"),
                                      rs.getDouble("par_valor"), rs.getDate("par_data_pagamento"), rs.getDate("par_data_vencimento"), rs.getInt("for_pag_codigo"), rs.getBoolean("par_status")));
                }
            } 
            catch (SQLException ex) 
            {

            }  
        }
        return p;
    }    
    
    public boolean extorno (int mov, int parc)
    {
        String sql = "update parcelaspag set par_data_pagamento = null, for_pag_codigo = null, par_status = 'false' where com_codigo = "+mov+" and par_parcela = "+parc;
        return Banco.getCon().manipular(sql); 
    }
    
    public boolean pagamento (int mov, int parc, Date data, int pag)
    {
        String sql = "update parcelaspag set par_data_pagamento = '"+data+"', for_pag_codigo = "+pag+", par_status = 'true' where com_codigo = "+mov+" and par_parcela = "+parc;
        return Banco.getCon().manipular(sql); 
    }
}