import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Gokemon {
    private String name;
    private String type;
    private long stamina;

    public Gokemon(String name, String type, long stamina){
        this.name = name;
        this.type = type;
        this.stamina = stamina;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public long getStamina() {
        return stamina;
    }
}

class MyPocket {
    private int number;
    private Gokemon gokemon;

    public MyPocket(String name, String type, long stamina, int number){
        this.gokemon = new Gokemon(name, type, stamina);
        this.number = number;
    }

    public Gokemon getGokemon(){
        return gokemon;
    }

    public int getNumber() {
        return number;
    }
}

public class SDA15161T {

    String[][] mazeMap;
    ArrayList<Integer> wasX;
    ArrayList<Integer> wasY;
    ArrayList<String> endOfStory;
    ArrayList<MyPocket> myPockets;

    private static final HashMap<String, String> areaTrainers = new HashMap<>();
    private static final HashMap<String, String> compareBetterType = new HashMap<>();
    private static final HashMap<String, ArrayList<Gokemon>> gokemonPlaces = new HashMap<>();
    static int counterNumberGokemon = 0;

    public SDA15161T(){
        wasX = new ArrayList<>();
        wasY = new ArrayList<>();
        endOfStory = new ArrayList<>();
        myPockets = new ArrayList<>();

        //IF W then use G, etc
        compareBetterType.put("W", "G");
        compareBetterType.put("F", "W");
        compareBetterType.put("G", "F");
    }


    public static void main(String[] args) throws Exception{
        SDA15161T mainKlass = new SDA15161T();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String LogyInput = br.readLine();
        int positionLogyX = 0, positionLogyY = 0;

        ArrayList<String> arrList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(LogyInput, " ");
        int rowGhokemonWorld = Integer.parseInt(st.nextToken());
        int columnGhokemonWorld = Integer.parseInt(st.nextToken());
        int maxGhokemonInWorld = Integer.parseInt(st.nextToken());
        int trainerGhokemon = Integer.parseInt(st.nextToken());
        int maxGhokemonForLogy = Integer.parseInt(st.nextToken());
        mainKlass.mazeMap = new String[rowGhokemonWorld][columnGhokemonWorld];

        for(int i = 0;i < rowGhokemonWorld;i++){
            String inputRow = br.readLine();
            arrList.add(inputRow);
        }

        int isiBaris = 0;

        for(int x = 0;x < rowGhokemonWorld;x++){
            for(int y = 0;y < columnGhokemonWorld;y++){
                mainKlass.mazeMap[x][y] = arrList.get(isiBaris).substring(y, Math.min(y + 1, arrList.get(isiBaris).length()));
                if(arrList.get(isiBaris).substring(y, Math.min(y + 1, arrList.get(isiBaris).length())).equals("L")){
                    positionLogyX = x;
                    positionLogyY = y;
                }
            }
            isiBaris++;
        }

        mainKlass.wasX.add(positionLogyX);
        mainKlass.wasY.add(positionLogyY);

        for(int i = 0;i < maxGhokemonInWorld + trainerGhokemon;i++){
            String gokemonArrived = br.readLine();
            StringTokenizer strk = new StringTokenizer(gokemonArrived, " ");
            int positionX = Integer.parseInt(strk.nextToken());
            int positionY = Integer.parseInt(strk.nextToken());
            String trainerOrGhokemon = strk.nextToken();
            String potition = positionX+","+positionY;
            if(strk.hasMoreTokens()){
                String type = strk.nextToken();
                String stamina = strk.nextToken();
                ArrayList<Gokemon> gokemons = gokemonPlaces.get(potition);
                if(gokemons == null){
                    gokemons = new ArrayList<>();
                    gokemons.add(new Gokemon(trainerOrGhokemon, type, Integer.parseInt(stamina)));
                    gokemonPlaces.put(potition, gokemons);
                }else{
                    gokemons.add(new Gokemon(trainerOrGhokemon, type, Integer.parseInt(stamina)));
                    gokemonPlaces.put(potition, gokemons);
                }
            } else {

                areaTrainers.put(potition, trainerOrGhokemon);
            }

        }

        ArrayList<String> outputLogyInGhokemonWorld = mainKlass.clockRoutes(positionLogyX, positionLogyY, rowGhokemonWorld, columnGhokemonWorld, maxGhokemonForLogy);

        for(int u = 0;u < outputLogyInGhokemonWorld.size();u++){
            System.out.println(outputLogyInGhokemonWorld.get(u));
        }

        if(mainKlass.myPockets.isEmpty()){
            System.out.println("NO GHOKEMON LEFT");
        } else {
            System.out.println("GHOKEMON LEFT:");

            ArrayList<MyPocket> gokemonStay = mainKlass.myPockets;
            Collections.sort(gokemonStay, new Comparator<MyPocket>() {
                @Override
                public int compare(MyPocket o1, MyPocket o2) {
                    return o1.getNumber() - o2.getNumber();
                }
            });
            for(MyPocket g: gokemonStay){
                System.out.println(g.getGokemon().getName());
            }
        }
    }

    public ArrayList<String> clockRoutes(int x, int y, int maxRow, int maxColumn, int maxGhokemonForLogy){

        String currentMaze = mazeMap[x][y];

        if(currentMaze.equals("?")){
            mazeMap[x][y] = "+";
            return earthRoute(x, y, maxRow, maxColumn, maxGhokemonForLogy);
        }

        trainOrGetGokemon(x, y, maxGhokemonForLogy);

        //up
        if(x - 1 >= 0){
            if(mazeMap[x - 1][y].equals(".") || mazeMap[x - 1][y].equals("?") || mazeMap[x - 1][y].equals("G") || mazeMap[x - 1][y].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x - 1);
                wasY.add(y);
                return clockRoutes(x - 1, y, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        //right
        if(y + 1 < maxColumn){
            if(mazeMap[x][y + 1].equals(".") || mazeMap[x][y + 1].equals("?") || mazeMap[x][y + 1].equals("G") || mazeMap[x][y + 1].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x);
                wasY.add(y + 1);
                return clockRoutes(x, y + 1, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        //down
        if(x + 1 < maxRow){
            if(mazeMap[x + 1][y].equals(".") || mazeMap[x + 1][y].equals("?") || mazeMap[x + 1][y].equals("G") || mazeMap[x + 1][y].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x + 1);
                wasY.add(y);
                return clockRoutes(x + 1, y, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        //left
        if(y - 1 >= 0){
            if(mazeMap[x][y - 1].equals(".") || mazeMap[x][y - 1].equals("?") || mazeMap[x][y - 1].equals("G") || mazeMap[x][y - 1].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x);
                wasY.add(y - 1);
                return clockRoutes(x, y - 1, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        if(y - 1 >= 0){
            if(!mazeMap[x][y - 1].equals(".") || !mazeMap[x][y - 1].equals("?") || !mazeMap[x][y - 1].equals("G") || !mazeMap[x][y - 1].equals("T")) {
                mazeMap[x][y] = "+";
            }
        }

        wasX.remove(wasX.size() - 1);
        wasY.remove(wasY.size() - 1);
        if((wasX.size() - 1) < 0){
            return endOfStory;
        }
        return clockRoutes(wasX.get(wasX.size() - 1), wasY.get(wasY.size() - 1), maxRow, maxColumn, maxGhokemonForLogy);
    }

    public ArrayList<String> earthRoute(int x, int y, int maxRow, int maxColumn, int maxGhokemonForLogy){

        String currentMaze = mazeMap[x][y];
        if(currentMaze.equals("?")){
            mazeMap[x][y] = "+";
            return clockRoutes(x, y, maxRow, maxColumn, maxGhokemonForLogy);
        }

        trainOrGetGokemon(x, y, maxGhokemonForLogy);

        //up
        if(x - 1 >= 0){
            if(mazeMap[x - 1][y].equals(".") || mazeMap[x - 1][y].equals("?") || mazeMap[x - 1][y].equals("G") || mazeMap[x - 1][y].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x - 1);
                wasY.add(y);
                return earthRoute(x - 1, y, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        //left
        if(y - 1 >= 0){
            if(mazeMap[x][y - 1].equals(".") || mazeMap[x][y - 1].equals("?") || mazeMap[x][y - 1].equals("G") || mazeMap[x][y - 1].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x);
                wasY.add(y - 1);
                return earthRoute(x, y - 1, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        //down
        if(x + 1 < maxRow){
            if(mazeMap[x + 1][y].equals(".") || mazeMap[x + 1][y].equals("?") || mazeMap[x + 1][y].equals("G") || mazeMap[x + 1][y].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x + 1);
                wasY.add(y);
                return earthRoute(x + 1, y, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        //right
        if(y + 1 < maxColumn){
            if(mazeMap[x][y + 1].equals(".") || mazeMap[x][y + 1].equals("?") || mazeMap[x][y + 1].equals("G") || mazeMap[x][y + 1].equals("T")) {
                mazeMap[x][y] = "+";
                wasX.add(x);
                wasY.add(y + 1);
                return clockRoutes(x, y + 1, maxRow, maxColumn, maxGhokemonForLogy);
            }
        }

        if(y + 1 < maxColumn){
            if(!mazeMap[x][y + 1].equals(".") || !mazeMap[x][y + 1].equals("?") || !mazeMap[x][y + 1].equals("G") || !mazeMap[x][y + 1].equals("T")) {
                mazeMap[x][y] = "+";
            }
        }

        wasX.remove(wasX.size() - 1);
        wasY.remove(wasY.size() - 1);
        if((wasX.size() - 1) < 0){
            return endOfStory;
        }
        return earthRoute(wasX.get(wasX.size() - 1), wasY.get(wasY.size() - 1), maxRow, maxColumn, maxGhokemonForLogy);
    }

    private void trainOrGetGokemon(int x, int y, int maxGhokemonForLogy){
        String currentMaze = mazeMap[x][y];
        if(currentMaze.equals("G")){
            mazeMap[x][y] = "+";
            getGokemon(x, y, maxGhokemonForLogy);
        }

        if(currentMaze.equals("T")){
            mazeMap[x][y] = "+";
            training(x, y);
        }
    }

    public void getGokemon(int x, int y, int maxGhokemonForLogy){
        int currentX = x + 1;
        int currentY = y + 1;
        ArrayList<String> gokemonsTaken = new ArrayList<>();
        String gokemonNew = null;
        boolean onlyOneGokemon = true;
        ArrayList<MyPocket> myPocketSortable;

        ArrayList<Gokemon> gokemons = gokemonPlaces.get((currentX+","+currentY));
        if(!gokemons.isEmpty()){
            for(Gokemon g1: gokemons){
                myPocketSortable = myPockets;
                Collections.sort(myPocketSortable, new Comparator<MyPocket>() {
                    @Override
                    public int compare(MyPocket o1, MyPocket o2) {
                        long result = o1.getGokemon().getStamina() - o2.getGokemon().getStamina();
                        if(result == 0){
                            result = o1.getNumber() - o2.getNumber();
                        }
                        return (int)result;
                    }
                });
                MyPocket gokemon = new MyPocket(g1.getName(), g1.getType(), g1.getStamina(), counterNumberGokemon);
                if(myPockets.size() > maxGhokemonForLogy){
                    gokemonsTaken.remove(myPocketSortable.get(0).getGokemon().getName());
                    myPockets.remove(myPocketSortable.get(0));
                    myPockets.add(gokemon);
                    counterNumberGokemon+=1;
                    gokemonsTaken.add(g1.getName());
                }else{
                    myPockets.add(gokemon);
                    counterNumberGokemon+=1;
                    gokemonsTaken.add(g1.getName());
                    if(myPockets.size() > maxGhokemonForLogy){
                        myPocketSortable = myPockets;
                        Collections.sort(myPocketSortable, new Comparator<MyPocket>() {
                            @Override
                            public int compare(MyPocket o1, MyPocket o2) {
                                long result = o1.getGokemon().getStamina() - o2.getGokemon().getStamina();
                                if(result == 0){
                                    result = o1.getNumber() - o2.getNumber();
                                }
                                return (int)result;
                            }
                        });
                        gokemonsTaken.remove(myPocketSortable.get(0).getGokemon().getName());
                        myPockets.remove(myPocketSortable.get(0));
                    }

                }

            }
        }

        if(gokemonsTaken.isEmpty()){
            endOfStory.add("ALL STAY " + currentX + " " + currentY);
        } else {
            for(String s: gokemonsTaken){
                if(onlyOneGokemon){
                    gokemonNew = s;
                    onlyOneGokemon = false;
                } else {
                    gokemonNew = gokemonNew + ", " + s;
                }
            }
            endOfStory.add("FOLLOW " + currentX + " " + currentY + ": " + gokemonNew);
        }
    }

    public void training(int x, int y){
        int currentX = x + 1;
        int currentY = y + 1;
        if(myPockets.isEmpty()){
            endOfStory.add("NO BATTLE " + currentX + " " + currentY);
        }else{
            String posisi = currentX+","+currentY;
            String currentType = areaTrainers.get(posisi);
            String shouldUsed = compareBetterType.get(currentType);
            MyPocket g = null;
            ArrayList<MyPocket> tempPocket = myPockets;

            Collections.sort(tempPocket, new Comparator<MyPocket>() {
                @Override
                public int compare(MyPocket o1, MyPocket o2) {
                    long result = o2.getGokemon().getStamina() - o1.getGokemon().getStamina();
                    if(result == 0){
                        result = o2.getNumber() - o1.getNumber();
                    }
                    return (int)result;
                }
            });

            for(MyPocket g1: tempPocket){
                if(g1.getGokemon().getType().equals(shouldUsed)){
                    g = g1;
                }
            }

            if(g == null){
                for(MyPocket g1: tempPocket){
                    if(g1.getGokemon().getType().equals(currentType)){
                        g = g1;
                    }
                }
            }
            if(g == null){
                for(MyPocket g1: tempPocket){
                    if(g1.getGokemon().getType().equals(currentType)){
                        g = g1;
                    }
                }
            }

            if(g != null){
                endOfStory.add("BATTLE " + g.getGokemon().getName() + " " + currentX + " " + currentY);
                myPockets.remove(g);
            }
        }
    }
}

