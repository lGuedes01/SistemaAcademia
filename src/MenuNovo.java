import java.util.Scanner;

public class MenuNovo {

    private String input(String str = ""){
        System.out.print(str);
        Scanner inp = new Scanner(System.in);
        return inp.nextLine();
    }

    public static void menuPrincipal(){
        int op;
        do {
            System.out.println("Bem-vindo! O que você deseja?");
            System.out.println("1: Entrar como instrutor");
            System.out.println("2: Entrar como aluno");
            System.out.println("3: Gerar relatorios");
            System.out.println("0: Sair");
            op = Integer.parseInt(input(">"));
            switch (op) {
                case 1:
                    menuInstrutor();
                    break;
                case 2:
                    menuAluno();
                    break;
                case 3:
                    menuRelatorios();
                    break;
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuInstrutor(){
        int op;
        do {
            System.out.println("1: Alunos");
            System.out.println("2: Planos");
            System.out.println("3: Exercícios");
            System.out.println("0: Voltar");
            op = Integer.parseInt(input(">"));
            switch(op){
                case 1:
                    menuInstrutorAlunos();
                    break;
                case 2:
                    menuInstrutorPlanos();
                    break;
                case 3:
                    menuInstrutorExercicios();
                    break;
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuInstrutorAlunos(){
        int op;
        do {
            System.out.println("1: Cadastrar aluno");
            System.out.println("2: Buscar aluno");
            System.out.println("3: Alterar aluno");
            System.out.println("0: Voltar");
            op = Integer.parseInt(input(">"));
            switch(op){
                case 1:
                    menuInstrutorAlunosCadastrar();
                    break;
                case 2:
                    menuInstrutorAlunosBuscar();
                    break;
                case 3:
                    menuInstrutorAlunosAlterar();
                    break;
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuInstrutorAlunosCadastrar(){
        //todo: implementar
    }

    public static void menuInstrutorAlunosBuscar(){
        //todo: implementar
    }

    public static void menuInstrutorAlunosAlterar(){
        //todo: implementar
        //planos
        //treinos
    }

    public static void menuInstrutorPlanos(){
        int op;
        do {
            System.out.println("1: Cadastrar plano");
            System.out.println("2: Listar planos");
            System.out.println("3: Alterar plano");
            System.out.println("0: Voltar");
            op = Integer.parseInt(input(">"));
            switch(op){
                case 1:
                    menuInstrutorPlanosCadastrar();
                    break;
                case 2:
                    menuInstrutorPlanosListar();
                    break;
                case 3:
                    menuInstrutorPlanosAlterar();
                    break;
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuInstrutorPlanosCadastrar(){
        //todo: implementar
    }

    public static void menuInstrutorPlanosListar(){
        //todo: implementar
    }

    public static void menuInstrutorPlanosAlterar(){
        //todo: implementar
    }

    public static void menuInstrutorExercicios(){
        int op;
        do {
            System.out.println("1: Cadastrar exercício");
            System.out.println("2: Listar exercícios");
            System.out.println("3: Alterar exercício");
            System.out.println("0: Voltar");
            op = Integer.parseInt(input(">"));
            switch (op) {
                case 1:
                    menuInstrutorExerciciosCadastrar();
                    break;
                case 2:
                    menuInstrutorExerciciosListar();
                    break;
                case 3:
                    menuInstrutorExerciciosAlterar();
                    break;
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuInstrutorExerciciosCadastrar(){
        //todo: implementar
    }

    public static void menuInstrutorExerciciosListar(){
        //todo: implementar
    }

    public static void menuInstrutorExerciciosAlterar(){
        //todo: alterar
    }

    public static void menuAluno(){
        int op;
        do {
            System.out.println("1: Listar treinos");
            System.out.println("2: Listar exercícios");
            System.out.println("3: Anotar execução ");
            System.out.println("0: Voltar");
            op = Integer.parseInt(input(">"));
            switch (op) {
                case 1:
                    menuAlunoTreinos();
                    break;
                case 2:
                    menuAlunoExercicios();
                    break;
                case 3:
                    menuAlunoExecucao();
                    break;
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuAlunoTreinos(){
        //todo: implementar
    }

    public static void menuAlunoExercicios(){
        //todo: implementar
    }

    public static void menuAlunoExecucao(){
        //todo: implementar
    }

    public static void menuRelatorios(){
        int op;
        do {
            System.out.println("1: Mostrar datas que o aluno compareceu à academia");
            System.out.println("2: Mostrar evolução do aluno em um exercício");
            System.out.println("0: Voltar");
            op = Integer.parseInt(input(">"));
            switch (op) {
                case 1:
                    menuRelatorios1();
                    break;
                case 2:
                    menuRelatorios2();
                    break;
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuRelatorios1(){
        //todo: implementar
    }

    public static void menuRelatorios2(){
        //todo: implementar
    }

}
