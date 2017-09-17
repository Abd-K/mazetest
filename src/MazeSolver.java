import java.io.IOException;

/**
 * Created by abdulkhaled on 16/09/2017.
 */
public class MazeSolver {
    private int startLocationX;
    private int startLocationY;

    private int endLocationX;
    private int endLocationY;

    private int previousLocationX;
    private int previousLocationY;

    private String[][] mazeArray;


    MazeSolver() throws IOException {
        RawFileReader rw = new RawFileReader("resources/input.txt");
        mazeArray = rw.initializeArrayInputAsArray();

        startLocationX = rw.getStartLocationX();
        startLocationY = rw.getStartLocationY();

        previousLocationX = startLocationX;
        previousLocationY = startLocationY;

        endLocationX = rw.getEndLocationX();
        endLocationY = rw.getEndLocationY();

        solveMaze(startLocationX, startLocationY);
    }

    private void solveMaze(int currentLocationX, int currentLocationY) throws IOException {
        // if current spot is not end location
        if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
            //TODO if new locaiton is end
            System.out.println("at end");
            //iterate till we get to end location
            //TODO if not possible to get to end location
            //
        } else {
            if (checkNorth(currentLocationX, currentLocationY)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationX, currentLocationY -1);
            }  else if (checkSouth(currentLocationX, currentLocationY +1)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationX, currentLocationY +1);
            } else if (checkEast(currentLocationX -1, currentLocationY)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationX -1, currentLocationY);
            } else if (checkWest(currentLocationX +1, currentLocationY)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationX +1, currentLocationY);
            }
        }

    }

    private Boolean isNewPassage(int locationX, int locationY){
        return locationX != previousLocationX && locationY != previousLocationY;
    }

    private Boolean checkNorth(int locationX, int locationY) {
        return mazeArray[locationX][locationY-1].equals("0") &&
                isNewPassage(locationX, locationY-1);
    }

    private Boolean checkSouth(int locationX, int locationY) {
        return mazeArray[locationX][locationY+1].equals("0") &&
                isNewPassage(locationX, locationY+1);
    }

    private Boolean checkEast(int locationX, int locationY) {
        return mazeArray[locationX-1][locationY].equals("0") &&
                isNewPassage(locationX-1, locationY);
    }

    private Boolean checkWest(int locationX, int locationY) {
        return mazeArray[locationX+1][locationY].equals("0") &&
                isNewPassage(locationX+1, locationY);
    }
}
