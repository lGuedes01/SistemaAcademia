import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AlunoExercicio {
    private int idAluno;
    private int idExercicio;
    private Date dataExec;
    private float carga;

    public AlunoExercicio(int idAluno, int idExercicio, Date dataExec) {
        this.idAluno = idAluno;
        this.idExercicio = idExercicio;
        this.dataExec = dataExec;
    }

    public void setCarga(float novaCarga) {
        carga = novaCarga;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getIdExercicio() {
        return idExercicio;
    }

    public Date getDataExercicio() {
        return dataExec;
    }

    public float getCarga() {
        return carga;
    }

}