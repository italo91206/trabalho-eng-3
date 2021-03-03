package mercado.Entidade;

public class FormaPagamento {
    private int codigo;
    private String nome;

    public FormaPagamento() {
    }

    public FormaPagamento(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public FormaPagamento(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }   
}