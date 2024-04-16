import br.com.ConexaoBanco.ConexaoMySQL;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public class Main {
    static Connection con = ConexaoMySQL.getConexaoMySQL();

    public static void main(String[] args) throws ParseException {
        Aluno aluno1 = new Aluno("Lucas", "10101","1999-08-10");
        Aluno aluno2 = new Aluno("Abner", "01010","1999-09-10");
        Plano plano1 = new Plano("Normal", 1, 64.99f);
        // Só pra testar se tá funcionando mesmo, depois ajeita legal na hr de cadastrar alguem no banco
        String data = "2025-10-10";
        Date datateste = Date.valueOf(data);

        System.out.println(con);

        //aluno1.inserirAluno(con);
        //aluno2.inserirAluno(con);
        //plano1.inserirPlano(con);
    }
}
