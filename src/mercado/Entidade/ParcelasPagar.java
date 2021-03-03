package mercado.Entidade;

import java.util.Date;

public class ParcelasPagar {
    private int com_codigo;
    private int ctp_codigo;
    private int for_codigo;
    private String descricao;
    private int parcela;
    private double valor;
    private Date pagamento;
    private Date vencimento;
    private int pag_codigo;
    private boolean status;

    public ParcelasPagar() {
    }

    public ParcelasPagar(int com_codigo, int ctp_codigo, int for_codigo, String descricao, int parcela, double valor, Date pagamento, Date vencimento, int pag_codigo, boolean status) {
        this.com_codigo = com_codigo;
        this.ctp_codigo = ctp_codigo;
        this.for_codigo = for_codigo;
        this.descricao = descricao;
        this.parcela = parcela;
        this.valor = valor;
        this.pagamento = pagamento;
        this.vencimento = vencimento;
        this.pag_codigo = pag_codigo;
        this.status = status;
    }

    public ParcelasPagar(int ctp_codigo, int for_codigo, String descricao, int parcela, double valor, Date pagamento, Date vencimento, int pag_codigo, boolean status) {
        this.ctp_codigo = ctp_codigo;
        this.for_codigo = for_codigo;
        this.descricao = descricao;
        this.parcela = parcela;
        this.valor = valor;
        this.pagamento = pagamento;
        this.vencimento = vencimento;
        this.pag_codigo = pag_codigo;
        this.status = status;
    }

    public int getCom_codigo() {
        return com_codigo;
    }

    public void setCom_codigo(int com_codigo) {
        this.com_codigo = com_codigo;
    }

    public int getCtp_codigo() {
        return ctp_codigo;
    }

    public void setCtp_codigo(int ctp_codigo) {
        this.ctp_codigo = ctp_codigo;
    }

    public int getFor_codigo() {
        return for_codigo;
    }

    public void setFor_codigo(int for_codigo) {
        this.for_codigo = for_codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getPagamento() {
        return pagamento;
    }

    public void setPagamento(Date pagamento) {
        this.pagamento = pagamento;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public int getPag_codigo() {
        return pag_codigo;
    }

    public void setPag_codigo(int pag_codigo) {
        this.pag_codigo = pag_codigo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }   
}