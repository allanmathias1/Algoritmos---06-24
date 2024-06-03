import com.opencsv.CSVReader;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Functions {
    private static final String USER_FILE_PATH = "user.csv";

    public static String @Nullable [] login(String login, String senha) {
        try{
            CSVReader reader = new CSVReader(new FileReader(USER_FILE_PATH));
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
        //O IntelliJ reclama que o loop abaixo é infinito. Bem, ele é feito pra ser infinito, então tá tudo certo.
        //O método quit() será responsável por finalizar o loop.
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println("""
                    Bem vindo ao sistema informatizado de pesquisa de livros WeBibliotech.
                    Por favor, de acordo com o índice abaixo, escolha uma opcao.
                    1. Informacoes de usuario
                    2. Pesquisar livros
                    3. Sair""");
            Scanner sc = new Scanner(System.in);
            switch (sc.nextLine()) {
                case "1"->{
                    System.out.println("Informações de usuário abaixo:");
                    System.out.println("Login: " + userInfo[0]);
                    System.out.println("Email: " + userInfo[2]);
                    System.out.println("Tipo de usuário: " + userInfo[3]);
                    }
                case "2" -> Book.busca();
                case "3" -> quit();
            }
        }
    }
    public static void adminMenu(){
        System.out.println("Bem vindo ao sistema informatizado de pesquisa de Livro Webibliotech.");
        //O IntelliJ reclama que o loop abaixo é infinito. Bem, ele é feito pra ser infinito, então tá tudo certo.
        //O método quit() será responsável por finalizar o loop.
        //noinspection InfiniteLoopStatement
        while(true){
            System.out.println("""
                Por favor, de acordo com o índice abaixo, escolha uma opcao.
                1. Gerenciamento de Usuários
                2. Gerenciamento de Livros
                3. Sair do sistema""");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            switch (input) {
                    case "1" -> {
                        boolean loop = true;
                        while (loop){
                            System.out.println("Sistema de gerenciamento de usuários.");
                            System.out.println("""
                                    1. Criar usuário;
                                    2. Editar usuário;
                                    3. Deletar usuário;
                                    4. Sair
                                    """);
                            switch (sc.nextLine()) {
                                case "1" -> User.create();
                                case "2" -> User.edit();
                                case "3" -> User.delete();
                                case "4" -> {
                                    System.out.println("Retornando ao menu anterior.");
                                    loop = false;
                                }
                                default-> System.out.println("Opção inválida, por favor tente novamente.");
                            }
                        }
                    }
                        case "2" -> System.out.println("Sistema de gerenciamento de livros.");
                        case "3" -> quit();
            }
        }
    }
    private static void quit(){
        System.out.println("Finalizando sistema.");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.err.println("Falha ao finalizar o programa.");
        }
        System.out.println("Sistema finalizado com sucesso.\nEsperamos sua próxima visita.");
        Functions.load(1000);
        System.exit(0);
    }

    public static void load(int time){
        try{
            Thread.sleep(time);
        }
        catch(Exception e){
            System.err.println("Falha durante o carregamento." + e);
        }
    }
}


/*
Esse arquivo provavelmente só será alterado no futuro se houver alteração no método login.
É possível que novas funcionalidades possam ser adicionadas aqui, quando não tiverem correlação direta com a Classe Book
ou com a classe User.
* */