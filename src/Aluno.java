import com.mysql.cj.exceptions.WrongArgumentException;

import java.sql.*;
import java.util.Scanner;

public class Aluno {
    private int idAluno;
    private String cpf;
    private String nome;
    private Date dataNasc;
    private int idPlano;
    private Date dataPlano;
    private String nCartao;
    private Date dataVenc;
    private int cvc;

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public Aluno(String cpf, String nome, String sDataNasc, int idPl, String sDPlano, String nCart, String sDVenc, int cvcNovo){
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = Date.valueOf(sDataNasc);
        setPlanoAluno(idPl,sDPlano,nCart,sDVenc,cvcNovo);
    }

    private void setPlanoAluno(int idPl, String sDPlano, String nCart, String sDVenc, int cvcNovo){
        this.idPlano = idPl;
        this.dataPlano = Date.valueOf(sDPlano);
        this.nCartao = nCart;
        this.dataVenc = Date.valueOf(sDVenc);
        this.cvc = cvcNovo;
    }

    //coloquei como estatico
    public static Aluno buscarAlunoCPF(Connection con, String cpf){
        String sql = "SELECT * FROM Aluno where cpf = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, cpf);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                int idAluno = rs.getInt(1);
                String cpfAluno = rs.getString(2);
                String nome = rs.getString(3);
                Date dataNasc = rs.getDate(4);
                int idPlano = rs.getInt(5);
                Date dataPlano = rs.getDate(6);
                String nCartao = rs.getString(7);
                Date dataVenc = rs.getDate(8);
                int cvc = rs.getInt(9);
                Aluno aluno = new Aluno(cpfAluno, nome, dataNasc.toString(), idPlano, dataPlano.toString(), nCartao, dataVenc.toString(), cvc);
                aluno.setIdAluno(idAluno);
                return aluno;
            }
            else{
                throw new WrongArgumentException("Esse CPF não está cadastrado no sistema");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //adicionei os dados do cartão no momento de cadastrar um aluno
    public void inserirAluno(java.sql.Connection con) {
        String sql = "INSERT INTO aluno (cpf, nome, dataNasc, idPlano, dataPlano, nCartao, dataVenc, cvc)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cpf);
            statement.setString(2, nome);
            statement.setDate(3,  dataNasc);
            statement.setInt(4,idPlano);
            statement.setDate(5,dataPlano);
            statement.setString(6,nCartao);
            statement.setDate(7,dataVenc);
            statement.setInt(8,cvc);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                idAluno = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluirDados(java.sql.Connection con) {
        String sql = "DELETE FROM aluno WHERE cpf = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, cpf);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePlanoAluno(java.sql.Connection con) {
        String sql = "UPDATE Aluno SET idPlano = ?, dataPlano = ?, nCartao = ?, dataVenc = ?, cvc = ? where idAluno = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idPlano);
            statement.setDate(2, dataPlano);
            statement.setString(3,  nCartao);
            statement.setDate(4,  dataVenc);
            statement.setInt(5, cvc);
            statement.setInt(6, idAluno);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void cadastrarAluno(){
        Aluno aluno = criarInstanciaAluno();
        aluno.inserirAluno(Main.con);
    }
    public static Aluno criarInstanciaAluno(){
        Scanner input = new Scanner(System.in);
        System.out.println("===Cadastrando aluno===");
        System.out.println("Informe o nome do aluno:");
        String nome = input.nextLine();
        System.out.println("Informe o cpf do aluno:");
        String cpf = input.nextLine();
        System.out.println("Informe a data de nascimento do aluno (yyyy-mm-dd):");
        String dataNasc = input.nextLine();
        System.out.println("Informe o id do Plano que o aluno será cadastrado:");
        int idPlano = input.nextInt(); input.nextLine();
        Plano.buscarPlanoId(Main.con, idPlano);
        System.out.println("Informe a data de ingresso no plano (yyyy-mm-dd):");
        String dataPlano = input.nextLine();
        System.out.println("Informe o numero do cartão:");
        String nCartao = input.nextLine();
        System.out.println("Informe o cvc do cartão:");
        int cvc = input.nextInt(); input.nextLine();
        System.out.println("Informe a data de vencimento do cartão (yyyy-mm-dd):");
        String dataVenc = input.nextLine();

        return new Aluno(cpf,nome,dataNasc,idPlano, dataPlano,nCartao,dataVenc,cvc);
    }

    public void alterarDados(){
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o Id do novo plano para alterar: ");
        int id = input.nextInt(); input.nextLine();
        System.out.println("Informe a data de inicio (yyyy-mm-dd): ");
        String dataIn = input.nextLine();
        System.out.println("Informe o numero do novo cartão: ");
        String nCart = input.nextLine();
        System.out.println("Informe a data de vencimento do cartão (yyyy-mm-dd):");
        String dataVenc = input.nextLine();
        System.out.println("Informe o cvc do cartão: ");
        int cvc = input.nextInt();
        this.setPlanoAluno(id,dataIn,nCart,dataVenc,cvc);
        this.updatePlanoAluno(Main.con);
    }

    public void mostrarDados() {
        //depois implementar
    }

    public void mostrarTreinos(java.sql.Connection con) {
        String sql = "SELECT * FROM treino WHERE idAluno = ?";
        try(PreparedStatement statement = con.prepareStatement(sql)){
            statement.setInt(1,idAluno);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int idTreino = rs.getInt(1);
                String nomeTreino = rs.getString(3);
                System.out.println("ID: " + idTreino + ". Treino de " + nomeTreino + ".");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
