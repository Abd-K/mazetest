import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by abdulkhaled on 15/09/2017.
 */
public class RawFileReader {
    private String rawFile;
    private String maze;

    private int arrayWidth;
    private int arrayHeight;

    private int startLocationX;
    private int startLocationY;

    private int endLocationX;

    private int endLocationY;

    public RawFileReader(String filePath) throws IOException {
        readFile(filePath);
        initializeArraySize();
        initializeStartLocation();
        initializeEndLocation();
        initializeMaze();

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

    public String[][] initializeArrayInputAsArray() throws IOException {
        String[][] list = new String[arrayWidth][arrayHeight];
        String[] line;
        line = (maze.split("\n"));
        for (int i = 0; i < arrayHeight; i++) {
            list[i] = line[i].split(" ");
        }
        return list;
    }

    private void readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String textInput = sb.toString();
            rawFile = textInput;
        } finally {
            br.close();
        }
    }

    private void initializeArraySize() {
        arrayWidth = Integer.parseInt(String.valueOf(rawFile.charAt(0)));
        arrayHeight = Integer.parseInt(String.valueOf(rawFile.charAt(2)));
    }

    private void initializeStartLocation() {
        startLocationX = Integer.parseInt(String.valueOf(rawFile.charAt(4)));
        startLocationY = Integer.parseInt(String.valueOf(rawFile.charAt(6)));
    }

    private void initializeEndLocation() {
        endLocationX = Integer.parseInt(String.valueOf(rawFile.charAt(8)));
        endLocationY = Integer.parseInt(String.valueOf(rawFile.charAt(10)));
    }

    private void initializeMaze() {
        maze = rawFile.substring(12);
    }


}
