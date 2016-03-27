import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***
 *
 * @author haluan
 *
 * Anto : Seseorang yang kita tuntun dengan program yang kita buat untuk mencapai titik barang yang disembunyikan
 * "S"  : Titik start dimana Anto mulai mencari barangnya
 * "X"  : Titik dimana barang yang dicari Anto diletakkan
 * "."  : Merupakan jalur yang memungkinkan dilalui Anto
 * "#"  : Bisa dikatakan tembok labirin, Anto tidak bisa melalui jalur tersebut
 *
 */

public class SDA15165L {

    String[][] isiMazee;
    ArrayList<String> arrRute = new ArrayList<String>();
    ArrayList<Integer> lastX = new ArrayList<Integer>();
    ArrayList<Integer> lastY = new ArrayList<Integer>();
    boolean arahJam = true, arahMekah = false;

    public static void main(String[] args) throws Exception{
        SDA15165L sda = new SDA15165L();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String totalInput = br.readLine();
        ArrayList<String> arr = new ArrayList<String>();
        boolean first = false;
        String ruteFinal = "";

        StringTokenizer st = new StringTokenizer(totalInput, " ");

        int baris = Integer.parseInt(st.nextToken());
        int kolom = Integer.parseInt(st.nextToken());
        sda.isiMazee = new String[baris][kolom];

        for(int i = 0;i < baris;i++){
            String inputRow = br.readLine();
            arr.add(inputRow);
        }

        int isiBaris = 0;
        int positionSinX = 0;
        int positionSinY = 0;

        for(int x = 0;x < baris;x++){
            for(int y = 0;y < kolom;y++){
                sda.isiMazee[x][y] = arr.get(isiBaris).substring(y, Math.min(y + 1, arr.get(isiBaris).length()));
                if(arr.get(isiBaris).substring(y, Math.min(y + 1, arr.get(isiBaris).length())).equals("L")){
                    positionSinX = x;
                    positionSinY = y;
                }
            }
            isiBaris++;
        }

        ArrayList<String> rute = sda.ruteToX(positionSinX, positionSinY, baris, kolom);

        if(rute.isEmpty()){
            System.out.println("tidak ketemu");
        } else {
            for(int i = 0;i < rute.size();i++){
                if(!first){
                    ruteFinal = rute.get(i);
                    first = true;
                } else {
                    ruteFinal = ruteFinal + "-" + rute.get(i);
                }
            }
            System.out.println(ruteFinal);
        }

    }

