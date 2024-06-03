import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo ao WeBibliotech.");
        //Criando uma instância do Scanner para receber valores digitados.
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Por favor, insira abaixo seu login e senha.");
            //Criando uma instância do Scanner para receber valores digitados.
            //Receber e salvar Login
            System.out.print("Login: ");
            String login = input.nextLine();
            //Receber e salvar Senha
            System.out.print("Senha: ");
            String password = input.nextLine();
            Functions.load(1000);
            //A função login retorna a linha com as informações do usuário, Login Senha Email e Tipo.
            //Se usuário admin, abre menu de admin. Se user, abre menu de user. Qualquer outra coisa, retorna login ou senha incorretos.
            //Melhoria futura: retirar informação SENHA da variável Linha, reduzindo vulnerabilidades.
            String[] linha = (Functions.login(login, password));
            System.out.println("\n\nIniciando módulo de menus.\n\n");
            Functions.load(1000);
            if (linha != null && linha[3].equals("Admin")) {
                Functions.adminMenu();
            } else if (linha != null && linha[3].equals("User")) {
                Functions.userMenu(linha);
            } else {
                //Abrindo a possibilidade de o usuário sair do sistema caso não saiba a senha.
                System.out.println("""
                Login ou senha incorretos.
                Em caso de perda de senha, por favor vá até a biblioteca para reiniciar sua senha.
                Deseja tentar novamente ou deseja sair?
                
                1. Tentar Novamente.
                2. Sair.
                
                Insira o numeral correspondente a opcao desejada:""");
                String opt = input.nextLine();
                if (opt.equals("2")) {
                    System.exit(0);
                }
            }
        }
    }
}
//main