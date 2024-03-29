import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Sistema {
    protected List<Alunos> alunosCadastrados = new ArrayList<>();
    protected List<Planos> listaPlanos = new ArrayList<>();
    protected String nomeAcademia;

    public Sistema(String nome){
        this.nomeAcademia = nome;
    }

    public void add_plano(Planos novoPlano){
        this.listaPlanos.add(novoPlano);
    }

    public void cadastrar(String nome,String CPF, String dataNascimento, Planos plano) throws ParseException {
        Alunos aluno = new Alunos(nome,CPF,dataNascimento);
        this.alunosCadastrados.add(aluno);
        plano.setAlunosCadastradosPlano(aluno);

    }

    public List<Alunos> getAlunosCadastrados() {
        return alunosCadastrados;
    }

    public List<Planos> getListaPlanos() {
        return listaPlanos;
    }
}
