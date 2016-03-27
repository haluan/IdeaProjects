import java.io.*;
import java.util.*;

class SolveMaze {

    static boolean arahTujuan = true;
    static Integer gokemonCounter = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String totalInput = br.readLine();
        StringTokenizer st = new StringTokenizer(totalInput, " ");
        int baris =  Integer.parseInt(st.nextToken())+2;
        int kolom = Integer.parseInt(st.nextToken())+2;
        int jumlahGokemon = Integer.parseInt(st.nextToken());
        int jumlahGokemonTrainer = Integer.parseInt(st.nextToken());
        int allowedGokemon = Integer.parseInt(st.nextToken());
        int startRow = 0;
        int startCol = 0;

        char[][] map = new char[baris][kolom];


        ArrayList<String> masukan = new ArrayList<String>();
        for(int i = 0;i < baris-2;i++){
            String inputRow = br.readLine();
            masukan.add(inputRow);
        }


        for(int i = 0; i < baris; i++){
            if(i == 0 || i == baris -1) {
                for (int j = 0; j < kolom; j++) {
                    map[i][j] = '#';
                }
            }else{
                String[] asolole = masukan.get(i-1).split("");
                for (int j = 0; j < kolom; j++) {
                    if(j == 0){
                        map[i][j] = '#';
                    }else if(j == kolom -1){
                        map[i][j]= '#';
                    }else {
                        char mapKomponen = asolole[j-1].charAt(0);
                        if(mapKomponen == 'L'){
                            startRow = i;
                            startCol = j;
                        }
                        map[i][j] = mapKomponen;
                    }

                }
            }
        }

        Maze maze = new Maze(map, allowedGokemon);

        for(int i = 0; i < jumlahGokemon; i ++){
            StringTokenizer gokemonAttr = new StringTokenizer(br.readLine(), " ");
//            System.out.println(gokemonAttr.nextToken()+gokemonAttr.nextToken()+gokemonAttr.nextToken());
            String indeksX = gokemonAttr.nextToken();
            String indeksY = gokemonAttr.nextToken();
            String nama = gokemonAttr.nextToken();
            String tipe = gokemonAttr.nextToken();
            String stamina = gokemonAttr.nextToken();
            Gokemon gokemon = new Gokemon();
            gokemon.setName(nama);
            gokemon.setStamina(Integer.parseInt(stamina));
            gokemon.setTipe(tipe);
            maze.setLokasiGokemon(indeksX+indeksY, gokemon);
        }

        for(int i = 0; i < jumlahGokemonTrainer; i++){
            StringTokenizer gokemonTrainer = new StringTokenizer(br.readLine(), " ");
            String indeksX = gokemonTrainer.nextToken();
            String indeksY = gokemonTrainer.nextToken();
            String tipe = gokemonTrainer.nextToken();
            maze.setBattleGokemon(indeksX+indeksY, tipe);
        }
//        System.out.println("Start: "+startRow+", "+startCol);
        solve(maze, startRow, startCol);
        maze.printLeft();
    }

    public static void solve(Maze maze, int startRow, int startCol) {
        explore(maze, startRow, startCol, arahTujuan, "Clock");
    }

    private static boolean explore(Maze maze, int row, int col, boolean arahTujuan, String status) {

        // unproductive path: wall or previously explored
        if (maze.isWall(row, col) || maze.isExplored(row, col)) {
            return false;
        } else if (row == 0 || row == maze.getHeight() - 1 || col == 0
                || col == maze.getWidth() - 1) {
            // exit has been found
            maze.mark(row, col);
            return true;
        } else {

            maze.setExplored(row, col, true);
            String value = maze.getValue(row, col);
            if (!value.equals("")) {
                System.out.println(value+"ARAH: "+status);
            }
            if(arahTujuan != SolveMaze.arahTujuan){
                arahTujuan = SolveMaze.arahTujuan;
            }
            if(!arahTujuan){
                System.out.println("REVERSE");
                return exploreReverse(maze, row, col, SolveMaze.arahTujuan, "Earth");
            }
            if (explore(maze, row - 1, col, SolveMaze.arahTujuan, "Clock")) { //up
                return false;
            }
            if (explore(maze, row, col + 1, SolveMaze.arahTujuan, "Clock")) {//right
                return false;
            }
            if (explore(maze, row + 1, col, SolveMaze.arahTujuan, "Clock")) {//down
                return false;
            }
            if (explore(maze, row, col - 1, SolveMaze.arahTujuan, "Clock")) { //left
                return false;
            }

        }
        return false;
    }

    private static boolean exploreReverse(Maze maze, int row, int col, boolean arahTujuan, String status) {

        // unproductive path: wall or previously explored
        if (maze.isWall(row, col) || maze.isExplored(row, col)) {
            return false;
        } else if (row == 0 || row == maze.getHeight() - 1 || col == 0
                || col == maze.getWidth() - 1) {
            // exit has been found
            maze.mark(row, col);
            return true;
        } else {

            maze.setExplored(row, col, true);
            String value = maze.getValue(row, col);
            if (!value.equals("")) {
                System.out.println(value+"ARAH: "+status);
            }
            if(arahTujuan != SolveMaze.arahTujuan){
                arahTujuan = SolveMaze.arahTujuan;
            }
            if(arahTujuan){
                return explore(maze, row, col, SolveMaze.arahTujuan, "Earth");
            }
            if (exploreReverse(maze, row - 1, col, SolveMaze.arahTujuan, "Earth")) { //up
                return true;
            }
            if (exploreReverse(maze, row, col - 1, SolveMaze.arahTujuan, "Earth")) { //left
                return true;
            }
            if (exploreReverse(maze, row + 1, col, SolveMaze.arahTujuan, "Earth")) {//down
                return true;
            }
            if (exploreReverse(maze, row, col + 1, SolveMaze.arahTujuan, "Earth")) {//right
                return true;
            }




        }
        return false;
    }
}
class Gokemon{
    private String name;
    private String tipe;
    private int stamina;
    private int gokemonCounter;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getGokemonCounter() {
        return gokemonCounter;
    }

