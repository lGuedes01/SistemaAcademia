import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import br.com.ConexaoBanco.ConexaoMySQL;
import java.sql.*;

public class Exercicio {
    private int idExercicio;
    private String nome;
    private String musculos;

    public Exercicio(int idExercicio, String nome, String musculos){
        this.idExercicio = idExercicio;
        this.nome = nome;
        this.musculos = musculos;
    }

    public int getId(){
        return idExercicio;
    }

    public String getNome(){
        return nome;
    }

    public String getMusculos(){
        return musculos;
    }

    public void inserirExercicio(java.sql.Connection con){
        String sql = "INSERT INTO Exercicio (idExercicio, nome, musculos) VALUES(?,?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql)){
          statement.setInt(1, idExercicio);
          statement.setString(2, nome);
          statement.setString(3, musculos);
          statement.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
}
