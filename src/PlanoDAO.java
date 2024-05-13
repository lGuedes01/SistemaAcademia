import br.com.ConexaoBanco.ConexaoMySQL;
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
        Connection con = ConexaoMySQL.abrir();
        String sql = "INSERT INTO Plano (idPlano, nome, preco) VALUES(?,?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1, pl.getId());
            statement.setString(2, pl.getNome());
            statement.setFloat(3, pl.getPreco());
            statement.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void alterarPrecoPlano(Plano pl) {
        Connection con = ConexaoMySQL.abrir();
        String sql = "UPDATE Plano SET preco = ? where idPlano = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setFloat(1, pl.getPreco());
            statement.setInt(2, pl.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Plano buscarPlanoId(int id){
        Connection con = ConexaoMySQL.abrir();
        String sql = "SELECT * FROM Plano WHERE idPlano = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)){
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
        Connection con = ConexaoMySQL.abrir();
        ArrayList<Plano> listPl = new ArrayList<Plano>();
        String sql = "SELECT * FROM Plano";
        try (PreparedStatement statement = con.prepareStatement(sql)){
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
