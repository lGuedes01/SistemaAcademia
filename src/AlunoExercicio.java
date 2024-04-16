import java.sql.Date;

public class AlunoExercicio{
    private int idAluno;
    private int idExercicio;
    private Date dataExec;
    private int carga;

    public AlunoExercicio(int idAluno, int idExercicio, String sDataExec){
        this.idAluno = idAluno;
        this.idExercicio = idExercicio;
        this.dataExec = Date.valueOf(sDataExec);
    }
}
