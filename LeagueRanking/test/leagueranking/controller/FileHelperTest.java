/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package leagueranking.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.assertArrayEquals;

/**
 *
 * @author danie
 */
public class FileHelperTest {
    /**
     * Test of readFile method, of class FileHelper.
     */
    @Test
    public void testReadFileNormalCase() {
        String fileName = "test/resources/testFile.txt";
        Map<String, Integer> expResult = Map.of(
            "Atlas", 3,
            "FC Barcelona", 1,
            "Tarantulas", 3,
            "Real Madrid", 1,
            "Lions", 4,
            "Grouches", 0,
            "Chivas", 1,
            "Snakes", 0,
            "FC Awesome", 0
        );
        // Actual result from the method
        Map<String, Integer> result = FileHelper.readFile(fileName);
        // Compare sorted lists
        assertEquals("The Map should be equals as the expResult",expResult, result);
    }
    
    @Test
    public void testReadFileIOException() {
        // File that does not exist
        String fileName = "invalid/path/to/file.txt";

        // Run the method
        Map<String, Integer> result = FileHelper.readFile(fileName);

        // Expected result is an empty map
        Map<String, Integer> expected = Collections.emptyMap();
        assertEquals("Expected an empty result map due to IOException.", expected, result);
    }
    
    @Test
    public void testReadFileNumberFormatException() {
        // Create a file with invalid number formats
        Path path = Paths.get("test/resources/invalidNumberFormat.txt");
        try {
            Files.write(path, "TeamA not_a_number, TeamB 2".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Run the method
        Map<String, Integer> result = FileHelper.readFile("test/resources/invalidNumberFormat.txt");

        // Expected result is an empty map
        Map<String, Integer> expected = Collections.emptyMap();
        assertEquals("Expected an empty result map due to NumberFormatException.", expected, result);
    }
    
    @Test
    public void testReadFileEmptyFile() {
        // Run the method
        Map<String, Integer> result = FileHelper.readFile("test/resources/emptyFile.txt");

        // Expected result is an empty map
        Map<String, Integer> expected = Collections.emptyMap();
        assertEquals("Expected an empty result map for an empty file.", expected, result);
    }
    
    @Test
    public void testExtractTeamAndScoreNormalCase() {
        String input = "Team A 3";
        String[] expected = {"Team A", "3"};
        String[] actual = FileHelper.extractTeamAndScore(input);
        assertArrayEquals("The team name and score should be extracted correctly.", expected, actual);
    }

    @Test
    public void testExtractTeamAndScoreSingleWord() {
        String input = "Team 5";
        String[] expected = {"Team", "5"};
        String[] actual = FileHelper.extractTeamAndScore(input);
        assertArrayEquals("The team name and score should be extracted correctly even with a single word.", expected, actual);
    }

    @Test
    public void testExtractTeamAndScoreMultipleSpaces() {
        String input = "   Team With Spaces 12";
        String[] expected = {"   Team With Spaces", "12"};
        String[] actual = FileHelper.extractTeamAndScore(input);
        assertArrayEquals("The team name and score should be extracted correctly with multiple spaces.", expected, actual);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testExtractTeamAndScoreNoSpace() {
        // This test expects an exception because there's no space in the input
        String input = "TeamWithoutScore";
        FileHelper.extractTeamAndScore(input);
    }
    
    @Test
    public void sortByValue() {
        String input = "   Team With Spaces 12";
        String[] expected = {"   Team With Spaces", "12"};
        String[] actual = FileHelper.extractTeamAndScore(input);
        assertArrayEquals("The team name and score should be extracted correctly with multiple spaces.", expected, actual);
    }
    
}
