import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Treino{
    private int idTreino;
    private int idAluno;
    private String nome;

    public Treino(int idAluno, String nome) {
        this.idAluno = idAluno;
        this.nome = nome;
    }

    public int getIdTreino(){
        return idTreino;
    }

    public int getIdAluno(){
        return idAluno;
    }

    public String getNome(){
        return nome;
    }

    public void inserirTreino(java.sql.Connection con){
        String sql = "INSERT INTO treino  (idAluno, nome) VALUES(?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1,idAluno);
            statement.setString(2,nome);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                idTreino = rs.getInt(1);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Treino selectTreino(java.sql.Connection con, int idAluno, int idTreino){
        String sql = "SELECT * from treino WHERE idAluno = ? AND idTreino = ?";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1,idAluno);
            statement.setInt(2,idTreino);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new Treino(rs.getInt(2),rs.getString(3));
            }
            else throw new WrongArgumentException("Esse treino não existe no sistema");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Treino cadastrarTreino(int idAluno){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome do treino:");
        String nome = input.nextLine();
        Treino treino = new Treino(idAluno,nome);
        treino.inserirTreino(Main.con);
        return treino;
    }

    public void acrescentarTreino(){
        Scanner input = new Scanner(System.in);
        boolean alterar = true;
        do {
            System.out.println("Diga o id de um exercício para colocar no treino:");
            int idEx = input.nextInt(); input.nextLine();
            Exercicio ex = Exercicio.selectExercicio(Main.con, idEx);
            TreinoExercicio treinoExercicio = new TreinoExercicio(this.getIdTreino(), this.getIdAluno(), ex.getId());
            treinoExercicio.cadastrarExercicioNoTreino();
            System.out.println("Continuar acrescentando exercícios [s|n]:");
            String  res = input.nextLine();
            alterar = res.equals("s");
        }while (alterar);

    }
}
