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

    private Boolean endLocationFound = false;
    private Location[][] grid ;

    //constructor which expects file path
    MazeSolver(String textFilePath) throws IOException {
        RawFileReader rw = new RawFileReader(textFilePath);
        initialiseGrid(rw.initialiseArrayInputAsArray(), rw.getArrayHeight(), rw.getArrayWidth());

        startLocationX = rw.getStartLocationX();
        startLocationY = rw.getStartLocationY();

        endLocationX = rw.getEndLocationX();
        endLocationY = rw.getEndLocationY();

        solveMaze(startLocationY, startLocationX);
        if(endLocationFound) {
            outputGrid();
        }
    }

    private void initialiseGrid(String[][] array, int arrayHeight, int arrayWidth) {
        this.arrayHeight = arrayHeight;
        this.arrayWidth = arrayWidth;
        grid = new Location[this.arrayHeight][arrayWidth];
        for(int i = 0; i < this.arrayHeight; i++) {
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
    private Boolean solveMaze(Location[][] grid, String[][] output, int currentLocationY, int currentLocationX) throws IOException {
        //set initial location as visited, to stop recursive function from returning to start location
        grid[currentLocationY][currentLocationX].setLocationVisited();

        // if current location is at end location
        if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
            endLocationFound = true;
            return endLocationFound;
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
        return endLocationFound;
    }

    /*
        generic method to check new location in any direction, if location is new and is traversable,
        then recurse through solveMaze method with new location
    */
    private void checkDirection(int newLocationY, int newLocationX) throws IOException {
       if(grid[newLocationY][newLocationX].isTraversablePassage()) {
           grid[newLocationY][newLocationX].setLocationVisited();
           solveMaze(newLocationY, newLocationX);
       }
    }

    //outputs grid as a system output
    private void outputGrid() {
        for(int i = 0 ; i < arrayHeight;i++) {
            for(int j = 0;j < arrayWidth;j++) {
                if (i == startLocationY && j == startLocationX) {
                    System.out.print("S");
                } else if (i == endLocationY && j == endLocationX) {
                    System.out.print("E");
                } else if (grid[i][j].getLocationValue() == 1) {
                    System.out.print("#");
                } else if (grid[i][j].getVisitedLocation() == true) {
                    System.out.print("X");
                } else if (grid[i][j].getLocationValue() == 0) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
