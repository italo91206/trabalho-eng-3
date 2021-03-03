package mercado.Entidade;

public class Produto {
    
    private int codigo;
    private String referencia;
    private String descricao;
    private double preco;
    private double preco_custo;
    private double preco_promo;
    private int estoque;
    private int estoque_minimo;
    private boolean status;

    public Produto(int codigo, String referencia, String descricao, double preco, double preco_custo, double preco_promo, int estoque, int estoque_minimo, boolean status) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.descricao = descricao;
        this.preco = preco;
        this.preco_custo = preco_custo;
        this.preco_promo = preco_promo;
        this.estoque = estoque;
        this.estoque_minimo = estoque_minimo;
        this.status = status;
    }

    public Produto(String referencia, String descricao, double preco, double preco_custo, double preco_promo, int estoque, int estoque_minimo, boolean status) {
        this.referencia = referencia;
        this.descricao = descricao;
        this.preco = preco;
        this.preco_custo = preco_custo;
        this.preco_promo = preco_promo;
        this.estoque = estoque;
        this.estoque_minimo = estoque_minimo;
        this.status = status;
    }

    public Produto(int codigo, String referencia, String descricao, double preco, int estoque) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto(int codigo, String referencia, String descricao, double preco, double preco_custo, double preco_promo, int estoque, int estoque_minimo) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.descricao = descricao;
        this.preco = preco;
        this.preco_custo = preco_custo;
        this.preco_promo = preco_promo;
        this.estoque = estoque;
        this.estoque_minimo = estoque_minimo;
    }

    public Produto() {
        
    }   
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco_custo() {
        return preco_custo;
    }

    public void setPreco_custo(double preco_custo) {
        this.preco_custo = preco_custo;
    }

    public double getPreco_promo() {
        return preco_promo;
    }

    public void setPreco_promo(double preco_promo) {
        this.preco_promo = preco_promo;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(int estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    } 
    
}