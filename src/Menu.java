import java.util.Scanner;

public class Menu {

    private static Scanner input = new Scanner(System.in);
    public static int menu_principal(){
        System.out.println("Escolha uma opção:");
        System.out.println("1- Aluno:");
        System.out.println("2- Treino:");
        System.out.println("3- Sistema:");
        System.out.printf("->");
        return input.nextInt();

    }
}

