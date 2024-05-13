import br.com.ConexaoBanco.ConexaoMySQL;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static String pedirCPF(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insira um CPF: ");
        return input.nextLine();
    }
    public static int pedirID(String nomeTabela){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o id da tabela " + nomeTabela + ": ");
        return input.nextInt();
    }

    public static Date pedirData(boolean ini){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite a data " + ((ini) ? "de início" : "final") + ":");
        return Date.valueOf(input.nextLine());
    }

    public static void main(String[] args) throws ParseException {
            int op = -1;
        while (op != 0){
            op = Menu.menu_principal();
            int op2;
            switch (op){
                case 1:
                    op2 = Menu.menu_alunos();
                    Aluno aluno;
                    String cpf;
                    switch (op2){
                        case 1:
                            //Cadastrar aluno
                            Aluno.cadastrarAluno();
                            break;
                        case 2:
                            //Buscar aluno
                            cpf = pedirCPF();
                            aluno = Aluno.buscarAlunoCPF(con,cpf);
                            int op3 = Menu.menu_aluno();
                            switch (op3){
                                case 1:
                                    aluno.mostrarDados();
                                    break;
                                case 2:
                                    aluno.alterarDados();
                                    break;
                                case 3:
                                    aluno.excluirDados(con);
                                    break;
                                case 4:
                                    Treino treino = Treino.cadastrarTreino(aluno.getIdAluno());
                                    treino.acrescentarTreino();
                                    break;
                                case 5:
                                    aluno.mostrarTreinos(con);
                                    break;
                                case 6:
                                    int idt = pedirID("treino");
                                    treino = Treino.selectTreino(con, aluno.getIdAluno(), idt);
                                    treino.mostrarExercicios(con);
                                    break;
                                case 7:
                                    aluno.registrarExecucaoDoExercicio();
                                    break;
                            }

                            break;
                    }
                    break;
                case 2:
                    op2 = Menu.menu_treinos();
                    switch (op2){
                        case 1:
                            Exercicio.cadastrarExercicio();
                            break;
                        case 2:
                            Exercicio.listarExercicios(Main.con);
                            Exercicio exercicio = Exercicio.escolherExercicio();
                            exercicio.alterarExercicio();
                            break;
                    }
                    break;
                case 3:
                    op2 = Menu.menu_planos();
                    switch (op2){
                        case 1:
                            Plano.criarPlano();
                            break;
                        case 2:
                            Plano.alterarPlano();
                            break;
                    }
                    break;
                case 4:
                    op2 = Menu.menu_relatorios();
                    String ncpf = pedirCPF();
                    Aluno al = Aluno.buscarAlunoCPF(con, ncpf);
                    switch (op2){
                        case 1:
                            al.relDatas(con);
                            break;
                        case 2:
                            int idEx = pedirID("exercício");
                            Date dataIni = pedirData(true);
                            Date dataFim = pedirData(false);
                            al.relEvolucao(con, idEx, dataIni, dataFim);
                    }
                    break;
            }
        }
        }
}
