package worldcup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard {

    private List<Match> scoreboardList;
    private int scoreboardId;
    private int idCounter = 1;

    public ScoreBoard() {
        this.scoreboardId = idCounter;
        idCounter += 1;
        this.scoreboardList = new ArrayList<>();
    }

    public Match getMatch(int numberInList) {
        return this.scoreboardList.get(numberInList - 1);
    }

    public void addMatch(Match match) {
        try {
            this.scoreboardList.add(match);
        } catch (IllegalArgumentException e) {
            //the match need both a home team name and a away team name.
        }

    }

    public int getNumberOfMatches() {
        return this.scoreboardList.size();
    }

    public ScoreBoard getScoreBoard() {
        ScoreBoard result = new ScoreBoard();
        for (int i = 0; i < this.scoreboardList.size(); i++) {
            result.addMatch(this.scoreboardList.get(i));
        }
        Collections.reverse(result.scoreboardList);
        Collections.sort(result.scoreboardList, new MatchComparator());

        return result;
    }

    // let index be in the "normal" way of counting (starting in 1), not in
    // data-index (starting in 0), to make it more user-friendly
    public void stopMatch(int index) {
        if (index >= 1 && index <= this.scoreboardList.size()) {
            this.scoreboardList.remove(index - 1);
        } else {
            System.out.println("There isn't any match on the given index: " + index);
        }
    }

}
