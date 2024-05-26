import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlanoDAO{
    public Plano preencherPlano(ResultSet rs) throws SQLException{
        return new Plano(rs.getInt(1), rs.getString(2), rs.getFloat(3));
    }
    public void inserirPlano(Plano pl){
        String sql = "INSERT INTO Plano (idPlano, nome, preco) VALUES(?,?,?)";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, pl.getId());
            statement.setString(2, pl.getNome());
            statement.setFloat(3, pl.getPreco());
            statement.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void alterarPrecoPlano(Plano pl) {
        String sql = "UPDATE Plano SET preco = ? where idPlano = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setFloat(1, pl.getPreco());
            statement.setInt(2, pl.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Plano buscarPlanoId(int id){
        String sql = "SELECT * FROM Plano WHERE idPlano = ?";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return preencherPlano(rs);
            }
            else{
                throw new WrongArgumentException("Esse plano não está cadastrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Plano> buscarTodos(){
        ArrayList<Plano> listPl = new ArrayList<>();
        String sql = "SELECT * FROM Plano";
        try(Connection con = ConexaoMySQL.abrir(); PreparedStatement statement = con.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                listPl.add(preencherPlano(rs));
            }
            return listPl;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
