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

        solveMaze(startLocationY, startLocationX);
    }

    private void solveMaze(int currentLocationY, int currentLocationX) throws IOException {
        // if current spot is not end location
//        System.out.println(Arrays.deepToString(mazeArray));
//        System.out.println(mazeArray[2][1]);
        if (currentLocationX == endLocationX && currentLocationY == endLocationY) {
            //TODO if new locaiton is end
            System.out.println("Route successfully found");
            //iterate till we get to end location
            //
        } else if (checkNorth(currentLocationY, currentLocationX)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationY - 1 , currentLocationX);
            }  else if (checkSouth(currentLocationY, currentLocationX)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationY + 1, currentLocationX);
            } else if (checkEast(currentLocationY, currentLocationX)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationY , currentLocationX - 1);
            } else if (checkWest(currentLocationY, currentLocationX)) {
                previousLocationX = currentLocationX;
                previousLocationY = currentLocationY;
                solveMaze(currentLocationY , currentLocationX + 1);
            } else {
                System.out.println("No possible solution");
        }

    }

    private Boolean isNewPassage(int locationY, int locationX){
        return locationY != previousLocationY || locationX != previousLocationX;
    }

    private Boolean checkNorth(int locationY, int locationX) {
        return mazeArray[locationY - 1][locationX].equals("0") &&
                isNewPassage(locationY - 1, locationX);
    }

    private Boolean checkSouth(int locationY, int locationX) {
        return mazeArray[locationY + 1][locationX].equals("0") &&
                isNewPassage(locationY + 1, locationX);
    }

    private Boolean checkEast(int locationY, int locationX) {
        return mazeArray[locationY][locationX-1].equals("0") &&
                isNewPassage(locationY, locationX-1);
    }

    private Boolean checkWest(int locationY, int locationX) {
        return mazeArray[locationY][locationX+1].equals("0") &&
                isNewPassage(locationY, locationX+1);
    }
}
