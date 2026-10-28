package br.com.texasbarbearia.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    
  
   private static final String URL = "jdbc:mysql://mysql-317a5ab7-samueljose767-3931.d.aivencloud.com:26899/defaultdb?useSSL=true&requireSSL=true&autoReconnect=true";
    
    
    private static final String USUARIO = "avnadmin";
    
    // Puxa a senha de forma segura diretamente do painel do Render
    private static final String SENHA = System.getenv("DB_PASSWORD"); 

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}