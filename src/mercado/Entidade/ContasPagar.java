package mercado.Entidade;

public class ContasPagar {
    private int codigo;
    private String descricao;
    private double valor;
    private int qtd_parcelas;

    public ContasPagar() {
    }

    public ContasPagar(int codigo, String descricao, double valor, int qtd_parcelas) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.qtd_parcelas = qtd_parcelas;
    }

    public ContasPagar(String descricao, double valor, int qtd_parcelas) {
        this.descricao = descricao;
        this.valor = valor;
        this.qtd_parcelas = qtd_parcelas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtd_parcelas() {
        return qtd_parcelas;
    }

    public void setQtd_parcelas(int qtd_parcelas) {
        this.qtd_parcelas = qtd_parcelas;
    }
}