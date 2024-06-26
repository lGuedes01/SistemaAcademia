import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuNovo {

    private static String input(String str) {
        System.out.print(str);
        Scanner inp = new Scanner(System.in);
        return inp.nextLine();
    }

    private static int int_input(String str){
        while(true){
            System.out.print(str);
            Scanner inp = new Scanner(System.in);
            try{
                return inp.nextInt();
            } catch (Exception e) {
                System.out.println("Argumento não aceito.");
            }
        }
    }

    private static float float_input(String str){
        while(true){
            System.out.print(str);
            Scanner inp = new Scanner(System.in);
            try{
                return inp.nextFloat();
            } catch (Exception e) {
                System.out.println("Argumento não aceito.");
            }
        }
    }

    private static void listarAluno(Aluno al, boolean pl){
        System.out.printf("%04d: %s%n", al.getIdAluno(), al.getNome());
        System.out.println("CPF: " + al.getCPF());
        System.out.println("Data Nasc.: " + al.getDataNasc());
        System.out.println("Plano: " + al.getIdPlano());
        if(pl) {
            System.out.println("Data de adesão: " + al.getDataPlano());
            System.out.println("Número do cartão: " + al.getnCartao());
            System.out.println("Data de vencimento: " + al.getDataVenc());
            System.out.println("CVC: " + al.getCVC());
        }
    }

    private static void listarTreino(Treino tr){
        System.out.println(tr.getIdTreino() + ": " + tr.getNome());
    }

    private static void listarExercicio(Exercicio ex){
        System.out.println(ex.getNome());
    }

    private static void listarTreinoExercicio(TreinoExercicio trex){
        ExercicioDAO exDAO = new ExercicioDAO();
        Exercicio ex = exDAO.buscarExercicio(trex.getIdExercicio());
        listarExercicio(ex);
        System.out.println("Número de séries: " + trex.getnSeries());
        System.out.println("Número de repetições: " + trex.getMinRep() + "-" + trex.getMaxRep());
        System.out.println("Carga recomendada: " + trex.getCarga());
        System.out.println("Descanso recomendado: " + trex.getDescanso());
    }

    private static void listarPlano(Plano pl){
        System.out.println(pl.getId() + ": " + pl.getNome() + ": " + pl.getPreco());
    }

    public static void menuPrincipal(){
        int op;
        do {
            System.out.println("Bem-vindo! O que você deseja?");
            System.out.println("1: Entrar como instrutor");
            System.out.println("2: Entrar como aluno");
            System.out.println("3: Gerar relatorios");
            System.out.println("0: Sair");
            op = int_input(">");
            switch (op) {
                case 1 -> menuInstrutor();
                case 2 -> menuAluno();
                case 3 -> menuRelatorios();
                default -> System.out.println("Adeus! Volte sempre!");
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
            op = int_input(">");
            switch (op) {
                case 1 -> menuInstrutorAlunos();
                case 2 -> menuInstrutorPlanos();
                case 3 -> menuInstrutorExercicios();
                default -> {
                }
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
            op = int_input(">");
            switch (op) {
                case 1 -> menuInstrutorAlunosCadastrar();
                case 2 -> menuInstrutorAlunosBuscar();
                case 3 -> menuInstrutorAlunosAlterar();
                default -> {
                }
            }
        }while(op != 0);
    }

    public static void menuInstrutorAlunosCadastrar(){
        System.out.println("Informações pessoais: ");
        String cpf = input("Digite o cpf: ");
        String nome = input("Digite o nome: ");
        Date dataNasc = Date.valueOf(input("Digite a data de nascimento (YYYY-MM-DD): "));
        Aluno aluno = new Aluno(cpf, nome, dataNasc);
        System.out.println("Informações do plano: ");
        int idPlano = int_input("Digite o código do plano: ");
        Date dataPlano = Date.valueOf(input("Digite a data de adesão ao plano (YYYY-MM-DD): "));
        String nCartao = input("Digite o número do cartão: ");
        Date dataVenc = Date.valueOf(input("Digite a data de vencimento do cartão (YYYY-MM-DD): "));
        int cvc = int_input("Digite o CVC do cartão: ");
        aluno.setPlanoAluno(idPlano, dataPlano, nCartao, dataVenc, cvc);
        AlunoDAO alDAO = new AlunoDAO();
        aluno = alDAO.inserirAluno(aluno);
        System.out.println("Aluno cadastrado com sucesso!");
        listarAluno(aluno, true);
    }

    public static void menuInstrutorAlunosBuscar(){
        //da pra implementar busca por nome tbm, mas por enquanto so por cpf
        String cpf = input("Digite o CPF: ");
        AlunoDAO alDAO = new AlunoDAO();
        try {
            Aluno al = alDAO.buscarAlunoCPF(cpf);
            listarAluno(al, false);
        } catch (WrongArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void menuInstrutorAlunosAlterar(){
        int id = int_input("Digite o id do aluno: ");
        AlunoDAO alDAO = new AlunoDAO();
        Aluno al = alDAO.buscarAlunoID(id);
        int op;
        do {
            System.out.println("1: Alterar Plano");
            System.out.println("2: Alterar Treinos");
            System.out.println("3: Excluir aluno");
            System.out.println("0: Voltar");
            op = int_input(">");
            switch (op) {
                case 1 -> menuInstrutorAlunosAlterarPlano(al, alDAO);
                case 2 -> menuInstrutorAlunosAlterarTreinos(al);
                case 3 -> menuInstrutorAlunosAlterarExcluir(al, alDAO);
                default -> {
                }
            }
        }while(op != 0 && op != 3);
    }

    public static void menuInstrutorAlunosAlterarPlano(Aluno al, AlunoDAO alDAO){
        int idPlano = int_input("Digite o novo código do plano: ");
        Date dataPlano = Date.valueOf(input("Digite a data de adesão: "));
        int sn = int_input("Deseja alterar informações do cartão? (1 = sim, 0 = não): ");
        if(sn == 1){
            String nCartao = input("Digite o número do cartão: ");
            Date dataVenc = Date.valueOf(input("Digite a data de vencimento: "));
            int cvc = int_input("Digite o CVC: ");
            al.setPlanoAluno(idPlano, dataPlano, nCartao, dataVenc, cvc);
        } else al.setPlanoAluno(idPlano, dataPlano, al.getnCartao(), al.getDataVenc(), al.getCVC());
        alDAO.alterarPlano(al);
        System.out.println("Plano alterado com sucesso!");
    }

    public static void menuInstrutorAlunosAlterarTreinos(Aluno al){
        int op;
        do {
            System.out.println("1: Criar treino");
            System.out.println("2: Listar treinos");
            System.out.println("3: Listar exercícios do treino");
            System.out.println("4: Adicionar exercícios ao treino");
            System.out.println("5: Renomear treino");
            System.out.println("6: Excluir treino");
            System.out.println("0: Voltar");
            op = int_input(">");
            switch(op){
                case 1:
                    menuInstrutorAlunosAlterarTreinosCriar(al);
                    break;
                case 2:
                    menuXListarTreinos(al);
                    break;
                case 3:
                    menuXListarExercicios(al);
                    break;
                case 4:
                    menuInstrutorAlunosAlterarTreinosAdicionarExercicios(al, null);
                    break;
                case 5:
                    menuInstrutorAlunosAlterarTreinosRenomear(al);
                    break;
                case 6:
                    menuInstrutorAlunosAlterarTreinosExcluir(al);
                default:
                    break;
            }
        }while(op != 0);
    }

    public static void menuInstrutorAlunosAlterarTreinosCriar(Aluno al){
        TreinoDAO trDAO = new TreinoDAO();
        String nome = input("Digite o nome do treino: ");
        Treino tr = new Treino(al.getIdAluno(), nome);
        tr = trDAO.inserirTreino(tr);
        int sn = int_input("Deseja inserir exercícios ao treino? (1 = sim, 0 = não): ");
        if(sn == 1) menuInstrutorAlunosAlterarTreinosAdicionarExercicios(al, tr);
        System.out.println("Treino criado com sucesso!");
    }

    public static void menuXListarTreinos(Aluno al){
        TreinoDAO trDao = new TreinoDAO();
        ArrayList<Treino> listTr = trDao.buscarTodos(al.getIdAluno());
        for (Treino tr: listTr) {
            listarTreino(tr);
        }
    }

    public static void menuXListarExercicios(Aluno al){
        TreinoExercicioDAO trexDAO = new TreinoExercicioDAO();
        int id = int_input("Digite o id do treino: ");
        try {
            ArrayList<TreinoExercicio> listTrEx = trexDAO.buscarTodos(id, al.getIdAluno());
            for (TreinoExercicio trex : listTrEx) {
                listarTreinoExercicio(trex);
            }
        } catch(WrongArgumentException e){
            System.out.println("Não foi encontrado nenhum exercício pertencente a esse treino.");
        }
    }

    public static void menuInstrutorAlunosAlterarTreinosAdicionarExercicios(Aluno al, Treino tr){
        if(tr == null){
            int idTreino = int_input("Digite o ID do treino: ");
            try {
                tr = new TreinoDAO().buscarTreino(al.getIdAluno(), idTreino);
            } catch (WrongArgumentException e){
                System.out.println("Treino não existe.");
                return;
            }
        }
        int sn;
        TreinoExercicioDAO trexDAO = new TreinoExercicioDAO();
        do{
            int idEx = int_input("Digite o ID do exercicio: ");
            try {
                TreinoExercicio trex = new TreinoExercicio(tr.getIdTreino(), al.getIdAluno(), idEx);
                trexDAO.inserirTreinoExercicio(trex);
            } catch(RuntimeException e){
                System.out.println("Esse exercício não existe.");
            }
            sn = int_input("Deseja inserir mais um exercício? (1 = sim, 0 = não): ");
        }while(sn != 0);
    }

    public static void menuInstrutorAlunosAlterarTreinosRenomear(Aluno al){
        TreinoDAO trDAO = new TreinoDAO();
        int idTr = int_input("Digite o id do treino: ");
        Treino tr = trDAO.buscarTreino(al.getIdAluno(), idTr);
        tr.setNome(input("Digite o novo nome do treino: "));
        trDAO.alterarNome(tr);
        System.out.println("Nome alterado com sucesso!");
        listarTreino(tr);
    }

    public static void menuInstrutorAlunosAlterarTreinosExcluir(Aluno al){
        TreinoDAO trDAO = new TreinoDAO();
        int idTr = int_input("Digite o id do treino: ");
        Treino tr = trDAO.buscarTreino(al.getIdAluno(), idTr);
        trDAO.excluirTreino(tr);
        System.out.println("Treino excluído.");
    }

    public static void menuInstrutorAlunosAlterarExcluir(Aluno al, AlunoDAO alDAO){
        int sn = int_input("Deseja mesmo excluir esse aluno do sistema? (1 = sim, 0 = não)");
        if(sn == 1){
            alDAO.excluirAluno(al);
            System.out.println("Aluno excluído do sistema.");
        }
    }

    public static void menuInstrutorPlanos(){
        int op;
        do {
            System.out.println("1: Cadastrar plano");
            System.out.println("2: Listar planos");
            System.out.println("3: Alterar preço do plano");
            System.out.println("0: Voltar");
            op = int_input(">");
            switch (op) {
                case 1 -> menuInstrutorPlanosCadastrar();
                case 2 -> menuInstrutorPlanosListar();
                case 3 -> menuInstrutorPlanosAlterar();
                default -> {
                }
            }
        }while(op != 0);
    }

    public static void menuInstrutorPlanosCadastrar(){
        int idPlano = int_input("Digite o código do novo plano: ");
        String nome = input("Digite o nome do plano: ");
        float preco = float_input("Digite o valor mensal do plano: ");
        Plano pl = new Plano(idPlano, nome, preco);
        new PlanoDAO().inserirPlano(pl);
        System.out.println("Plano inserido com sucesso!");
    }

    public static void menuInstrutorPlanosListar(){
        PlanoDAO plDAO = new PlanoDAO();
        ArrayList<Plano> listPl = plDAO.buscarTodos();
        for(Plano pl : listPl){
            listarPlano(pl);
        }
    }

    public static void menuInstrutorPlanosAlterar(){
        PlanoDAO plDAO = new PlanoDAO();
        int id = int_input("Digite o ID do plano: ");
        Plano pl = plDAO.buscarPlanoId(id);
        float preco = float_input("Digite o novo preço: ");
        pl.setPreco(preco);
        plDAO.alterarPrecoPlano(pl);
        System.out.println("Preço alterado!");
    }

    public static void menuInstrutorExercicios(){
        int op;
        do {
            System.out.println("1: Cadastrar exercício");
            System.out.println("2: Listar exercícios");
            System.out.println("3: Excluir exercício");
            System.out.println("0: Voltar");
            op = int_input(">");
            switch (op) {
                case 1 -> menuInstrutorExerciciosCadastrar();
                case 2 -> menuInstrutorExerciciosListar();
                case 3 -> menuInstrutorExerciciosExcluir();
                default -> {
                }
            }
        }while(op != 0);
    }

    public static void menuInstrutorExerciciosCadastrar(){
        int id = int_input("Digite o ID do exercício: ");
        String nome = input("Digite o nome do exercício: ");
        String musculos = input("Digite os músculos ativados pelo exercício (separados por vírgula): ");
        Exercicio ex = new Exercicio(id, nome, musculos);
        ExercicioDAO exDAO = new ExercicioDAO();
        exDAO.inserirExercicio(ex);
        System.out.println("Exercício cadastrado!");
    }

    public static void menuInstrutorExerciciosListar(){
        ExercicioDAO exDAO = new ExercicioDAO();
        ArrayList<Exercicio> listEx = exDAO.buscarTodos();
        for(Exercicio ex : listEx){
            listarExercicio(ex);
        }
    }

    public static void menuInstrutorExerciciosExcluir(){
        int idEx = int_input("Digite o ID do exercício: ");
        ExercicioDAO exDAO = new ExercicioDAO();
        Exercicio ex;
        try{
            ex = exDAO.buscarExercicio(idEx);
            exDAO.excluirExercicio(ex);
            System.out.println("Exercício excluído.");
        } catch (WrongArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void menuAluno(){
        int id = int_input("Digite seu código: ");
        Aluno al;
        try { al = new AlunoDAO().buscarAlunoID(id); }
        catch(WrongArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        int op;
        do {
            System.out.println("1: Listar treinos");
            System.out.println("2: Listar exercícios do treino");
            System.out.println("3: Anotar execução ");
            System.out.println("0: Voltar");
            op = int_input(">");
            switch (op) {
                case 1 -> menuXListarTreinos(al);
                case 2 -> menuXListarExercicios(al);
                case 3 -> menuAlunoExecucao(al);
                default -> {
                }
            }
        }while(op != 0);
    }

    public static void menuAlunoExecucao(Aluno al){
        int idEx = int_input("Digite o ID do exercício: ");
        Date dataExec = Date.valueOf(input("Digite a data de execução (YYYY-MM-DD): "));
        AlunoExercicio aluex = new AlunoExercicio(al.getIdAluno(), idEx, dataExec);
        float carga = float_input("Digite a carga: ");
        aluex.setCarga(carga);
        AlunoExercicioDAO aluexDAO = new AlunoExercicioDAO();
        try {
            aluexDAO.inserirAlunoExercicio(aluex);
        } catch (RuntimeException e){
            System.out.println("Não foi possível inserir a execução.");
        }
    }

    public static void menuRelatorios(){
        int op;
        do {
            System.out.println("1: Mostrar datas que o aluno compareceu à academia");
            System.out.println("2: Mostrar evolução do aluno em um exercício");
            System.out.println("0: Voltar");
            op = int_input(">");
            switch (op) {
                case 1 -> menuRelatorios1();
                case 2 -> menuRelatorios2();
                default -> {
                }
            }
        }while(op != 0);
    }

    public static void menuRelatorios1(){
        int idAl = int_input("Digite o código do aluno: ");
        Aluno al;
        try {
            al = new AlunoDAO().buscarAlunoID(idAl);
        } catch (WrongArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        ArrayList<Date> listDatas = new AlunoExercicioDAO().buscarDatas(al.getIdAluno());
        for(Date d : listDatas){
            System.out.println(d.toString());
        }
    }

    public static void menuRelatorios2(){
        int idAl = int_input("Digite o código do aluno: ");
        Aluno al;
        Exercicio ex;
        try {
            al = new AlunoDAO().buscarAlunoID(idAl);
        } catch (WrongArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        int idEx = int_input("Digite o ID do exercício: ");
        try {
            ex = new ExercicioDAO().buscarExercicio(idEx);
        } catch (WrongArgumentException e){
            System.out.println(e.getMessage());
            return;
        }
        Date dataIni = Date.valueOf(input("Digite a data inicial: "));
        Date dataFim = Date.valueOf(input("Digite a data final: "));
        ArrayList<AlunoExercicio> listAluEx = new AlunoExercicioDAO().buscarAluEx(al.getIdAluno(), ex.getId(), dataIni, dataFim);
        for(AlunoExercicio aluex : listAluEx){
            System.out.println(aluex.getDataExercicio().toString() + ": " + aluex.getCarga());
        }
    }
}
