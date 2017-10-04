import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by abdulkhaled on 15/09/2017.
 */
public class RawFileReader {
    private String rawFile;

    private int arrayWidth;
    private int arrayHeight;

    private int startLocationX;
    private int startLocationY;

    private int endLocationX;
    private int endLocationY;

    public RawFileReader(String filePath) throws IOException {
        readFile(filePath);
        initialiseArrayInputAsArray();
    }

    /*
        return the maze as a multidimensional array of strings with values of 0 or 1
     */
    public String[][] initialiseArrayInputAsArray() throws IOException {
        String[][] list = new String[arrayHeight][arrayWidth];
        String[] line;

        line = (rawFile.split("\n"));
        for (int i = 0; i < arrayHeight; i++) {
            list[i] = line[i].split("\\s+");
        }
        return list;
    }

    /*
        fetches the values from first 3 lines, and sets them to appropriate int values
        then returns the rest of the text file as a String representing the maze
    */
    private void readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();

            //get first line for array size
            initialiseArraySize(br.readLine());
            //get start position
            initialiseStartLocation(br.readLine());
            //get end position
            initialiseEndLocation(br.readLine());

            //read rest of text file
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            rawFile = sb.toString();
        } finally {
            br.close();
        }
    }

    private void initialiseArraySize(String s) {
        arrayWidth = Integer.parseInt(s.split(" ")[0]);
        arrayHeight = Integer.parseInt(s.split(" ")[1]);
    }

    private void initialiseStartLocation(String s) {
        startLocationX = Integer.parseInt(s.split(" ")[0]);
        startLocationY = Integer.parseInt(s.split(" ")[1]);
    }

    private void initialiseEndLocation(String s) {
        endLocationX = Integer.parseInt(s.split(" ")[0]);
        endLocationY = Integer.parseInt(s.split(" ")[1]);
    }

    public int getArrayHeight() {
        return arrayHeight;
    }

    public int getArrayWidth() {
        return arrayWidth;
    }

    public int getEndLocationY() {
        return endLocationY;
    }

    public int getEndLocationX() {
        return endLocationX;
    }

    public int getStartLocationY() {
        return startLocationY;
    }

    public int getStartLocationX() {
        return startLocationX;
    }
}
