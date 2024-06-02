import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String login;
    private String password;
    private String email;
    private String type;
    private static final String FILE_PATH = "user.csv";

    public User(@NotNull String login,@NotNull  String password,@NotNull  String email, @NotNull String type) {
        this.login = login;
        this.password = password;
        this.email = email;
        if (type.equals("Admin") || type.equals("User")) {
            this.type = type;
            try{
               writeToCSV();
            }
            catch (IOException e){
                System.err.println(e);
        }}  else {
            throw new IllegalArgumentException("Tipo de usuario invalido. Deve ser \"Admin\" ou \"User\".");
        }
    }

    private void writeToCSV() throws IOException {
        File file = new File(FILE_PATH);
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fileWriter);
        writer.writeNext(new String[]{login, password, email, type});
        writer.close();
    }

    public void createUser() {
        System.out.println("Criando um novo usuario");
        Scanner sc = new Scanner(System.in);
        System.out.println("Login: ");
        String login = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Tipo de usuario: ");
        String type = sc.nextLine();
        new User(login, password, email, type);
    }

    public void deleteUser(String login){
        try{
            File base = new File(FILE_PATH);
            File temp = new File("temp.csv");
            CSVReader reader = new CSVReader(new FileReader(base));
            CSVWriter writer = new CSVWriter(new FileWriter(temp));
            String[] baseline = reader.readAll();
            String[] newline = new ArrayList<>();
            for (String[] line : baseline){
                if (!line[0].equals(login)){
                    newline.add(line);
                }
            }
            writer.writeAll(newline);
            reader.close();
            writer.close();
            temp.renameTo(base);
            System.out.println("Usuário excluído com sucesso!");
        }
        catch (FileNotFoundException e){
            System.err.println(e);
        }
    }

    //Getters
    public String getLogin() {
        return this.login;
    }
    public String getPassword() {
        return this.password;
    }
    public String getEmail() {
        return this.email;
    }
    public String getType() {
        return this.type;
    }


    //Setters
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setType(String type) {
        this.type = type;
    }
}
/*
 * Funções que ainda precisam ser feitas:
 * -------Gerenciamento de Usuários-------
 * Criar Usuário; ok
 * Deletar Usuário;
 * Editar Usuário;
 * Trocar senha de usuário;
 * */
