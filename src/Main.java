import br.com.ConexaoBanco.ConexaoMySQL;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws ParseException {

        Sistema academia = new Sistema("Minha Academia");
        Alunos aluno1 = new Alunos("Lucas", "10101","1999-08-10");
        Alunos aluno2 = new Alunos("Abner", "01010","1999-09-10");
        Planos plano1 = new Planos("Normal", 1, 64.99f);
        // Só pra testar se tá funcionando mesmo, depois ajeita legal na hr de cadastrar alguem no banco
        String data = "2025-10-10";
        Date datateste = Date.valueOf(data);
        academia.inserir_plano_bd(plano1);
        academia.inserir_aluno(aluno1,plano1,datateste,"1234 5678 8900 1234", datateste, 123);
        academia.inserir_aluno(aluno2,plano1,datateste,"1234 5678 8900 1234", datateste, 321);
        academia.procurar_aluno_CPF("10101");
        academia.procurar_aluno_CPF("01010");


    }
}