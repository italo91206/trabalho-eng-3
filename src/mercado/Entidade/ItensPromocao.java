package mercado.Entidade;

public class ItensPromocao {
    private Produto prod_codigo;
    private Promocao promo_codigo;
    private double preco;

    public ItensPromocao() {
    }

    public ItensPromocao(Produto prod_codigo, Promocao promo_codigo, double preco) {
        this.prod_codigo = prod_codigo;
        this.promo_codigo = promo_codigo;
        this.preco = preco;
    }

    public Produto getProd_codigo() {
        return prod_codigo;
    }

    public void setProd_codigo(Produto prod_codigo) {
        this.prod_codigo = prod_codigo;
    }

    public Promocao getPromo_codigo() {
        return promo_codigo;
    }

    public void setPromo_codigo(Promocao promo_codigo) {
        this.promo_codigo = promo_codigo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }   
}