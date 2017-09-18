import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;

/**
 * Created by abdulkhaled on 15/09/2017.
 */
public class Main {

    final static String INPUT_TEXT_EXPECTED_RESULT = "[[#, #, #, #, #], [#,  , #,  , #], [#,  , #,  , #], [#,  ,  ,  , #], [#, #, #, #, #]]";
    final static String SMALL_TEXT_EXPECTED_RESULT = "[[#, #, #, #, #], [#,  ,  ,  , #], [#,  , #,  , #], [#,  , #,  , #], [#,  , #,  , #], [#, #, #, #, #]]";

    final static String SPARSE_MEDIUM_TEXT_PARTIAL_EXPECTED_RESULT = "[#,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  , #], [#,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  , #]";
    final static String MEDIUM_INPUT_TEXT_PARTIAL_EXPECTED_RESULT = "[#,  , #,  , #,  , #,  , #, #, #, #, #, #, #,  , #,  , #,  , #,  , #], [#,  , #,  , #,  , #,  ,  ,  ,  ,  ,  ,  , #,  , #,  , #,  , #,  , #],";
    final static String LARGE_INPUT_TEXT_PARTIAL_EXPECTED_RESULT = ", #,  ,  ,  , #,  ,  ,  ,  ,  , #,  ,  ,  ,  ,  ,  ,  , #,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  , #,  ,  ,  ,  ,  ,  ,  , #,  ,  ,  ,  ,  ,  ,  ,  ,  , #,  ,  , ";

    final static String INVALID_INPUT_EXPECTED_RESULT = "null";
    final static String SMALL_2_TEXT_EXPECTED_RESULT = "[[#, #, #, #, #], [#,  , #,  , #], [#,  ,  ,  , #], [#,  , #, #, #], [#,  ,  ,  , #], [#, #, #, #, #]]";

    public static void main(String[] args) throws IOException {
        //tests with assertions
        //provided scenarios
        assertArraysMatch(new MazeSolver("resources/input.txt").getOutput(), INPUT_TEXT_EXPECTED_RESULT, "input.txt");
        assertArraysMatch(new MazeSolver("resources/small.txt").getOutput(), SMALL_TEXT_EXPECTED_RESULT, "small.txt");
        assertArraysMatch(new MazeSolver("resources/sparse_medium.txt").getOutput(), SPARSE_MEDIUM_TEXT_PARTIAL_EXPECTED_RESULT, "sparse_medium.txt");

        assertArraysMatch(new MazeSolver("resources/medium_input.txt").getOutput(), MEDIUM_INPUT_TEXT_PARTIAL_EXPECTED_RESULT, "medium_input.txt");
        assertArraysMatch(new MazeSolver("resources/large_input.txt").getOutput(), LARGE_INPUT_TEXT_PARTIAL_EXPECTED_RESULT, "large_input.txt");

        //additional scenarios
        assertArraysMatch(new MazeSolver("resources/invalid_input.txt").getOutput(), INVALID_INPUT_EXPECTED_RESULT, "invalid_input.txt");
        assertArraysMatch(new MazeSolver("resources/small2.txt").getOutput(), SMALL_2_TEXT_EXPECTED_RESULT, "small2.txt");
    }

    //assertions done using contains matcher, as only partial expected result is used, as outputs are too large
    private static void assertArraysMatch(String [][] actualArray, String expectedArray, String textFile) throws IOException {
        assertTrue(format("actualArray for %s did not match expected result " + Arrays.deepToString(actualArray), textFile), Arrays.deepToString(actualArray).contains(expectedArray));
    }
}
