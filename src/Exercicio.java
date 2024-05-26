public class Exercicio {
    private int idExercicio;
    private String nome;
    private String musculos;

    public Exercicio(int idExercicio, String nome, String musculos) {
        this.idExercicio = idExercicio;
        this.nome = nome;
        this.musculos = musculos;
    }

    public int getId() {
        return idExercicio;
    }

    public String getNome() {
        return nome;
    }

    public String getMusculos() {
        return musculos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMusculos(String musculos) {
        this.musculos = musculos;
    }

}