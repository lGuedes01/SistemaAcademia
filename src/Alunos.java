import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alunos {
    protected String dataNascimento;
    protected String CPF;
    protected String nome;

    public Alunos(String nome, String CPF, String patternDate) throws ParseException {
        this.CPF = CPF;
        this.nome = nome;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formato.parse(patternDate);
        this.dataNascimento = formato.format(date);

    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public void buscar_aluno(String CPF){

    }

}