    public void setGokemonCounter(int gokemonCounter) {
        this.gokemonCounter = gokemonCounter;
    }
}
class Maze{
    private char[][] squares;
    private boolean  arahJam = true, arahMekah = false;
    private boolean[][] explored;
    private  int lastIsRow, lastIsCol;
    private ArrayList<Gokemon> myPocket;
    private HashMap<String, String> battleGokemon = new HashMap<>();
    private HashMap<String, ArrayList<Gokemon>> lokasiGokemon = new HashMap<>();
    private int allowedGokemon;
    private HashMap<String, String> bobotTipeGokemon = new HashMap<>();

    public Maze(char[][] lines, int allowedGokemon) {
        this.allowedGokemon = allowedGokemon;

        squares = new char[lines.length][lines[0].length];
        explored = new boolean[lines.length][lines[0].length];

        squares = lines;
        bobotTipeGokemon.put("G", "F");
        bobotTipeGokemon.put("W", "G");
        bobotTipeGokemon.put("F", "W");


        lastIsRow = -1;
        lastIsCol = -1;
        myPocket = new ArrayList<>();
    }
    public char[][] getSquares(){
        return squares;
    }

    public int getHeight() {
        return squares.length;
    }

    public int getWidth() {
        return squares[0].length;
    }

    public boolean getArahJam(){
        return arahJam;
    }

    public boolean getArahMekah(){
        return arahMekah;
    }

    public void setArahJam(boolean arahJam){
        this.arahJam = arahJam;
    }

    public void setArahMekah(boolean arahMekah){
        this.arahMekah = arahMekah;
    }


    public boolean isExplored(int row, int col) {
        checkIndexes(row, col);
        lastIsRow = row;
        lastIsCol = col;
        return explored[row][col];
    }


