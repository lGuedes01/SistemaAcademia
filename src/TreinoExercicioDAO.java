import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TreinoExercicioDAO {
    private TreinoExercicio preencherTreinoExercicio(ResultSet rs) throws SQLException{
        TreinoExercicio trex = new TreinoExercicio(rs.getInt(1), rs.getInt(2), rs.getInt(3));
        trex.setnSeries(rs.getInt(4));
        trex.setMinRep(rs.getInt(5));
        trex.setCarga(rs.getFloat(6));
        trex.setDescanso(rs.getFloat(7));
        return trex;
    }

    public void inserirTreinoExercicio(TreinoExercicio trex){
        String sql = "INSERT INTO TreinoExercicio (idTreino, idAluno, idExercicio, nSeries, minRep, maxRep, carga, descanso)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, trex.getIdTreino());
            statement.setInt(2, trex.getIdAluno());
            statement.setInt(3, trex.getIdExercicio());
            statement.setInt(4, trex.getnSeries());
            statement.setInt(5, trex.getMinRep());
            statement.setInt(6, trex.getMaxRep());
            statement.setFloat(7, trex.getCarga());
            statement.setFloat(8, trex.getDescanso());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void alterarCarga(TreinoExercicio trex){
        String sql = "UPDATE TreinoExercicio SET carga = ? WHERE idTreino = ? AND idAluno = ? AND idExercicio = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setFloat(1, trex.getCarga());
            statement.setInt(2, trex.getIdTreino());
            statement.setInt(3, trex.getIdAluno());
            statement.setInt(4, trex.getIdExercicio());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TreinoExercicio buscarTreinoExercicio(int idTreino, int idAluno, int idExercicio){
        String sql = "SELECT * FROM TreinoExercicio WHERE idTreino = ? AND idAluno = ? AND idExercicio = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idTreino);
            statement.setInt(2, idAluno);
            statement.setInt(3, idExercicio);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return preencherTreinoExercicio(rs);
            } else {
                throw new WrongArgumentException("Não existe esse registro");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<TreinoExercicio> buscarTodos(int idTreino, int idAluno){
        ArrayList<TreinoExercicio> listTrEx = new ArrayList<>();
        String sql = "SELECT * FROM TreinoExercicio WHERE idTreino = ? AND idAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idTreino);
            statement.setInt(2, idAluno);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                listTrEx.add(preencherTreinoExercicio(rs));
            }
            return listTrEx;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
