import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by haluan on 3/7/16.
 */
public class SDA15164L {
    public static void main(String[] args) throws IOException {
        final int SIKLUS = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int counterOne = Integer.parseInt(reader.readLine());
        StringTokenizer token;
        String firstCommand;
        Animal animal;
        HashMap<String, Animal> map = new HashMap<String, Animal>();
        String keyMap = "";
        int targetBeranak = 0;
        String animalName ="", animalKategori = "";

        for(int i=0;i<counterOne;i++){
            firstCommand = reader.readLine();
            token = new StringTokenizer(firstCommand, " ");
            animalName = token.nextToken();
            animalKategori = token.nextToken();
            animal = new Animal(animalName, animalKategori, Integer.parseInt(token.nextToken()));
            map.put(animal.getNama(), animal);
        }
        counterOne = Integer.parseInt(reader.readLine());
        for(int i = 0; i < counterOne; i++){
            firstCommand = reader.readLine();
            token = new StringTokenizer(firstCommand, " ");
            keyMap = token.nextToken();
            animal = map.get(keyMap);
            if(animal == null){
                System.out.println("tidak ada binatang bernama "+keyMap);
                continue;
            }
            if(kategoriMap().get(animal.getKategori()) != null) {
                targetBeranak = animal.getInitialSum() + Integer.parseInt(token.nextToken());
                System.out.println("butuh " + counter(animal, targetBeranak, SIKLUS) + " siklus untuk " + targetBeranak + " " + animal.getNama());
            }
            animal.setInitialSum(targetBeranak);
            targetBeranak = 0;
        }
        System.exit(0);
    }

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


    private static HashMap<String, Boolean> kategoriMap(){
        HashMap<String, Boolean> kategoriMap = new HashMap<>();
        kategoriMap.put("vivipar", true);
        kategoriMap.put("ovovivipar", true);
        kategoriMap.put("ovipar", true);
        return  kategoriMap;
    }

    

    public static int counter(Animal animal, int targetBeranak, int SIKLUS){
        if(animal.getInitialSum() < 1){
            return 0;
        }
        if(animal.getInitialSum() == targetBeranak){
            return  0;
        }
        if("ovipar".equals(animal.getKategori())){
            return ovipar(animal.getInitialSum(), targetBeranak, SIKLUS);
        }else if("vivipar".equals(animal.getKategori())){
            return vivipar(animal.getInitialSum(), targetBeranak, SIKLUS);
        }else if("ovovivipar".equals(animal.getKategori())){
            return ovovivipar(animal.getInitialSum(), targetBeranak, SIKLUS);
        }
        return  0;
    }

    public static int ovipar(int initialSum, int target, int counter){
        int rasio= (int) Math.floor(((initialSum*initialSum) + (3 * initialSum) + 1)/(double)initialSum);
        int result = initialSum * rasio;
        if(result < target){
            counter += 1;
            return ovipar(result, target, counter);
        }
        return counter;
    }
    public static int vivipar(int initialSum, int target, int counter){
        int rasio = (int) Math.floor(((initialSum * initialSum * initialSum) + 4)/(double)((initialSum * initialSum) + 1));
        int result = rasio * initialSum;
        if(result < target){
            counter +=1;
            return vivipar(result, target, counter);
        }
        return counter;
    }

    public static int ovovivipar(int initialSum, int target, int counter){
        int rasio = (int) Math.floor((3 * (((initialSum * initialSum) + (3 * initialSum) + 1)/(double)initialSum)) + (2* (((initialSum * initialSum * initialSum)+4)/((initialSum * initialSum)+1)))) ;
        int result = rasio * initialSum;
        if(result < target) {
            counter += 1;
            return ovovivipar(result, target, counter);
        }
        return counter;
    }
}
