import java.sql.*;
import java.util.Scanner;
import br.com.ConexaoBanco.ConexaoMySQL;

public class Aluno {
    private int idAluno;
    private String cpf;
    private String nome;
    private Date dataNasc;
    private int idPlano;
    private Date dataPlano;
    private String nCartao;
    private Date dataVenc;
    private int cvc;

    public Aluno(String cpf, String nome, String sDataNasc){
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = Date.valueOf(sDataNasc);
    }

    public String getCPF(){
        return cpf;
    }

    public Date getDataNasc(){
        return dataNasc;
    }

    public String getNome(){
        return nome;
    }

    public void setPlano(int idPl, String sDPlano, String nCart, String sDVenc, int cvcNovo){
        idPlano = idPl;
        dataPlano = Date.valueOf(sDPlano);
        nCartao = nCart;
        dataVenc = Date.valueOf(sDVenc);
    }

    public void buscarAlunoCPF(java.sql.Connection con){
        String sql = "SELECT * FROM Aluno where cpf = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, cpf);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                idAluno = rs.getInt(1);
                cpf = rs.getString(2);
                nome = rs.getString(3);
                dataNasc = rs.getDate(4);
                idPlano = rs.getInt(5);
                dataPlano = rs.getDate(6);
                nCartao = rs.getString(7);
                dataVenc = rs.getDate(8);
                cvc = rs.getInt(9);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserirAluno(java.sql.Connection con) {
        String sql = "INSERT INTO Aluno (cpf, nome, dataNasc) VALUES (?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cpf);
            statement.setString(2, nome);
            statement.setDate(3,  dataNasc);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                idAluno = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePlanoAluno(java.sql.Connection con) {
        String sql = "UPDATE Aluno SET idPlano = ?, dataPlano = ?, nCartao = ?, dataVenc = ?, cvc = ? where idAluno = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idPlano);
            statement.setDate(2, dataPlano);
            statement.setString(3,  nCartao);
            statement.setDate(4,  dataVenc);
            statement.setInt(5, cvc);
            statement.setInt(6, idAluno);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
