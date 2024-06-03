import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Scanner;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String pages;
    private String year;
    private String availability;
    private String status = "Disponivel";
    private static final String FILE_PATH = "livros.csv";

        //Criando a classe Book.
    public Book(String title, String author, String isbn, String pages, String year, String availability) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
        this.year = year;
        //Se a disponibilidade do livro for Online ou Offline na criação, OK. Se não, erro.
        if (availability.equals("Online") || availability.equals("Offline")) {
            this.availability = availability;
        }
        else{
            throw new IllegalArgumentException("Argumento Disponibilidade é inválido. Apenas \"Online\" ou \"Offline\" é permitido.");
        }
        this.status = "Disponivel";
        try {
            writeToCsv();
            }
        catch (IOException e){
            System.err.println("Erro ao gravar os dados do livro." + e);
        }

    }

    private void writeToCsv() throws IOException {
        File file = new File(FILE_PATH);
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fileWriter);
        writer.writeNext(new String[]{title, author, isbn, pages, year, availability, status});
        writer.close();
    }

    public static void busca() {
        //O IntelliJ reclama que o loop abaixo é infinito. Bem, ele é feito pra ser infinito, então tá tudo certo.
        //O método quit() será responsável por finalizar o loop.
        boolean loop = true;
        while (loop) {
            System.out.println("""
                                Escolha, de acordo com o indice a baixo, se gostaria de pesquisar o livro por:
                                1. Título
                                2. Autor ou;
                                3. ISBN
                                4. Sair
                                """);
            Scanner sc = new Scanner(System.in);
            switch (sc.nextLine()) {
                case "1" -> buscaLivro(1);
                case "2" -> buscaLivro(2);
                case "3" -> buscaLivro(3);
                case "4" -> loop = false;
                default-> System.out.println("Opcao inválida.");
            }
        }
    }
    //Só pra facilitar na hora de inserir um loading artificial.
    public static void load(int time){
        try{
            Thread.sleep(time);
        }
        catch(InterruptedException e){
            System.err.println("Falha durante carregamento.");
        }
    }

    private static void buscaLivro(int keyType) {
        if (keyType == 1) {
            System.out.println("Insira o título do livro:");
        }
        if (keyType == 2) {
            System.out.println("Insira o autor do livro:");
        }
        if (keyType == 3) {
            System.out.println("Insira o isbn do livro:");
        }
        Scanner sc = new Scanner(System.in);
        String keyWord = sc.nextLine();
        try {
            CSVReader achaCSV = new CSVReader(new FileReader(FILE_PATH));
            String [] linha;
            try {
                while ((linha = achaCSV.readNext())!=null) {
                    if (linha[keyType - 1].equals(keyWord)) {
                        System.out.println("Nome:" + linha[0]);
                        System.out.println("Autor:" + linha[1]);
                        System.out.println("ISBN:" + linha[2]);
                        System.out.println("Número de páginas: " + linha[3]);
                        System.out.println("Ano: " + linha[4]);
                        System.out.println("Disponibilidade: " + linha[5]);
                        System.out.println("Status: " + linha[6]);
                        System.out.println("Esse e o livro desejado?");
                        sc.nextLine();
                    }
                }
            } catch (IOException | CsvValidationException e){
                System.err.println("Falha durante busca." + e);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("Banco de dados inacessível." + e);
        }
    }

    //Getters
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPages() {
        return pages;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getAvailability() {
        return availability;
    }
    public String getStatus() {
        return status;
    }
    public String getYear() {
        return year;
    }

    //Setters
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPages(String pages) {
        int pag = Integer.parseInt(pages);
        if (pag < 1) {
            throw new IllegalArgumentException("Numero de páginas precisa ser >=1.");
        }
        else {
            this.pages = pages;
        }
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setAvailability(@NotNull String availability) {
        if (availability.equals("Online") || availability.equals("Offline")) {
            this.availability = availability;
            this.status = "Disponivel";
        }
        else {
            throw new IllegalArgumentException("Argumento Disponibilidade é inválido. Apenas \"Online\" ou " +
                    "\"Offline\" é permitido.");
        }
    }
    public void setStatus(@NotNull String status) {
        if (status.equals("Disponivel")||status.equals("Reservado")||status.equals("Emprestado")||
                status.equals("Indisponivel")) {
            this.status = status;
        }
        else{
            throw new RuntimeException("Argumento Status inválido. Apenas as opções \"Disponivel\", \"Reservado\", " +
                    "\"Emprestado\" ou \"Indisponivel\" são permitidas.");
        }
    }
    public void setYear(String year) {
        this.year = year;
    }
}

/*
 * -------Gerenciamento de Livros-------
 * Incluir Livro;
 * Retirar Livro;
 * Emprestar Livro para terceiros;
 * Retorno de empréstimo;
 * Registro de empréstimo de livros;
 * Registro de livros reservados para retirada;
 * -------Busca de Livros-------
 * Buscar Livros; ok
 * Buscar Autores; ok
 * Buscar Disciplina; ok
 * Buscar ISBN; ok
*/
