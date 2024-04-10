import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Planos {
    private int id;
    private String nome;
    private float preco;




    public Planos(String nome, int id, float preco){
        this.nome = nome;
        this.id = id;
        this.preco = preco;

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }


}
