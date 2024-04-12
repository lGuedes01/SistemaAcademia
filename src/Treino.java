class Treino{
    private int idTreino;
    private int idAluno;
    private String nome;

    public Treino(int idTreino, int idAluno, String nome){
        this.idTreino = idTreino;
        this.idAluno = idAluno;
        this.nome = nome;
    }

    public getIdTreino(){
        return idTreino;
    }

    public getIdAluno(){
        return idAluno;
    }
    
    public getNome(){
        return nome;
    }
}

class TreinoExercicio{
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

    public getIdTreino(){
        return idTreino;
    }

    public getIdAluno(){
        return idAluno;
    }
    
    public getidExercicio(){
        return idExercicio;
    }

}
