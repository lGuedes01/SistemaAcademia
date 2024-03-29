import java.util.ArrayList;
import java.util.List;

public class Planos {
    protected int codigo;
    protected String nome;
    protected float preco;

    protected List<Alunos> alunosCadastradosPlano = new ArrayList<>();

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
