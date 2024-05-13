package br.com.ConexaoBanco;//Nome do nosso pacote //

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    public static final String JDBC_DRIVER = "org.mysql.cj.jdbc.Driver";
    static final String SENHA = "root";
    static final String USUARIO = "root";
    static final String URL_CONEXAO = "jdbc:mysql://localhost:5433/livraria";

    public static java.sql.Connection abrir(){
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}