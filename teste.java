import com.opencsv.CSVReader;
import java.io.*;

public class    teste {
    public static void main(String[] args) {
        try{
            CSVReader reader = new CSVReader(new FileReader("user.csv"));
            try{
                String[] cabecalho = reader.readNext();
                int loginIndex = -1;
                int senhaIndex = -1;
                for (int i = 0; i < cabecalho.length; i++) {
                    if (cabecalho[i].equalsIgnoreCase("login")) {
                        loginIndex = i;
                    } else if (cabecalho[i].equalsIgnoreCase("senha") || cabecalho[i].equalsIgnoreCase("password")) {
                        senhaIndex = i;
                    }
                }
                String[] linha;
                linha = reader.readNext();
                for (String palavra:linha){
                    System.out.println(palavra);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Deu ruim.");
        }
    }
}

