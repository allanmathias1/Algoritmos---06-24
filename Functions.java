import java.io.*;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.stream.reader.LineReader;
import org.jetbrains.annotations.Nullable;


public class Functions {
    public static String @Nullable [] login(String login, String senha) {
        String filePath = "user.csv";
        try{
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] linha;
            try{
                while ((linha = reader.readNext()) != null) {
                    if (linha[0].equals(login) && linha[1].equals(senha)) {
                        System.out.println("Login aprovado.");
                        reader.close();
                        return linha;
                    }
                }
                reader.close();
                System.out.println("Login recusado. Informações incorretas.");
                return null;
            }
            catch(Exception e){
                System.err.println("Falha ao ler o arquivo.");
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Falha: arquivo não encontrado.\n" + e.getMessage());
            return null;
        }
        return null;
    }

    public static void userMenu(String[] userInfo){
        while (true) {
            System.out.println("""
                    Bem vindo ao sistema informatizado de pesquisa de livros WeBibliotech.
                    Por favor, de acordo com o índice abaixo, escolha uma opcao.
                    1. Informacoes de usuario
                    2. Pesquisar livros
                    3. Sair""");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Informações de usuário abaixo:");
                    System.out.println("Login: " + userInfo[0]);
                    System.out.println("Email: " + userInfo[2]);
                    System.out.println("Tipo de usuário: " + userInfo[3]);

            }
        }

    }
    public static void adminMenu(String[] userInfo){
        System.out.println("""
                Bem vindo ao sistema informatizado de pesquisa de livros WeBibliotech.
                Por favor, de acordo com o índice abaixo, escolha uma opcao.
                1. Informacoes de usuario
                2. Pesquisar livros
                3. Sair""");
    }
}

/*
* Funções que ainda precisam ser feitas:
* -------Gerenciamento de Livros-------
* Incluir Livro;
* Retirar Livro;
* Emprestar Livro para terceiros;
* Retorno de empréstimo;
* Registro de empréstimo de livros;
* Registro de livros reservados para retirada;
* -------Busca de Livros-------
* Buscar Livros;
* Buscar Autores;
* Buscar Disciplina;
* Buscar ISBN;
* */