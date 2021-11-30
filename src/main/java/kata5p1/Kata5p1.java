package kata5p1;

import View.MailListReader;
import Model.Database;
import java.util.List;

public class Kata5p1 {
    public static void main(String[] args) {
        Database BD = new Database("KATA5.db");
        //BD.Query();
        //BD.createNewTable();
        MailListReader mlr = new MailListReader();
        List<String> lista = mlr.read("email.txt");
        BD.insertData(lista);
    }
}