    public String getValue(int row, int col){
        char isi = squares[row][col];
        String indeksKey = ""+row+col;
//        Collections.sort(myPocket, (o1,o2) -> o1.getStamina() - o2.getStamina());
        if(isi == 'G'){
            String nama_gokemon = "";
            ArrayList<Gokemon> kumpulanFieldGokemon = lokasiGokemon.get(indeksKey);
            for(Gokemon g : kumpulanFieldGokemon){
                addGokemon(g);
            }
            for(Gokemon g: kumpulanFieldGokemon){
                for(Gokemon g1: myPocket){
                    if(g1.getName().equals(g.getName())){
                        nama_gokemon += g1.getName()+" ";
                    }
                }
            }
            int counter = 0;
            String final_name="";
            for(String name: nama_gokemon.split(" ")){
                if(counter > 0 ){
                    final_name += ", "+name;
                }else{
                    final_name = name;
                }
                counter++;
            }
            if(final_name.equals("")){
                return "ALL STAY "+row+" "+col;
            }else{
                return "FOLLOW "+row+" "+col+": "+final_name;
            }
        }else if (isi == 'T'){
            String tipeLawan = battleGokemon.get(indeksKey);
            String lawanBagus = bobotTipeGokemon.get(tipeLawan);
//            System.out.println("Tipe lawan: "+tipeLawan+" Lawan Bagus: "+lawanBagus);
            Gokemon gosong = null;
            ArrayList<Gokemon> goTemp = myPocket;
            Collections.sort(goTemp, new Comparator<Gokemon>() {
                @Override
                public int compare(Gokemon o1, Gokemon o2) {
                    int result = o1.getStamina() - o2.getStamina();
                    if(result == 0){
                        result = o1.getGokemonCounter() - o2.getGokemonCounter();
                    }
                    return result;
                }
            });

            for(Gokemon g1: goTemp){
                if(g1.getTipe().equals(lawanBagus)){
                    gosong = g1;
                    return "BATTLE "+gosong.getName()+" "+row+" "+col;
                }
            }

            if(gosong == null){
                Collections.sort(goTemp, new Comparator<Gokemon>() {
                    @Override
                    public int compare(Gokemon o1, Gokemon o2) {
                        int result = o2.getStamina() - o1.getStamina();
                        if(result == 0){
                            result = o1.getGokemonCounter() - o2.getGokemonCounter();
                        }
                        return result;
                    }
                });

                for(Gokemon g1: goTemp){
                    if(g1.getTipe().equals(tipeLawan)){
                        gosong = g1;
                        return "BATTLE "+gosong.getName()+" "+row+" "+col;
                    }
                }
            }
            if(gosong == null){

                if(myPocket.size() > 0){
                    gosong = goTemp.get(0);
                    return "BATTLE "+gosong.getName()+" "+row+" "+col;
                }
            }
            return "NO BATTLE "+row+" "+col;
        }else if(isi == '?'){
            SolveMaze.arahTujuan = !SolveMaze.arahTujuan;
            squares[row][col] = '#';
            return "";
        }else{
            return "";
        }
    }

    public String addGokemon(Gokemon gokemon){
        if(myPocket.size() < allowedGokemon){
            gokemon.setGokemonCounter(SolveMaze.gokemonCounter);
            myPocket.add(gokemon);
            SolveMaze.gokemonCounter += 1;
            return  gokemon.getName();
        }else{
            ArrayList<Gokemon> gokeTemp = myPocket;
            Collections.sort(gokeTemp, new Comparator<Gokemon>() {
                @Override
                public int compare(Gokemon o1, Gokemon o2) {
                    int result = o1.getStamina() - o2.getStamina();
                    if(result == 0){
                        result = o1.getGokemonCounter() - o2.getGokemonCounter();
                    }
                    return result;
                }
            });
            if(gokemon.getStamina() >= gokeTemp.get(0).getStamina()){
                myPocket.remove(gokeTemp.get(0));
                gokemon.setGokemonCounter(SolveMaze.gokemonCounter);
                myPocket.add(gokemon);
                SolveMaze.gokemonCounter += 1;
                return  gokemon.getName();
            }
        }
        return "";
    }

    public boolean isChanges(int row, int col){
        char isi = squares[row][col];
        if(isi == '?'){
            return true;
        }else{
            return false;
        }
    }

    public boolean isWall(int row, int col) {
        checkIndexes(row, col);
        lastIsRow = row;
        lastIsCol = col;
        return squares[row][col] == '#';
    }

    public void mark(int row, int col) {
        checkIndexes(row, col);
        squares[row][col] = 'x';
    }


    public void setExplored(int row, int col, boolean value) {
        checkIndexes(row, col);
        explored[row][col] = value;
    }



    private void checkIndexes(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            throw new IllegalArgumentException("illegal indexes: (" +
                    row + ", " + col + ")");
        }
    }

    public HashMap<String, String> getBattleGokemon() {
        return battleGokemon;
    }

    public void setBattleGokemon(String indeks, String tipe) {
        battleGokemon.put(indeks, tipe);
    }

    public HashMap<String, ArrayList<Gokemon>> getLokasiGokemon() {
        return lokasiGokemon;
    }

    public void setLokasiGokemon(String indeks, Gokemon gokemon) {
        ArrayList<Gokemon> goko = lokasiGokemon.get(indeks);
        if( goko == null){
            ArrayList<Gokemon> arrGokemon = new ArrayList<>();
            arrGokemon.add(gokemon);
            lokasiGokemon.put(indeks, arrGokemon);
        }else{
            goko.add(gokemon);
            lokasiGokemon.put(indeks, goko);
        }
    }

    public void printLeft(){
        if(myPocket.isEmpty()){
            System.out.println("No GOKHEMON LEFT");
        }else{
            Collections.sort(myPocket, new Comparator<Gokemon>() {
                @Override
                public int compare(Gokemon o1, Gokemon o2) {
                    return o1.getGokemonCounter() - o2.getGokemonCounter();
                }
            });
            System.out.println("GOKHEMON LEFT");
            for(Gokemon g: myPocket){
                System.out.println(g.getName());
            }
        }

    }
}