import java.io.IOException;

/**
 * Created by abdulkhaled on 16/09/2017.
 */
public class MazeSolver {
    private int startLocationX;
    private int startLocationY;

    private int endLocationX;
    private int endLocationY;

    private int arrayHeight;
    private int arrayWidth;

    private Location[][] grid;
    private String[][] output;
    private String[][] output2;
    private Boolean endLocationFound = false;


    //constructor which expects file path
    MazeSolver(String textFilePath) throws IOException {

        RawFileReader rw = new RawFileReader(textFilePath);
        initialiseGrid(rw.initialiseArrayInputAsArray(), rw.getArrayHeight(), rw.getArrayWidth());

        startLocationX = rw.getStartLocationX();
        startLocationY = rw.getStartLocationY();

        endLocationX = rw.getEndLocationX();
        endLocationY = rw.getEndLocationY();

        solveMaze( startLocationY, startLocationX);
    }

    private void initialiseGrid(String[][] array, int arrayHeight, int arrayWidth) {
        this.arrayHeight = arrayHeight;
        this.arrayWidth = arrayWidth;
        output = array;
        grid = new Location[arrayHeight][arrayWidth];
        for(int i = 0; i < arrayHeight; i++) {
            for (int j =0; j<arrayWidth;j++){
                grid[i][j] = new Location(Integer.parseInt(array[i][j]));
            }
        }
    }

    //TODO find more efficient algorithm than a linear solution
    //TODO work forwards and backwards simultaneously
    /*
        recursive method, checks 4 directions from current location, if
        location is new and is traversable, set as new current location and
        recurse through method again till all reachable locations are checked
     */
    private void solveMaze(int currentLocationY, int currentLocationX) throws IOException {
        //set initial location as visited, to stop recursive function from returning to start location
        grid[currentLocationY][currentLocationX].setLocationVisited();

        // if current location is at end location
        if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
            endLocationFound = true;
            outputGridArray();
            output2 = output;
        } else if(!endLocationFound){ //stops further search if solution's found, however pending recursions continue
            //checks 4 directions from current location
            //check east
            checkDirection(currentLocationY, currentLocationX+1);
            //check south
            checkDirection(currentLocationY+1, currentLocationX);
            //check north
            checkDirection(currentLocationY-1, currentLocationX);
            //check west
            checkDirection(currentLocationY, currentLocationX-1);
        }
        output[currentLocationY][currentLocationX]= " "; //TODO alternative via passing output in recursion
    }

    /*
        generic method to check new location in any direction, if location is new and is traversable,
        then recurse through solveMaze method with new location
    */
    private void checkDirection( int newLocationY, int newLocationX) throws IOException {
       if(grid[newLocationY][newLocationX].isTraversablePassage() &&
               !endLocationFound) {
           output[newLocationY][newLocationX] = "X";
           solveMaze( newLocationY, newLocationX);
       }
    }

    private void outputGridArray(){
        for(int i = 0 ; i < arrayHeight;i++) {
            for(int j = 0;j < arrayWidth;j++) {
                if (i == startLocationY && j == startLocationX){
                    output[i][j] = "S";
                    System.out.print("S");
                } else if (i == endLocationY && j == endLocationX) {
                    output[i][j] = "E";
                    System.out.print("E");
                } else if(output[i][j].equals("1")) {
                    output[i][j] = "#";
                    System.out.print("#");
                } else if (output[i][j].equals("0")) {
                    output[i][j] = " ";
                    System.out.print(" ");
                } else {
                    System.out.print(output[i][j]);
                }
            }
            System.out.println("");
        }
    }

    public String[][] getOutput(){
        return output2;
    }
}
