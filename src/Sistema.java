import br.com.ConexaoBanco.ConexaoMySQL;

import java.sql.*;
import java.util.Scanner;


public class Sistema {
    private String nomeAcademia;

    public Sistema(String nome){
        this.nomeAcademia = nome;
    }

    private static final Scanner input = new Scanner(System.in);
    private Connection con = ConexaoMySQL.getConexaoMySQL();

    public String getNomeAcademia() {
        return nomeAcademia;
    }
    public void inserir_plano_bd(Planos plano){
        String sql = "INSERT INTO plano (idPlano, nome, preco) VALUES(?,?,?)";
        try(PreparedStatement statement = con.prepareStatement(sql)){
          statement.setInt(1, plano.getId());
          statement.setString(2, plano.getNome());
          statement.setFloat(3,plano.getPreco());
          statement.executeUpdate();
        } catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void inserir_aluno(Alunos aluno,Planos plano,Date dataPlano,String nCartao,Date dataVenc,int cvc) {
        String sql = "INSERT INTO aluno (cpf,nome,dataNasc, idPlano, nCartao, dataVenc, cvc, dataPlano) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, aluno.getCPF());
            statement.setString(2, aluno.getNome());
            statement.setDate(3,  aluno.getDataNascimento());
            statement.setInt(4,plano.getId());
            statement.setString(5,nCartao);
            statement.setDate(6, dataVenc);
            statement.setInt(7,cvc);
            statement.setDate(8, dataPlano);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void procurar_aluno_CPF(String cpf){
        try  {
            String sql = "SELECT * FROM aluno WHERE cpf = ?";
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setString(1, cpf); // Define o valor do parâmetro
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Recupera os dados do resultado
                        int cpfResult = resultSet.getInt("cpf");
                        String nomeResult = resultSet.getString("nome");
                        Date dataNascResult = resultSet.getDate("dataNasc");
                        System.out.printf("Aluno: CPF = %d; Nome = %s; Data Nascimento = %s\n", cpfResult, nomeResult, dataNascResult);
                        }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
