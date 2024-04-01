import java.text.ParseException;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws ParseException {

        Sistema academia = new Sistema("Minha Academia");
        boolean run = true;
        while (run){
            int op = Menu.menu_principal();
            if (op == 0){run = false;}
        }
    }

    public Planos criar_plano(Sistema academia){
        String nomeAcademia = academia.getNomeAcademia();
        System.out.printf("Criando um novo plano para a academia '%s' \n", nomeAcademia);
        System.out.println("Digite um nome para o plano:");
        String nome = input.nextLine();
        System.out.println("Digite o código do plano:");
        int codigo = input.nextInt();
        System.out.println("Digite o valor do plano: ");
        float preco = input.nextFloat();
        return new Planos(nome,codigo,preco);
    }
}