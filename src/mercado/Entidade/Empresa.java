package mercado.Entidade;

public class Empresa {    
    private int codigo;
    private String cnpj;
    private String nome_fant;
    private String razao_social;
    private String ie;
    private String endereco;
    private String cep;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String email;
    private String telefone;
    private byte[] logo;

    public Empresa(int codigo,String cnpj, String nome_fant, String razao_social, String ie, String endereco, String cep, int numero, String bairro, String cidade, String uf, String email, String telefone, byte[] logo) {
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.nome_fant = nome_fant;
        this.razao_social = razao_social;
        this.ie = ie;
        this.endereco = endereco;
        this.cep = cep;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.email = email;
        this.telefone = telefone;
        this.logo = logo;
    }

    public Empresa(String cnpj, String nome_fant, String razao_social, String ie, String endereco, String cep, int numero, String bairro, String cidade, String uf, String email, String telefone, byte[] logo) {
        this.cnpj = cnpj;
        this.nome_fant = nome_fant;
        this.razao_social = razao_social;
        this.ie = ie;
        this.endereco = endereco;
        this.cep = cep;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.email = email;
        this.telefone = telefone;
        this.logo = logo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getNome_fant() {
        return nome_fant;
    }

    public void setNome_fant(String nome_fant) {
        this.nome_fant = nome_fant;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }    
            
}