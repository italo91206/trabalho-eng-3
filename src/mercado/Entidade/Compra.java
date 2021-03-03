package mercado.Entidade;

import java.time.LocalDateTime;

public class Compra {
    private int codigo;
    private boolean fiado;
    private double valor;
    private double acrescimo;
    private double desconto;
    private LocalDateTime dtcompra;
    private Fornecedor fornecedor;
    private String tipopagamento;

    public Compra() {
    }

    public Compra(int codigo, boolean fiado, double valor, double acrescimo, double desconto, LocalDateTime dtcompra, Fornecedor fornecedor, String tipopagamento)
    {
        this.codigo = codigo;
        this.fiado = fiado;
        this.valor = valor;
        this.acrescimo = acrescimo;
        this.desconto = desconto;
        this.dtcompra = dtcompra;
        this.fornecedor = fornecedor;
        this.tipopagamento = tipopagamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean isFiado() {
        return fiado;
    }

    public void setFiado(boolean fiado) {
        this.fiado = fiado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public LocalDateTime getDtcompra() {
        return dtcompra;
    }

    public void setDtcompra(LocalDateTime dtcompra) {
        this.dtcompra = dtcompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTipopagamento() {
        return tipopagamento;
    }

    public void setTipopagamento(String tipopagamento) {
        this.tipopagamento = tipopagamento;
    }    
    
}