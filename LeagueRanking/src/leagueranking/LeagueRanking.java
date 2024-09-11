
package leagueranking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import leagueranking.controller.FileHelper;
import static leagueranking.controller.FileHelper.printTeamsWithRanks;
import static leagueranking.controller.FileHelper.sortByValue;
import leagueranking.model.Team;

public class LeagueRanking {

    public static void main(String[] args) {
        Map<String, Integer> test = new HashMap<>();
        test = FileHelper.readFile("test/resources/invalidNumberFormat.");
        List<Team> sortedTeams = sortByValue(test);
        printTeamsWithRanks(sortedTeams);

    }
    
}
