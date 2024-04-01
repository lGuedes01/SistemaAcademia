import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercicios {
    private int num;
    private String nome;
    private List<String> musculosAtivos = new ArrayList<>();

    public Exercicios(int num, String nome, String musculos){
        this.num = num;
        this.nome = nome;
        this.musculosAtivos.addAll(Arrays.asList(musculos.split(",")));
        System.out.println(this.musculosAtivos);
    }
}
