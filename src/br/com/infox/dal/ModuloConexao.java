package br.com.infox.dal;

import java.sql.*; // pega tudo relacionado a conexao.
import javax.swing.JOptionPane;

public class ModuloConexao {
        

    // Metodo responsavel por estabelecer conexao com banco
    public static Connection conector() { // Connection vem do import
     
        java.sql.Connection conexao = null;
        // A linha abaixo "chama" o driver
        String driver = "com.mysql.jdbc.Driver"; // nao vai funcionar se nao importar o driver
        // Armazenando informaçoes referente ao banco
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";

        
        // Estabelecendo conexao com banco
        try {
            Class.forName(driver); // Executa o arquivo do driver
            conexao = DriverManager.getConnection(url, user, password); // Abri conexao passando url, user, password e armazena
          
            return conexao; // Retorna conexao
        } catch (Exception e) {
            // a linha abaixo mostra o erro caso nao conecte com banco
            //System.out.println("Deu erro "+e);  
            JOptionPane.showMessageDialog(null, e,null,JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
/**
public class ModuloConexao {

    String driver = "com.mysql.cj.jdbc.Driver"; //Classe do driver JDBC
    String banco = "dbinfox"; //Nome do Banco criado
    String host = "localhost"; //Maquina onde está o banco
    String str_conn = "jdbc:mysql://" + host + ":3306/" + banco; //URL de conexão
    String usuario = "root"; //Usuário do banco
    String senha = ""; //Senha de conexão

    public Statement getConexao() {
        java.sql.Statement stmt = null;
        try {
            //Class.forName(driver); //Carrega o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            String BD_URL = "jdbc:mysql://localhost:port/bd_name?useTimezone=true&serverTimezone=UTC";
            //Obtém a conexão com o banco
            Connection conn = DriverManager.getConnection(str_conn, usuario, senha);

            //Cria um statement para podermos mandar um SQL para o banco
           stmt = conn.createStatement();

        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível carregar o driver.");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Problema com o SQL");
            ex.printStackTrace();
        }
        return stmt;
    }
    **/



}
