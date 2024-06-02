import com.opencsv.CSVWriter;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        File file = new File(FILE_PATH);
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
            e.printStackTrace();
        }
    }

    private void writeToCsv() throws IOException {
        File file = new File(FILE_PATH);
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter writer = new CSVWriter(fileWriter);
        writer.writeNext(new String[]{title, author, isbn, pages, year, availability, status});
        writer.close();
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
 * Buscar Livros;
 * Buscar Autores;
 * Buscar Disciplina;
 * Buscar ISBN;
*/
