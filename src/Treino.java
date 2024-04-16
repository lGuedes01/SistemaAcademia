public class Treino{
    private int idTreino;
    private int idAluno;
    private String nome;

    public Treino(int idTreino, int idAluno, String nome){
        this.idTreino = idTreino;
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
}
