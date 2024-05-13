import java.sql.*;
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

    public int getIdAluno() {
        return idAluno;
    }

    public String getCPF() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public Date getDataPlano() {
        return dataPlano;
    }

    public String getnCartao() {
        return nCartao;
    }

    public Date getDataVenc() {
        return dataVenc;
    }

    public int getCVC() {
        return cvc;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public Aluno(String cpf, String nome, Date dataNasc) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
    }

    public void setPlanoAluno(int idPlano, Date dataPlano, String nCartao, Date dataVenc, int cvc) {
        this.idPlano = idPlano;
        this.dataPlano = dataPlano;
        this.nCartao = nCartao;
        this.dataVenc = dataVenc;
        this.cvc = cvc;
    }

}