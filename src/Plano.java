import com.mysql.cj.exceptions.WrongArgumentException;

import java.util.Scanner;
import java.sql.*;

public class Plano {
    private int idPlano;
    private String nome;
    private float preco;

    public Plano(int idPlano, String nome, float preco) {
        this.nome = nome;
        this.idPlano = idPlano;
        this.preco = preco;
    }

    public int getId() {
        return idPlano;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}