    public ArrayList<String> ruteToX(int x, int y, int maxBaris, int maxKolom){

        if(isiMazee[x][y].equals("X")){
            return arrRute;
        }

        if(arahJam){
            if(x - 1 >= 0){
                if(isiMazee[x - 1][y].equals("T") || isiMazee[x - 1][y].equals(".") || isiMazee[x - 1][y].equals("X") || isiMazee[x - 1][y].equals("?") || isiMazee[x - 1][y].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("atas");
                    if (isiMazee[x - 1][y].equals("?")){
                        changeDirection();

                    }
                    if (isiMazee[x - 1][y].equals("G")){
                        System.out.println("Digisu");

                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x - 1, y, maxBaris, maxKolom);
                }
            }

            if(y + 1 < maxKolom){
                if(isiMazee[x][y + 1].equals("T") || isiMazee[x][y + 1].equals(".")  || isiMazee[x][y + 1].equals("?") || isiMazee[x][y + 1].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("kanan");
                    if (isiMazee[x][y + 1].equals("?")){
                        changeDirection();

                    }
                    if (isiMazee[x][y + 1].equals("G")){
                        System.out.println("Digisu");

                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x, y + 1, maxBaris, maxKolom);
                }
            }

            if(x + 1 < maxBaris){
                if(isiMazee[x + 1][y].equals("T") || isiMazee[x + 1][y].equals(".")  || isiMazee[x + 1][y].equals("?") || isiMazee[x + 1][y].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("bawah");
                    if (isiMazee[x + 1][y].equals("?")){
                        changeDirection();

                    }
                    if (isiMazee[x + 1][y].equals("G")){
                        System.out.println("Digisu");

                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x + 1, y, maxBaris, maxKolom);
                }
            }

            if(y - 1 >= 0){
                if(isiMazee[x][y - 1].equals("T") || isiMazee[x][y - 1].equals(".") || isiMazee[x][y - 1].equals("?") || isiMazee[x][y - 1].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("kiri");
                    if (isiMazee[x][y - 1].equals("?")){
                        changeDirection();

                    }
                    if (isiMazee[x][y - 1].equals("G")){
                        System.out.println("Digisu");

                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x, y - 1, maxBaris, maxKolom);
                }
            }
        }

        if(arahMekah){
            if(x - 1 >= 0){
                if(isiMazee[x - 1][y].equals("T") || isiMazee[x - 1][y].equals(".")  || isiMazee[x - 1][y].equals("?") || isiMazee[x - 1][y].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("atas");
                    if (isiMazee[x - 1][y].equals("?")){
                        changeDirection();

                    }
                    if (isiMazee[x - 1][y].equals("G")){
                        System.out.println("Digisu");

                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x - 1, y, maxBaris, maxKolom);
                }
            }

            if(y - 1 >= 0){
                if(isiMazee[x][y - 1].equals("T") || isiMazee[x][y - 1].equals(".")  || isiMazee[x][y - 1].equals("?") || isiMazee[x][y - 1].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("kiri");
                    if (isiMazee[x][y - 1].equals("?")){
                        changeDirection();

                    }
                    if (isiMazee[x][y - 1].equals("G")){
                        System.out.println("Digisu");

                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x, y - 1, maxBaris, maxKolom);
                }
            }


            if(x + 1 < maxBaris){
                if(isiMazee[x + 1][y].equals("T") || isiMazee[x + 1][y].equals(".")  || isiMazee[x + 1][y].equals("?") || isiMazee[x + 1][y].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("bawah");
                    if (isiMazee[x + 1][y].equals("?")){
                        changeDirection();

                    }
                    if (isiMazee[x + 1][y].equals("G")){
                        System.out.println("Digisu");

                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x + 1, y, maxBaris, maxKolom);
                }
            }

            if(y + 1 < maxKolom){
                if(isiMazee[x][y + 1].equals("T") || isiMazee[x][y + 1].equals(".")  || isiMazee[x][y + 1].equals("?") || isiMazee[x][y + 1].equals("G")) {
                    isiMazee[x][y] = "+";
                    System.out.println(""+x+", "+y);

                    arrRute.add("kanan");
                    if (isiMazee[x][y + 1].equals("?")){
                        changeDirection();
                    }
                    if(isiMazee[x][y + 1].equals("G")){
                        System.out.println("Ketemu Digisu");
                    }
                    lastX.add(x);
                    lastY.add(y);
                    return ruteToX(x, y + 1, maxBaris, maxKolom);
                }
            }



        }



        if(y - 1 >= 0){
            if(isiMazee[x][y - 1].equals("+") || isiMazee[x][y - 1].equals("#")) {
                isiMazee[x][y] = "x";
                int xLast = lastX.get(lastX.size() - 1);
                int yLast = lastY.get(lastY.size() - 1);
                lastX.remove(lastX.size() - 1);
                lastY.remove(lastY.size() - 1);
                arrRute.remove(arrRute.size() - 1);
                return ruteToX(xLast, yLast, maxBaris, maxKolom);
            }
        }

		/*---------------------------------------------------------------------------------------------*/

        arrRute.clear();
        return arrRute;
    }

    void changeDirection(){
        if(arahJam){
            arahMekah = true;
            arahJam = false;
        }else{
            arahMekah = false;
            arahJam = true;
        }
    }
}
