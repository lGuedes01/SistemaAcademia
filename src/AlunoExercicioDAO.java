import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class AlunoExercicioDAO {
    private AlunoExercicio preencherAlunoExercicio(ResultSet rs) throws SQLException{
        AlunoExercicio aluex = new AlunoExercicio(rs.getInt(1), rs.getInt(2), rs.getDate(3));
        aluex.setCarga(rs.getFloat(4));
        return aluex;
    }

    public void inserirAlunoExercicio(AlunoExercicio aluex){
        String sql = "INSERT INTO AlunoExercicio(idAluno, idExercicio, dataExec, carga)" +
                " VALUES(?,?,?,?)";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, aluex.getIdAluno());
            statement.setInt(2, aluex.getIdExercicio());
            statement.setDate(3, aluex.getDataExercicio());
            statement.setFloat(4, aluex.getCarga());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Date> buscarDatas(int idAluno){
        ArrayList<Date> listDates = new ArrayList<>();
        String sql = "SELECT dataExec from AlunoExercicio where IdAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idAluno);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                listDates.add(rs.getDate(1));
            }
            return listDates;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<AlunoExercicio> buscarAluEx(int idAluno, int idExercicio, Date dataIni, Date dataFim){
        ArrayList<AlunoExercicio> listAluEx = new ArrayList<>();
        String sql = "SELECT * FROM AlunoExercicio WHERE idAluno = ? and idExercicio = ? and dataExec BETWEEN ? and ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1,idAluno);
            statement.setInt(2, idExercicio);
            statement.setDate(3, dataIni);
            statement.setDate(4, dataFim);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                listAluEx.add(preencherAlunoExercicio(rs));
            }
            return listAluEx;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
