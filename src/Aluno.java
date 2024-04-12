import java.sql.Date;
import java.util.Scanner;

public class Aluno {
    private int idAluno;
    private String cpf;
    private String nome;
    private Date dataNasc;
    private int idPlano;
    private Date dataPlano;
    private String nCartao;
    private Date dataVenc;
    private int cvc;

    private static final Scanner input = new Scanner(System.in);

    public Alunos(String cpf, String nome, String sDataNasc){
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = Date.valueOf(sDataNasc);
    }

    public String getCPF(){
        return cpf;
    }

    public Date getDataNasc(){
        return dataNasc;
    }

    public String getNome(){
        return nome;
    }

    public Boolean setPlano(int idPlano, String sDataPlano, String nCartao, String sDataVenc, int cvc){
        
    }

    public void buscar_aluno(String cpf){

    }
}

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
