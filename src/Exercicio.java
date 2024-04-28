import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import br.com.ConexaoBanco.ConexaoMySQL;
import com.mysql.cj.exceptions.WrongArgumentException;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.Scanner;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMusculos(String musculos) {
        this.musculos = musculos;
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

    public static Exercicio selectExercicio(java.sql.Connection con, int idExercicio){
        String sql = "SELECT * from Exercicio WHERE idExercicio = ?";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1, idExercicio);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new Exercicio(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            else {
                throw new WrongArgumentException("Esse exercício não existe no sistema");
            }

        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public static void listarExercicios(java.sql.Connection con){
        String sql = "SELECT * FROM exercicio";
        try(Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()){
                int id = rs.getInt(1);
                String nome = rs.getString(2);
                String musculos = rs.getString(3);
                System.out.println("Id: " + id + "; Nome: " + nome + "; Músculos: " + musculos);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void updateExercicio(java.sql.Connection con){
        String sql = "UPDATE exercicio SET nome = ?, musculos = ? WHERE idExercicio = ?";
        try(PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1,nome);
            statement.setString(2,musculos);
            statement.setInt(3,idExercicio);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void excluirExercicio(java.sql.Connection con){
        String sql = "DELETE FROM exercicio WHERE idExercicio = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idExercicio);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cadastrarExercicio(){
        Exercicio exercicio = criarInstanciaExercicio();
        exercicio.inserirExercicio(Main.con);
    }
    public static Exercicio criarInstanciaExercicio(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do exercicio:");
        String nomeEx = input.nextLine();
        System.out.println("Digite os músculos que o exercício aciona: ");
            String musc = input.nextLine();
        System.out.println("Digite o id do exercicio: ");
        int id = input.nextInt();

        return new Exercicio(id,nomeEx,musc);
    }

    public static Exercicio escolherExercicio(){
        Scanner input = new Scanner(System.in);
        System.out.println("Escolha um exercício pelo id para alterar:");
        int id = input.nextInt(); input.nextLine();
        return selectExercicio(Main.con, id);
    }
    public void alterarExercicio(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite um novo nome:");
        this.setNome(input.nextLine());
        System.out.println("Digite uma nova lista de músculos:");
        this.setMusculos(input.nextLine());
        this.updateExercicio(Main.con);
    }



}
