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
        initializeArrayInputAsArray();
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

        line = (rawFile.split("\n"));
        for (int i = 0; i < arrayHeight; i++) {
            list[i] = line[i].split(" ");
        }
        return list;
    }

    private void readFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(filePath));
        try {
            StringBuilder sb = new StringBuilder();

            //get first line for array size
            initializeArraySize(br.readLine());
            //get start position
            initializeStartLocation(br.readLine());
            //get end position
            initializeEndLocation(br.readLine());

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

    private void initializeArraySize(String s) {
        arrayWidth = Integer.parseInt(s.split(" ")[0]);
        arrayHeight = Integer.parseInt(s.split(" ")[1]);
    }

    private void initializeStartLocation(String s) {
        startLocationX = Integer.parseInt(s.split(" ")[0]);
        startLocationY = Integer.parseInt(s.split(" ")[1]);
    }

    private void initializeEndLocation(String s) {
        endLocationX = Integer.parseInt(s.split(" ")[0]);
        endLocationY = Integer.parseInt(s.split(" ")[1]);
    }

}
