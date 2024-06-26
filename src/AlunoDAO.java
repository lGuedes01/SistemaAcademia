import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO {

    private Aluno preencherAluno(ResultSet rs) throws SQLException{
        int idAluno = rs.getInt(1);
        String cpfAluno = rs.getString(2);
        String nome = rs.getString(3);
        Date dataNasc = rs.getDate(4);
        int idPlano = rs.getInt(5);
        Date dataPlano = rs.getDate(6);
        String nCartao = rs.getString(7);
        Date dataVenc = rs.getDate(8);
        int cvc = rs.getInt(9);
        Aluno aluno = new Aluno(cpfAluno, nome, dataNasc);
        aluno.setPlanoAluno(idPlano, dataPlano, nCartao, dataVenc, cvc);
        aluno.setIdAluno(idAluno);
        return aluno;
    }
    public Aluno inserirAluno(Aluno al) {
        String sql = "INSERT INTO Aluno (cpf, nome, dataNasc, idPlano, dataPlano, nCartao, dataVenc, cvc)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, al.getCPF());
            statement.setString(2, al.getNome());
            statement.setDate(3,  al.getDataNasc());
            statement.setInt(4, al.getIdPlano());
            statement.setDate(5, al.getDataPlano());
            statement.setString(6, al.getnCartao());
            statement.setDate(7, al.getDataVenc());
            statement.setInt(8, al.getCVC());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                al.setIdAluno(rs.getInt(1));
            }
            return al;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno buscarAlunoID(int id){
        String sql = "SELECT * FROM Aluno where idAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return preencherAluno(rs);
            }
            else{
                throw new WrongArgumentException("Esse ID não existe");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Aluno buscarAlunoCPF(String cpf){
        String sql = "SELECT * FROM Aluno where cpf = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, cpf);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return preencherAluno(rs);
            }
            else{
                throw new WrongArgumentException("Esse CPF não está cadastrado no sistema");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterarPlano(Aluno aluno){
        String sql = "UPDATE Aluno SET idPlano = ?, dataPlano = ?, nCartao = ?, dataVenc = ?, cvc = ? WHERE idAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, aluno.getIdPlano());
            statement.setDate(2, aluno.getDataPlano());
            statement.setString(3, aluno.getnCartao());
            statement.setDate(4, aluno.getDataVenc());
            statement.setInt(5, aluno.getCVC());
            statement.setInt(6, aluno.getIdAluno());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirAluno(Aluno aluno) {
        String sql = "DELETE FROM Aluno WHERE idAluno = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, aluno.getIdAluno());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Aluno> buscarTodos(){
        ArrayList<Aluno> listAl = new ArrayList<>();
        String sql = "SELECT * FROM Aluno";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                listAl.add(preencherAluno(rs));
            }
            return listAl;
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
    }
}
