import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Scanner;

public class Treino {
    private int idTreino;
    private int idAluno;
    private String nome;

    public Treino(int idAluno, String nome) {
        this.idAluno = idAluno;
        this.nome = nome;
    }

    public int getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(int idTr) {
        this.idTreino = idTr;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public String getNome() {
        return nome;
    }

}