package mercado.Banco;

public class Banco 
{
    static private Conexao con = null;
    static private String url = "jdbc:postgresql://isilo.db.elephantsql.com:5432/yjyujddp";
    static private String username = "yjyujddp" ;
    static private String password = "5K59TBI1sCXdE7zRewa3Xxy60Cv3LRAN";
           
    
    static public Conexao getCon()
    {
        return con;
    }

    private Banco() {} // construtor privado, ou seja, nao pode dar um new em banco, da uma segurança maior no projeto
    
    static public boolean conectar()
    {
        con = new Conexao(); 
        return con.conectar(url, username, password);
    }
}