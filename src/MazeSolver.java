import java.io.IOException;

/**
 * Created by abdulkhaled on 16/09/2017.
 */
public class MazeSolver {
    private int startLocationX;
    private int startLocationY;

    private int endLocationX;
    private int endLocationY;

    private Location[][] loc ;


    MazeSolver() throws IOException {
        RawFileReader rw = new RawFileReader("resources/medium_input.txt");
        initArray(rw.initializeArrayInputAsArray(), rw.getArrayHeight(), rw.getArrayWidth());

        startLocationX = rw.getStartLocationX();
        startLocationY = rw.getStartLocationY();

        endLocationX = rw.getEndLocationX();
        endLocationY = rw.getEndLocationY();

        solveMaze(startLocationY, startLocationX);
    }

    private void initArray(String[][] arr, int arrayHeight, int arrayWidth) {
        loc = new Location[arrayHeight][arrayWidth];
        for( int i=0 ; i <arrayHeight;i++) {
            for (int j =0; j<arrayWidth;j++){
                loc[i][j] = new Location(Integer.parseInt(arr[i][j]));
            }

        }
    }

    private void solveMaze(int currentLocationY, int currentLocationX) throws IOException {
        // if current spot is end location
        if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
            System.out.println("Route successfully found");

            //iterate till end location is found

            //if direction is 0 iterate, but also check all other positions
        }
        else {
            //check north
            checkDirection(currentLocationY-1, currentLocationX,currentLocationY, currentLocationX);
            //check south
            checkDirection(currentLocationY+1, currentLocationX,currentLocationY, currentLocationX);
            //check east
            checkDirection(currentLocationY, currentLocationX+1,currentLocationY, currentLocationX);
            //check west
            checkDirection(currentLocationY, currentLocationX-1,currentLocationY, currentLocationX);

        }
        //TODO when no solution is possible
//        System.out.println("No possible solution");
    }

    private void checkDirection(int newLocationY, int newLocationX, int currentLocationY, int currentLocationX) throws IOException {
       if(loc[newLocationY][newLocationX].isTraversablePassage()) {
           loc[newLocationY][newLocationX].setLocation();
           solveMaze(newLocationY, newLocationX);
       }
    }
}
