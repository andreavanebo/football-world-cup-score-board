package worldcup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard {

    private List<Match> scoreboardList;

    public ScoreBoard() {
        this.scoreboardList = new ArrayList<>();
    }

    public Match getMatch(String nameOfTeam) {
        for (int i = 0; i < this.scoreboardList.size(); i++) {
            Match match = this.scoreboardList.get(i);
            if (match.getHomeTeam().toLowerCase().equals(nameOfTeam.toLowerCase())
                    || match.getAwayTeam().toLowerCase().equals(nameOfTeam.toLowerCase())) {
                return match;
            }
        }
        return null;
    }

    public Match getMatchOnIndex(int index) {
        if (index < this.scoreboardList.size()) {
            return this.scoreboardList.get(index);
        } else {
            return null;
        }
    }

    public void addMatch(Match match) {
        // check that there is no ongoing games with eighter of the teams
        if (getMatch(match.getHomeTeam()) == null && getMatch(match.getAwayTeam()) == null) {
            this.scoreboardList.add(match);
        } else {
            throw new IllegalArgumentException("Team already in the scoreboard.");
        }

    }

    public int getNumberOfMatches() {
        return this.scoreboardList.size();
    }

    public void stopMatch(String nameOfTeam) {
        Match matchToStop = getMatch(nameOfTeam);
        if (matchToStop != null) {
            this.scoreboardList.remove(matchToStop);
        } else {
            throw new IllegalArgumentException("It is no ongoing match with the given team name " + nameOfTeam);
        }
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

    public String getScoreBoardText() {
        ScoreBoard orderedScoreBoard = getScoreBoard();
        String resultString = "";
        for (int i = 0; i < getNumberOfMatches(); i++) {
            resultString += orderedScoreBoard.getMatchOnIndex(i).getMatchString() + "\n";
        }
        // remove last \n
        return resultString.substring(0, resultString.length() - 1);
    }

}
