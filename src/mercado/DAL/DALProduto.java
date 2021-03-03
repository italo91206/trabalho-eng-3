package mercado.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mercado.Banco.Banco;
import mercado.Entidade.Produto;

public class DALProduto
{
    public boolean salvar (Produto p)
    {
       String sql = "insert into produto (prod_referencia,prod_descricao,prod_preco,prod_preco_custo,prod_preco_promo,prod_estoque_minimo,prod_estoque,prod_status) values ('#A','#B',#C,#D,#E,#F,#G,'#H')";
       sql = sql.replace("#A", p.getReferencia());
       sql = sql.replace("#B", p.getDescricao());
       sql = sql.replace("#C", ""+p.getPreco());
       sql = sql.replace("#D", ""+p.getPreco_custo());
       sql = sql.replace("#E", ""+p.getPreco_promo());
       sql = sql.replace("#F", ""+p.getEstoque_minimo());
       sql = sql.replace("#G", ""+p.getEstoque());
       sql = sql.replace("#H", ""+p.isStatus());
       return Banco.getCon().manipular(sql); 
    }
    
    public boolean alterar (Produto p, int Cod)// NAÕ ALTERA STATUS, ESTOQUE E NEM PREÇO DE PROMOÇÃO
    {
       String sql = "update produto set prod_referencia = '#A', prod_descricao = '#B', prod_preco = #C, prod_preco_custo = #D, prod_estoque_minimo = #E where prod_codigo = " + Cod;
       sql = sql.replace("#A", p.getReferencia());
       sql = sql.replace("#B", p.getDescricao());
       sql = sql.replace("#C", ""+p.getPreco());
       sql = sql.replace("#D", ""+p.getPreco_custo());
       sql = sql.replace("#E", ""+p.getEstoque_minimo());
       return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar (int codigo)
    {
        return Banco.getCon().manipular("update produto set prod_status = false where prod_codigo = " + codigo);
    }
    
    public List<Produto> getAtivo ()
    {
        List<Produto> p = new ArrayList();
        String sql = "select * from produto where prod_status = true order by prod_descricao asc";
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                p.add(new Produto(rs.getInt("prod_codigo"),rs.getString("prod_referencia"),rs.getString("prod_descricao"),rs.getDouble("prod_preco"),rs.getDouble("prod_preco_custo"),
                                  rs.getDouble("prod_preco_promo"),rs.getInt("prod_estoque"),rs.getInt("prod_estoque_minimo")));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return p;
    }
    
    public List<Produto> getAtivo (String filtro)
    {
        List<Produto> p = new ArrayList();
        String sql = "select * from produto where prod_status = true "; //order by prod_descricao asc
        if(!filtro.isEmpty())
        {
            sql+=" and "+filtro+" order by prod_descricao asc";
        }
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                p.add(new Produto(rs.getInt("prod_codigo"),rs.getString("prod_referencia"),rs.getString("prod_descricao"),rs.getDouble("prod_preco"),rs.getDouble("prod_preco_custo"),
                                  rs.getDouble("prod_preco_promo"),rs.getInt("prod_estoque"),rs.getInt("prod_estoque_minimo")));
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return p;
    }
    
    public Produto get (int codigo)
    {
        
        String sql = "select * from produto where prod_codigo = " + codigo + " and prod_status = true";
        ResultSet rs = Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                Produto p = new Produto(rs.getInt("prod_codigo"),rs.getString("prod_referencia"),rs.getString("prod_descricao"),rs.getDouble("prod_preco"),rs.getDouble("prod_preco_custo"),
                                  rs.getDouble("prod_preco_promo"),rs.getInt("prod_estoque"),rs.getInt("prod_estoque_minimo"));
                
                return p;
            }
        } 
        catch (SQLException ex) 
        {
            
        }
        return null;
    }
}