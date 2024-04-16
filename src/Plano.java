import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.com.ConexaoBanco.ConexaoMySQL;
import java.sql.*;

public class Plano {
    private int idPlano;
    private String nome;
    private float preco;

    public Plano(String nome, int idPlano, float preco){
        this.nome = nome;
        this.idPlano = idPlano;
        this.preco = preco;
    }

    public int getId() {
        return idPlano;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    public void inserirPlano(java.sql.Connection con){
        String sql = "INSERT INTO Plano (idPlano, nome, preco) VALUES(?,?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql)){
          statement.setInt(1, idPlano);
          statement.setString(2, nome);
          statement.setFloat(3, preco);
          statement.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void updatePrecoPlano(java.sql.Connection con) {
        String sql = "UPDATE Plano SET preco = ? where idPlano = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setFloat(1, preco);
            statement.setInt(2, idPlano);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
