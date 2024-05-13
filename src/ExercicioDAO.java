import br.com.ConexaoBanco.ConexaoMySQL;
import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExercicioDAO {

    private Exercicio preencherExercicio(ResultSet rs) throws SQLException{
        return new Exercicio(rs.getInt(1), rs.getString(2), rs.getString(3));
    }
    public void inserirExercicio(Exercicio ex){
        Connection con = ConexaoMySQL.abrir();
        String sql = "INSERT INTO Exercicio (idExercicio, nome, musculos) VALUES(?,?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1, ex.getId());
            statement.setString(2, ex.getNome());
            statement.setString(3, ex.getMusculos());
            statement.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public Exercicio buscarExercicio(int idExercicio){
        Connection con = ConexaoMySQL.abrir();
        String sql = "SELECT * from Exercicio WHERE idExercicio = ?";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1, idExercicio);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return preencherExercicio(rs);
            }
            else {
                throw new WrongArgumentException("Esse exercício não existe no sistema");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Exercicio> buscarTodos(){
        Connection con = ConexaoMySQL.abrir();
        ArrayList<Exercicio> listEx = new ArrayList<Exercicio>();
        String sql = "SELECT * from Exercicio";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                listEx.add(preencherExercicio(rs));
            }
            return listEx;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void alterarExercicio(Exercicio ex){
        Connection con = ConexaoMySQL.abrir();
        String sql = "UPDATE Exercicio SET nome = ?, musculos = ? WHERE idExercicio = ?";
        try(PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, ex.getNome());
            statement.setString(2, ex.getMusculos());
            statement.setInt(3, ex.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void excluirExercicio(Exercicio ex){
        Connection con = ConexaoMySQL.abrir();
        String sql = "DELETE FROM exercicio WHERE idExercicio = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, ex.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
