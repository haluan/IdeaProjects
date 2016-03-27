import java.util.Scanner;

/**
 * Created by haluan on 3/20/16.
 */
class Mystery1{
    public static String mystery(int a, int b){
        int hB;
        hB = a/b;
        String sls = Integer.toString(a%b);
        if(hB == 0) return sls;
        return mystery(hB, b) + sls;
    }
}
public class Online {
    public static void main(String args[]){
//        int bD, bas;
//        String hasil;
//        Scanner input = new Scanner(System.in);
//        bD = input.nextInt();
//        bas = input.nextInt();
//        hasil = Mystery1.mystery(bD, bas);
//        System.out.println(hasil);
//        mystery(0,4);
        mystery(5,9);

    }

    public static void mystery(int a, int b){
        if(a==b){
            System.out.print(a);
        }else{
            int m1 = (a+b)/2;
            int m2 = (a+b+1)/2;
            mystery(a, m1);
            mystery(m2,b);
        }
    }

    public static void hehe(int a, int b){
        if(a%2 == b){
            System.out.print("Ganteng");
            return;
        }
        else if((a%2-1)==b){
            System.out.print("Anda");
        }
        if(b%2 != 0){
            System.out.print("Asdos");
        }
        else{
            System.out.print("Saya");
        }
        hehe(a, b-1);
    }

    public static void rekursif(int n){
        if(n<=0){
            return;
        }
        System.out.print(n);
        rekursif(n-2);
        rekursif(n-3);
        System.out.print(n);
    }
}
