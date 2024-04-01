import java.util.ArrayList;
import java.util.List;

public class Planos {
    private int codigo;
    private String nome;
    private float preco;
    private List<Alunos> alunosCadastradosPlano = new ArrayList<>();

    public Planos(String nome, int codigo, float preco){
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;

    }

    public void setAlunosCadastradosPlano(Alunos aluno) {
        this.alunosCadastradosPlano.add(aluno);
    }

    public void incluir(Alunos aluno, Planos plano){

    }
}
