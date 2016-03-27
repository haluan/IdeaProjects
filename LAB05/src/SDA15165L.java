import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/***

 * @author Haluan

 */

public class SDA15165L {

    String[][] theLabyrinth;
    ArrayList<String> myPath = new ArrayList<String>();
    ArrayList<Integer> lastX = new ArrayList<Integer>();
    ArrayList<Integer> lastY = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception{
        SDA15165L environment = new SDA15165L();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean first = false;
        String input = reader.readLine();
        ArrayList<String> theMaze = new ArrayList<String>();
        String path = "";

        StringTokenizer token = new StringTokenizer(input, " ");

        int row = Integer.parseInt(token.nextToken());
        int column = Integer.parseInt(token.nextToken());
        environment.theLabyrinth = new String[row][column];

        for(int i = 0;i < row;i++){
            String inputRow = reader.readLine();
            theMaze.add(inputRow);
        }

        int counterStep = 0;
        int pointX = 0;
        int pointY = 0;

        for(int x = 0;x < row;x++){
            for(int y = 0;y < column;y++){
                environment.theLabyrinth[x][y] = theMaze.get(counterStep).substring(y, Math.min(y + 1, theMaze.get(counterStep).length()));
                if(theMaze.get(counterStep).substring(y, Math.min(y + 1, theMaze.get(counterStep).length())).equals("S")){
                    pointX = x;
                    pointY = y;
                }
            }
            counterStep++;
        }

        ArrayList<String> myRoute = environment.gotoX(pointX, pointY, row, column);

        if(myRoute.isEmpty()){
            System.out.println("tidak ketemu");
        } else {
            for(int i = 0;i < myRoute.size();i++){
                if(!first){
                    path = myRoute.get(i);
                    first = true;
                } else {
                    path = path + "-" + myRoute.get(i);
                }
            }
            System.out.println(path);
        }

    }

    public void addWhatMyFeetOn(int pointX, int pointY, String posisi){
        theLabyrinth[pointX][pointY] = ":))";
        myPath.add(posisi);
        lastX.add(pointX);
        lastY.add(pointY);
    }

    public ArrayList<String> oneStepBack(String stepBack, int pointX, int pointY, int maxRow, int maxColumn){
        boolean isIwasHere = stepBack.equals("x");
        boolean isBlocked = stepBack.equals("#");
        boolean isPassed = stepBack.equals(":))");
        if( isIwasHere || isBlocked || isPassed ) {
            theLabyrinth[pointX][pointY] = "x";
            int xLast = lastX.get(lastX.size() - 1);
            int yLast = lastY.get(lastY.size() - 1);
            lastX.remove(lastX.size() - 1);
            lastY.remove(lastY.size() - 1);
            myPath.remove(myPath.size() - 1);
            return gotoX(xLast, yLast, maxRow, maxColumn);
        }
        return null;
    }

    public ArrayList<String> gotoX(int pointX, int pointY, int maxRow, int maxColumn){

        if(theLabyrinth[pointX][pointY].equals("X")){
            return myPath;
        }


        if(pointX - 1 >= 0){
            if(theLabyrinth[pointX - 1][pointY].equals(".") || theLabyrinth[pointX - 1][pointY].equals("X")) {
                addWhatMyFeetOn(pointX, pointY, "atas");
                return gotoX(pointX - 1, pointY, maxRow, maxColumn);
            }
        }

        if(pointY + 1 < maxColumn){
            if(theLabyrinth[pointX][pointY + 1].equals(".") || theLabyrinth[pointX][pointY + 1].equals("X")) {
                addWhatMyFeetOn(pointX, pointY, "kanan");
                return gotoX(pointX, pointY + 1, maxRow, maxColumn);
            }
        }

        if(pointX + 1 < maxRow){
            if(theLabyrinth[pointX + 1][pointY].equals(".") || theLabyrinth[pointX + 1][pointY].equals("X")) {
                addWhatMyFeetOn(pointX, pointY, "bawah");
                return gotoX(pointX + 1, pointY, maxRow, maxColumn);
            }
        }

        if(pointY - 1 >= 0){
            if(theLabyrinth[pointX][pointY - 1].equals(".") || theLabyrinth[pointX][pointY - 1].equals("X")) {
                addWhatMyFeetOn(pointX, pointY, "kiri");
                return gotoX(pointX, pointY - 1, maxRow, maxColumn);
            }
        }

        if(pointY - 1 >= 0){
            String stepBack = theLabyrinth[pointX][pointY - 1];
            oneStepBack(stepBack, pointX, pointY, maxRow, maxColumn);
        }

        myPath.clear();
        return myPath;
    }
}
