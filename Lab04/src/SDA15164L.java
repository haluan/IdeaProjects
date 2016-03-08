import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by haluan on 3/7/16.
 */
public class SDA15164L {
    private static class Animal{
        private String nama;
        private String kategori;
        private int initialSum;
        public Animal(String nama, String kategori, int initialSum){
            this.nama = nama;
            this.kategori = kategori;
            this.initialSum = initialSum;
        }

        public void setInitialSum(int initialSum){
            this.initialSum = initialSum;
        }

        public String getNama() {
            return nama;
        }

        public String getKategori() {
            return kategori;
        }

        public int getInitialSum() {
            return initialSum;
        }
    }
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        final int SIKLUS = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int counterOne = Integer.parseInt(reader.readLine());
        StringTokenizer token;
        String firstCommand;
        Animal animal;
        HashMap<String, Animal> map = new HashMap<String, Animal>();
        String keyMap = "";
        int targetBeranak = 0;
        for(int i=0;i<counterOne;i++){
            firstCommand = reader.readLine();
            token = new StringTokenizer(firstCommand, " ");
            animal = new Animal(token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken()));
            map.put(animal.getNama(), animal);
        }
        counterOne = Integer.parseInt(reader.readLine());
        for(int i = 0; i < counterOne; i++){
            firstCommand = reader.readLine();
            token = new StringTokenizer(firstCommand, " ");
            keyMap = token.nextToken();
            animal = map.get(keyMap);
            if(animal == null){
                System.out.println("tidak ada binatang bernama paus");
                continue;
            }
            targetBeranak = animal.getInitialSum() + Integer.parseInt(token.nextToken());
            System.out.println( targetBeranak+" "+ animal.getNama() +" dalam "+counter(animal, targetBeranak, SIKLUS) + " siklus");
            animal.setInitialSum(targetBeranak);
            targetBeranak = 0;
        }
        long endTime = System.nanoTime();
        double finishTime = (endTime-startTime)/1_000_000_000.0;
        System.out.println(finishTime);
    }

    public static int counter(Animal animal, int targetBeranak, int SIKLUS){
        if("ovipar".equals(animal.getKategori())){
            return ovipar(animal.getInitialSum(), targetBeranak, SIKLUS, (int)Math.floor(((animal.getInitialSum()*animal.getInitialSum())+(animal.getInitialSum()*3) + 1)/Math.floor(Math.sqrt(animal.getInitialSum()))));
        }else if("vivipar".equals(animal.getKategori())){
            return vivipar(animal.getInitialSum(), targetBeranak, SIKLUS, (int)Math.floor((animal.getInitialSum()+(Math.floor(Math.sqrt((animal.getInitialSum()*animal.getInitialSum())+1))))/animal.getInitialSum()));
        }else if("ovivipar".equals(animal.getKategori())){
            return ovivipar(animal.getInitialSum(), targetBeranak, SIKLUS, (int)Math.floor((2*(((animal.getInitialSum()*animal.getInitialSum())+(3*animal.getInitialSum())+1)/(Math.sqrt(animal.getInitialSum()))))+(5*((animal.getInitialSum()+(Math.sqrt((animal.getInitialSum())+((animal.getInitialSum()*animal.getInitialSum())+1))))/animal.getInitialSum()))));
        }
        return 0;
    }

    public static int ovipar(int initialSum, int target, int counter, int rasio){
        int result = initialSum * rasio;
        if(result < target){
            counter += 1;
            return ovipar(result, target, counter, rasio);
        }
        return  counter;
    }
    public static int vivipar(int initialSum, int target, int counter, int rasio){
        int result = rasio * initialSum;
        if(result < target){
            counter +=1;
            return vivipar(result, target, counter, rasio);
        }
        return counter;
    }

    public static int ovivipar(int initialSum, int target, int counter, int rasio){
        int result = rasio * initialSum;
        if(result < target){
            counter +=1;
            return ovivipar(result, target, counter, rasio);
        }
        return counter;
    }
}
