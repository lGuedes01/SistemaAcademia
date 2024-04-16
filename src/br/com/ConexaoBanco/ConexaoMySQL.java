package br.com.ConexaoBanco;//Nome do nosso pacote //

//Classes necessárias para uso de Banco de dados //

import java.sql.*;

//Início da classe de conexão//

public class ConexaoMySQL {
    public static String status = "Não conectou...";

    private final static String serverName = "localhost";    //caminho do servidor do BD

    private final static String mydatabase = "Academia";        //nome do seu banco de dados

    private static final String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

    private static final String username = "root";        //nome de um usuário de seu BD

    private static final String password = "root";      //sua senha de acesso

//Método Construtor da Classe//
    public ConexaoMySQL() {

    }


//Método de Conexão//

    public static java.sql.Connection getConexaoMySQL() {

        Connection connection = null;          //atributo do tipo Connection

        try {

            // Carregando o JDBC Driver padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);

            // Configurando a nossa conexão com um banco de dados//
            connection = DriverManager.getConnection(url, username, password);

            //Testa sua conexão//
            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->Não foi possivel realizar conexão");

            }



            return connection;



        } catch (ClassNotFoundException e) {  //Driver não encontrado



            System.out.println("O driver especificado nao foi encontrado.");

            return null;

        } catch (SQLException e) {

            //Não conseguindo se conectar ao banco

            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }
    }

    //Método que retorna o status da sua conexão//

    public static String statusConection() {

        return status;

    }



    //Método que fecha sua conexão//

    public static boolean FecharConexao() {

        try {

            ConexaoMySQL.getConexaoMySQL().close();

            return true;

        } catch (SQLException e) {

            return false;

        }



    }



    //Método que reinicia sua conexão//

    public static java.sql.Connection ReiniciarConexao() {

        FecharConexao();



        return ConexaoMySQL.getConexaoMySQL();

    }

}
