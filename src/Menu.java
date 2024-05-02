import java.util.Scanner;

public class Menu {

    private static Scanner input = new Scanner(System.in);
    public static int menu_principal(){
        System.out.println("Escolha uma opção:");
        System.out.println("1- Aluno");
        System.out.println("2- Treino");
        System.out.println("3- Planos");
        System.out.println("0- Sair");
        System.out.printf("->");
        return input.nextInt();
    }

    public static int menu_planos(){
        System.out.println("1- Criar Plano");
        System.out.println("2- Alterar Plano");
        System.out.println("0- Voltar");
        System.out.printf("->");
        return input.nextInt();
    }

    public static int menu_alunos(){
        System.out.println("1- Cadastrar aluno");
        System.out.println("2- Buscar aluno");
        System.out.println("0- Voltar");
        System.out.printf("->");
        return input.nextInt();
    }

    public static int menu_aluno(){
        System.out.println("1- Mostrar dados do aluno");
        System.out.println("2- Alterar dados aluno");
        System.out.println("3- Excluir aluno");
        System.out.println("4- Criar treino");
        System.out.println("5- Mostrar treinos do aluno");
        System.out.println("6- Listar exercicios do treino");
        System.out.println("7- Anotar execucao de exercicio");
        System.out.println("0- Voltar");
        System.out.printf("->");
        return input.nextInt();
    }

    public static int menu_treinos(){
        System.out.println("1- Criar execício");
        System.out.println("2- Alterar exercício");
        System.out.println("0- Voltar");
        System.out.printf("->");
        return input.nextInt();
    }
}

