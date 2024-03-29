import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Sistema academia = new Sistema("Minha Academia");
        Planos p1 = new Planos("Normal", 1, 49.90f);
        academia.add_plano(p1);
        academia.cadastrar("Lucas","1","01/04/2005", p1);
        academia.cadastrar("Lucas2", "2", "02/04/2005",p1);

        for(Alunos a : academia.getAlunosCadastrados()){
            System.out.println(a.getNome());
            System.out.println(a.getCPF());
        }
    }
}