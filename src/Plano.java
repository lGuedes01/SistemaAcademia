import com.mysql.cj.exceptions.WrongArgumentException;

import java.util.Scanner;
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

    public void setPreco(float preco) {
        this.preco = preco;
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

    public static Plano criarInstanciaPlano(){
        Scanner input = new Scanner(System.in);
        System.out.println("Criando um novo plano para a academia");
        System.out.println("Digite um nome para o plano:");
        String nome = input.nextLine();
        System.out.println("Digite o id do plano:");
        int id = input.nextInt();
        System.out.println("Digite o valor do plano: ");
        float preco = input.nextFloat();
        return new Plano(nome,id,preco);
    }

    public static void criarPlano(){
        Plano plano = criarInstanciaPlano();
        plano.inserirPlano(Main.con);
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

    public static Plano buscarPlanoId(Connection con, int Id){
        String sql = "SELECT * FROM Plano WHERE idPlano = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1,Id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                int idPlano = rs.getInt(1);
                String nome = rs.getString(2);
                float preco = rs.getFloat(3);
                return new Plano(nome,idPlano,preco);
            }
            else{
                throw new WrongArgumentException("Esse plano não está cadastrado");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int perguntarId(){
        Scanner input = new Scanner(System.in);
        System.out.printf("Id do plano que deseja alterar: ");
        return input.nextInt();
    }
    public static void alterarPlano() {
        Scanner input = new Scanner(System.in);
        int id = perguntarId();
        Plano plano = buscarPlanoId(Main.con, id);
        System.out.println("Plano " + plano.nome + " R$" + plano.preco);
        System.out.println("Digite um novo valor: ");
        plano.setPreco(input.nextFloat());
        plano.updatePrecoPlano(Main.con);
    }

}
