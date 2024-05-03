import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AlunoExercicio{
    private int idAluno;
    private int idExercicio;
    private Date dataExec;
    private float carga;

    public AlunoExercicio(int idAluno, int idExercicio, String sDataExec){
        this.idAluno = idAluno;
        this.idExercicio = idExercicio;
        this.dataExec = Date.valueOf(sDataExec);
    }

    public void setCarga(float novaCarga){
        carga = novaCarga;
    }

    public int getIdAluno(){
        return idAluno;
    }

    public int getIdExercicio(){
        return idExercicio;
    }

    public Date getDataExercicio(){
        return dataExec;
    }

    public float getCarga(){
        return carga;
    }

    public void inserirAlunoExercicio(java.sql.Connection con){
        String sql = "INSERT INTO AlunoExercicio(idAluno, idExercicio, dataExec, carga)" +
                " VALUES(?,?,?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1,idAluno);
            statement.setInt(2,idExercicio);
            statement.setDate(3,dataExec);
            statement.setFloat(4,carga);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }




}
