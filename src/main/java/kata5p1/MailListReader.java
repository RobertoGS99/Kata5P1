
package kata5p1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MailListReader {
    public  List<String> read(String fileName){
        List<String> list= new ArrayList<String>();
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
            while (true){
                String line = bufferedReader.readLine();
                if (line == null) break; 
                if (line.contains("@")){
                    list.add(line);
                }
                
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException, MailListReader::read() "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException, MailListReader::read() "+ex.getMessage());
        }
        
        return list;
    }
}
