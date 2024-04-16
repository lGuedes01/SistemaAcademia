public class TreinoExercicio{
    private int idTreino;
    private int idAluno;
    private int idExercicio;
    private int nSeries;
    private int minRep;
    private int maxRep;
    private int carga;
    private int descanso;

    public TreinoExercicio(int idTreino, int idAluno, int idExercicio){
        this.idTreino = idTreino;
        this.idAluno = idAluno;
        this.idExercicio = idExercicio;
    }

    public int getIdTreino(){
        return idTreino;
    }

    public int getIdAluno(){
        return idAluno;
    }
    
    public int getidExercicio(){
        return idExercicio;
    }
}
