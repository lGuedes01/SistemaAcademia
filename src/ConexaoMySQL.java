import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    static final String SENHA = "root";
    static final String USUARIO = "root";
    static final String URL_CONEXAO = "jdbc:mysql://localhost/Academia";

    public static java.sql.Connection abrir(){
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Conexão não pôde ser feita.");
        }
        return c;
    }
}