
/**
 * Created by haluan on 3/7/16.
 */
public class Main {
    public static void main(String[] args){
        long startTime = System.nanoTime();
        final int SIKLUS = 1;
        int initAyam = 9;
        int targetAyam = 900;
        System.out.println(targetAyam + " ayam dalam "+ovipar(initAyam, targetAyam, SIKLUS) + " siklus");
        int initKucing = 23;
        int targetKucing = 830;

        System.out.println(targetKucing + " kucing dalam "+vivipar(initKucing, targetKucing, SIKLUS)+" siklus");
        System.out.println(ovivipar(4, 900, SIKLUS));
        long endTime = System.nanoTime();
        long finishTime = (endTime-startTime)/1000000;
        System.out.println(finishTime);
    }
    public static int ovipar(int initialSum, int target, int counter){
        double temp1 = Math.floor(Math.sqrt(initialSum));
        double temp2 = (initialSum*initialSum);
        double temp3 = (initialSum*3) + 1;
        int rasio = (int)Math.floor(((temp2)+(temp3))/temp1);
        int result = initialSum * rasio;
        if(result <= target){
            counter += 1;
            return ovipar(result, target, counter);
        }
        return  counter;
    }
    public static int vivipar(int initialSum, int target, int counter){
        double temp1 = Math.floor(Math.sqrt((initialSum*initialSum)+1));
        int rasio = (int)Math.floor((initialSum+temp1)/initialSum);
        int result = rasio * initialSum;
        if(result <= target){
            counter +=1;
            return vivipar(result, target, counter);
        }
        return counter;
    }

    public static int ovivipar(int initialSum, int target, int counter){
        double temp1 = 2*(((initialSum*initialSum)+(3*initialSum)+1)/(Math.sqrt(initialSum)));
        double temp2 = 5*((initialSum+(Math.sqrt((initialSum)+((initialSum*initialSum)+1))))/initialSum);
        int rasio = (int)Math.floor(temp1+temp2);
        int result = rasio * initialSum;
        if(result <= target){
            counter +=1;
            return ovivipar(result, target, counter);
        }
        return counter;
    }
}
