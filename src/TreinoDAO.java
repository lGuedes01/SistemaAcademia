import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.*;
import java.util.ArrayList;

public class TreinoDAO {
    private Treino preencherTreino(ResultSet rs) throws SQLException{
        Treino tr = new Treino(rs.getInt(2), rs.getString(3));
        tr.setIdTreino(rs.getInt(1));
        return tr;
    }
    public Treino inserirTreino(Treino tr){
        String sql = "INSERT INTO Treino (idAluno, nome) VALUES(?,?)";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, tr.getIdAluno());
            statement.setString(2, tr.getNome());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                tr.setIdTreino(rs.getInt(1));
            }
            return tr;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Treino buscarTreino(int idAluno, int idTreino){
        String sql = "SELECT * from Treino WHERE idAluno = ? AND idTreino = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1,idAluno);
            statement.setInt(2,idTreino);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return preencherTreino(rs);
            }
            else throw new WrongArgumentException("Esse treino não existe no sistema");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Treino> buscarTodos(int idAluno){
        ArrayList<Treino> listTr = new ArrayList<>();
        String sql = "SELECT * from Treino WHERE idAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1,idAluno);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                listTr.add(preencherTreino(rs));
            }
            return listTr;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void alterarNome(Treino tr){
        String sql = "UPDATE Treino SET nome = ? WHERE idTreino = ? and idAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tr.getNome());
            statement.setInt(2, tr.getIdTreino());
            statement.setInt(3, tr.getIdAluno());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void excluirTreino(Treino tr){
        String sql = "DELETE FROM Treino WHERE idTreino = ? AND idAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, tr.getIdTreino());
            statement.setInt(2, tr.getIdAluno());
            statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
