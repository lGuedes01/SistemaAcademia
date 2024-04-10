import java.sql.Date;
import java.util.Scanner;

public class Alunos {
    protected Date dataNascimento;
    protected String CPF;
    protected String nome;

    private static final Scanner input = new Scanner(System.in);

    public Alunos(String nome, String CPF, String patternDate){
        this.CPF = CPF;
        this.nome = nome;
        this.dataNascimento = Date.valueOf(patternDate);

    }

    public String getCPF() {
        return CPF;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void buscar_aluno(String CPF){

    }

}
