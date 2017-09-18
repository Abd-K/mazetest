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
    private String[][] initialOutput;
    private Boolean endLocationFound = false;


    //constructor which expects file path
    MazeSolver(String textFilePath) throws IOException {

        RawFileReader rw = new RawFileReader(textFilePath);
        initialiseGrid(rw.initialiseArrayInputAsArray(), rw.getArrayHeight(), rw.getArrayWidth());

        startLocationX = rw.getStartLocationX();
        startLocationY = rw.getStartLocationY();

        endLocationX = rw.getEndLocationX();
        endLocationY = rw.getEndLocationY();

        solveMaze(initialOutput, startLocationY, startLocationX);
//        solveMaze(initialGrid , startLocationY, startLocationX);
//        if(endLocationFound) {
//            outputGridArray();
//            outputGrid();
//        }
    }

    private void initialiseGrid(String[][] array, int arrayHeight, int arrayWidth) {
        this.arrayHeight = arrayHeight;
        this.arrayWidth = arrayWidth;
        initialOutput = array;
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
    private Boolean solveMaze(String[][] output, int currentLocationY, int currentLocationX) throws IOException {
//    private Location[][] solveMaze(Location[][] grid, int currentLocationY, int currentLocationX) throws IOException {
        //set initial location as visited, to stop recursive function from returning to start location
        grid[currentLocationY][currentLocationX].setLocationVisited();

        // if current location is at end location
        if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
            endLocationFound = true;
            outputGridArray(output);
            return endLocationFound;
        } else if(!endLocationFound){ //stops further search if solution's found, however pending recursions continue
            //checks 4 directions from current location
            //check east
            checkDirection(output, currentLocationY, currentLocationX+1);
            //check south
            checkDirection(output, currentLocationY+1, currentLocationX);
            //check north
            checkDirection(output, currentLocationY-1, currentLocationX);
            //check west
            checkDirection(output, currentLocationY, currentLocationX-1);
        }
        output[currentLocationY][currentLocationX]= " "; //TODO alternative via
        return endLocationFound;
    }

    /*
        generic method to check new location in any direction, if location is new and is traversable,
        then recurse through solveMaze method with new location
    */
    private void checkDirection(String[][] output, int newLocationY, int newLocationX) throws IOException {
       if(grid[newLocationY][newLocationX].isTraversablePassage()) {
           grid[newLocationY][newLocationX].setLocationVisited();
           output[newLocationY][newLocationX] = "X";
           solveMaze(output, newLocationY, newLocationX);
       }
    }

    private void outputGridArray(String[][] output){
        for(int i = 0 ; i < arrayHeight;i++) {
            for(int j = 0;j < arrayWidth;j++) {
                if (i == startLocationY && j == startLocationX){
                    System.out.print("S");
                } else if (i == endLocationY && j == endLocationX) {
                    System.out.print("E");
                } else if(output[i][j].equals("1")) {
                    System.out.print("#");
                } else if (output[i][j].equals("0")) {
                    System.out.print(" ");
                } else {
                    System.out.print(output[i][j]);
                }
            }
            System.out.println("");
        }
    }
}
