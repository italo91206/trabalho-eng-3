package mercado.DAL;

import java.util.List;
import mercado.Banco.Banco;
import mercado.Entidade.Produto;
import mercado.Entidade.Promocao;

public class DALPromocao {
    public boolean salvar (Promocao p, List<Produto> i)
    {
       int cod_promo = 0;
       boolean aux = false;
       String sql = "insert into promocao (promo_nome, promo_data_inicial, promo_data_final, promo_desc, promo_status) values ('#A','#B','#C',#D,#E)";
       sql = sql.replace("#A", p.getNome());
       sql = sql.replace("#B", ""+p.getData_inicial());
       sql = sql.replace("#C", ""+p.getData_final());
       sql = sql.replace("#D", ""+p.getDesconto());       
       sql = sql.replace("#E", ""+p.isStatus());
       aux = Banco.getCon().manipular(sql);        
       if(aux == false)
       {
           return aux;
       }
       cod_promo = Banco.getCon().getMaxPK("Promocao","promo_codigo");
       double preco = 0;
       for (Produto itensPromocao : i) {
            preco = itensPromocao.getPreco() - (itensPromocao.getPreco() / 100 * p.getDesconto());
            sql = "insert into itenspromo (prod_codigo,promo_codigo,prod_preco) values (#A, #B, #C)";
            sql = sql.replace("#A", ""+itensPromocao.getCodigo());
            sql = sql.replace("#B", ""+cod_promo);
            sql = sql.replace("#C", ""+preco);
            aux = Banco.getCon().manipular(sql); 
       }
       return aux;
    }
}