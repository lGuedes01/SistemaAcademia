import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plano {
    private int idPlano;
    private String nome;
    private float preco;

    public Plano(String nome, int idPlano, float preco){
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


}
