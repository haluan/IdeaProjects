import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by haluan on 2/19/16.
 */
public class SDA15161L {
    public static void main(String args[]){
        ArrayList<Nasabah> nasabahs = new ArrayList<Nasabah>();
        Nasabah nasabah = new Nasabah(nasabahs);

        //Shortable Nasabah
        Collections.sort(nasabahs);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String readString = "";
        try{
            readString = reader.readLine();
        }catch (IOException ioex){
            System.out.println(ioex.getMessage());
        }
        StringTokenizer token = new StringTokenizer(readString);
        int firstNumber = Integer.parseInt(token.nextToken());
        int secondNumber = Integer.parseInt(token.nextToken());
        int counterAddNAsabah = 0, counterCommandExecuted = 0;
        String firstCommand = "", secondCommand = "", thirdCommand = "";
        while(counterAddNAsabah < firstNumber){
            try{
                firstCommand = reader.readLine();
                secondCommand = reader.readLine();
                thirdCommand = reader.readLine();
            }catch(IOException ioex){

            }
            counterAddNAsabah ++;

        }
        System.out.println(firstCommand);
        String comandTriger = "", trigerActionOne = "", trigerActionTwo = "";
        while (counterCommandExecuted < secondNumber){
            try{
                firstCommand = reader.readLine();
            }catch(IOException ioex){

            }
            token = new StringTokenizer(firstCommand);
            comandTriger = token.nextToken();
            if("TAMBAH".equalsIgnoreCase(comandTriger)){
                System.out.println(comandTriger);
                System.out.println(token.nextToken());

            }else if("CARI".equalsIgnoreCase(comandTriger)){

            }else if("MEAN".equalsIgnoreCase(comandTriger)){

            }

            counterCommandExecuted ++;
        }


    }
}
