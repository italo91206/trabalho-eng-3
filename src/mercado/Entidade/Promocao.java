package mercado.Entidade;

import java.util.Date;

public class Promocao {
    private int codigo;
    private String nome;
    private Date data_inicial;
    private Date data_final;
    private double desconto;
    private boolean status;

    public Promocao() {
    }

    public Promocao(int codigo, String nome, Date data_inicial, Date data_final, double desconto, boolean status) {
        this.codigo = codigo;
        this.nome = nome;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.desconto = desconto;
        this.status = status;
    }

    public Promocao(String nome, Date data_inicial, Date data_final, double desconto, boolean status) {
        this.nome = nome;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.desconto = desconto;
        this.status = status;
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

    public Date getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }   
}