import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alunos {
    protected Date dataNascimento;
    protected String CPF;
    protected String nome;

    public Alunos(String nome, String CPF, String data) throws ParseException {
        this.CPF = CPF;
        this.nome = nome;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        this.dataNascimento = formato.parse(data);

    }

    public Date getDataNascimento() {
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
