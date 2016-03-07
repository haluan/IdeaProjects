import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;

/**
 * Created by haluan on 3/7/16.
 */
public class Main {
    final static ExecutorService pool = Executors.newFixedThreadPool(5);
    public static void main(String[] args){
        long startTime = System.nanoTime();
        final int SIKLUS = 1;
        int initAyam = 9;
        int targetAyam = 900;
        Future<Integer> result = oviparCounter(initAyam, targetAyam, SIKLUS);
        try {
            System.out.println(targetAyam + " ayam dalam "+ result.get() + " siklus");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int initKucing = 23;
        int targetKucing = 830;
        result = viviparCounter(initKucing, targetKucing, SIKLUS);
        try {
            System.out.println(targetKucing + " kucing dalam "+ result.get() +" siklus");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        result = oviviparCounter(4, 900, SIKLUS);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        double finishTime = (endTime-startTime)/1000000.0;
        System.out.println(finishTime);
        System.exit(0);
    }

    public static Future<Integer> oviparCounter(int initialSum, int target, int counter){
        double temp1 = Math.floor(Math.sqrt(initialSum));
        double temp2 = (initialSum*initialSum);
        double temp3 = (initialSum*3) + 1;
        int rasio = (int)Math.floor(((temp2)+(temp3))/temp1);
        Future<Integer> finalCounter = ovipar(initialSum, target, counter, rasio);
        return  pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return finalCounter.get();
            }
        });
    }
    public static Future<Integer> ovipar(int initialSum, int target, int counter, int rasio){
        int result = initialSum * rasio;
        if(result <= target){
            counter += 1;
            return ovipar(result, target, counter, rasio);
        }
        int finalCounter = counter;
        return  pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return finalCounter;
            }
        });
    }
    public static Future<Integer> viviparCounter(int initialSum, int target, int counter){
        double temp1 = Math.floor(Math.sqrt((initialSum*initialSum)+1));
        int rasio = (int)Math.floor((initialSum+temp1)/initialSum);
        Future<Integer> finalCounter = vivipar(initialSum, target, counter, rasio);
        return  pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return finalCounter.get();
            }
        });
    }
    public static Future<Integer> vivipar(int initialSum, int target, int counter, int rasio){
        int result = rasio * initialSum;
        if(result <= target){
            counter +=1;
            return vivipar(result, target, counter, rasio);
        }
        int finalCounter = counter;
        return  pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return finalCounter;
            }
        });
    }
    public static Future<Integer> oviviparCounter(int initialSum, int target, int counter){
        double temp1 = 2*(((initialSum*initialSum)+(3*initialSum)+1)/(Math.sqrt(initialSum)));
        double temp2 = 5*((initialSum+(Math.sqrt((initialSum)+((initialSum*initialSum)+1))))/initialSum);
        int rasio = (int)Math.floor(temp1+temp2);
        Future<Integer> finalCounter = ovivipar(initialSum, target, counter, rasio);
        return  pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return finalCounter.get();
            }
        });

    }
    public static Future<Integer> ovivipar(int initialSum, int target, int counter, int rasio){
        int result = rasio * initialSum;
        if(result <= target){
            counter +=1;
            return ovivipar(result, target, counter, rasio);
        }
        int finalCounter = counter;
        return  pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return finalCounter;
            }
        });
    }
}
