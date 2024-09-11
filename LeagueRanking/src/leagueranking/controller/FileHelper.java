/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package leagueranking.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import leagueranking.model.Team;

/**
 * A utility class for handling file operations related to league rankings.
 * This class provides methods to read match results from a file, compute team scores,
 * sort teams by their scores, and print the sorted team list with ranks.
 */
public class FileHelper {
    
    /**
     * Reads a file containing match results and computes the scores for each 
     * team based on the match outcomes.
     * The file should contain lines with match results in the following format:
     * teamAName scoreA, teamBName scoreB
     * Each line represents a match between team A and team B. The method updates 
     * the scores for each team based on the following rules:
     *   1. The winning team receives 3 points.
     *   2. If the match is a draw, both teams receive 1 point.
    */
    public static Map<String, Integer> readFile(String fileName) {
        HashMap<String, Integer> scores = new HashMap<>();
        try {
            Path path = Path.of(fileName);
            List<String> lines = Files.readAllLines(path);

            for(String line: lines) {
                String[] team = line.split(",\\s*", 2);
                String[] teamA = extractTeamAndScore(team[0]);
                String[] teamB = extractTeamAndScore(team[1]);

                int scoreA = Integer.parseInt(teamA[1]);
                int scoreB = Integer.parseInt(teamB[1]);

                if (scoreA > scoreB) {
                    scores.put(teamA[0], scores.getOrDefault(teamA[0], 0) + 3);
                    scores.put(teamB[0], scores.getOrDefault(teamB[0], 0));
                } else if (scoreB > scoreA) {
                    scores.put(teamB[0], scores.getOrDefault(teamB[0], 0) + 3);
                    scores.put(teamA[0], scores.getOrDefault(teamA[0], 0));
                } else {
                    scores.put(teamA[0], scores.getOrDefault(teamA[0], 0) + 1);
                    scores.put(teamB[0], scores.getOrDefault(teamB[0], 0) + 1);
                }
            }
            System.out.println(scores);
        } catch (IOException ex) {
            System.err.println("Error reading file: " + fileName);
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            System.err.println("Invalid number format in file: " + fileName);
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Unexpected error occurred");
            ex.printStackTrace();
        }
        return scores;
    }
    
    /**
     * Extracts the team name and score from a given string. The string is expected 
     * to contain a team name followed by a score, separated by the last space in 
     * the string. The method identifies the last space character in the input string, 
     * which is assumed to separate the team name from the score. It then splits the 
     * string into two parts: the team name and the score.
    */
    public static String[] extractTeamAndScore(String teamPart) {
        // Find the last space in the string which separates the score from the team name
        int lastSpaceIndex = teamPart.lastIndexOf(' ');
        
        // Split into team name and score
        String teamName = teamPart.substring(0, lastSpaceIndex);
        String score = teamPart.substring(lastSpaceIndex + 1);
        
        return new String[]{teamName, score};
    }
    
    /**
     * Sorts a map of team names and their associated integer values in descending 
     * order by value(points), and in ascending order by key (team name) for entries 
     * with the same value. Then, converts the sorted map entries into a list of 
     * {@link Team} objects with assigned ranks.
     * The method performs the following steps:
     *   1. Sorts the map entries first by their values in descending order.
     *   2. If two entries have the same value, sorts them by their keys in ascending order.
     *   3. Assigns position to the entries, ensuring that entries with the same 
     *      value receive the same position.
     *   4. Creates {@link Team} objects from the sorted entries and assigns the 
     *      computed position to each team.
    */
    public static List<Team> sortByValue(Map<String, Integer> hm) {
        Comparator<Map.Entry<String, Integer>> valDescThenKeyDesc = 
                Map.Entry.<String, Integer>comparingByValue().reversed()
                        .thenComparing(Map.Entry.<String, Integer>comparingByKey());
        
        List<Map.Entry<String, Integer>> sortedEntries = hm.entrySet()
                .stream()
                .sorted(valDescThenKeyDesc)
                .collect(Collectors.toList());
        
        List<Team> teams = new ArrayList<>();
        if (!sortedEntries.isEmpty()) {
            int currentPosition = 0;
            int previousValue = -1;

            for (Map.Entry<String, Integer> entry : sortedEntries) {
                if (entry.getValue() == previousValue) {
                    // Same value as the previous one, keep the same position
                    teams.add(new Team(entry.getKey(), entry.getValue(), currentPosition));
                } else {
                    // New value, update position
                    teams.add(new Team(entry.getKey(), entry.getValue(), ++currentPosition));
                }
                previousValue = entry.getValue();
            }
        }
        return teams;
    }
    
    /**
     * Prints a list of teams with their ranks, names, and points to the console.
     * This method iterates over each team in the provided list and prints the team's position,
     * name, and points in a formatted string. The list is expected to be sorted by position.
    */
    public static void printTeamsWithRanks(List<Team> teams) {
        for (Team team : teams) {
            System.out.println(team.getPosition() + ". " + team.getName() + ", " + team.getPoints() + " pts");
        }
    }    
}
    