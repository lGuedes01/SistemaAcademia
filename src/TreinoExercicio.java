import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;

public class TreinoExercicio{
    private int idTreino;
    private int idAluno;
    private int idExercicio;

    public TreinoExercicio(int idTreino, int idAluno, int idExercicio) {
        this.idTreino = idTreino;
        this.idAluno = idAluno;
        this.idExercicio = idExercicio;
    }

    public void inserirTreinoExercicio(java.sql.Connection con, int nSeries, int minRep, int maxRep, float carga, float descanso){
        String sql = "INSERT INTO TreinoExercicio (idTreino, idAluno, idExercicio, nSeries, minRep, maxRep, carga, descanso)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1,idTreino);
            statement.setInt(2,idAluno);
            statement.setInt(3,idExercicio);
            statement.setInt(4,nSeries);
            statement.setInt(5,minRep);
            statement.setInt(6,maxRep);
            statement.setFloat(7,carga);
            statement.setFloat(8,descanso);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void updateTreinoExercicio(java.sql.Connection con, String campo, int novoValor){
        String sql = "UPDATE treinoExercicio SET " + campo + " = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, novoValor);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarExercicioNoTreino(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o numero de series:");
        int nSeries = input.nextInt();
        System.out.println("Digite o numero minimo de repetições: ");
        int minRep = input.nextInt();
        System.out.println("Digite o numero maximo de repetições: ");
        int maxRep = input.nextInt();
        System.out.println("Digite a carga do exercício:");
        float carga = input.nextFloat();
        input.nextLine();
        System.out.println("Digite o tempo de descanso:");
        float descanso = input.nextFloat();
        this.inserirTreinoExercicio(Main.con,nSeries,minRep,maxRep,carga,descanso);
    }

}
