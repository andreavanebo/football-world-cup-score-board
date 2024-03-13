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
            if (match.getHomeTeam().equals(nameOfTeam) || match.getAwayTeam().equals(nameOfTeam)) {
                return match;
            }
        }
        return null;
    }

    public Match getMatchOnIndex(int index){
        if(index<this.scoreboardList.size()){
            return this.scoreboardList.get(index);
        } else{
            return null;
        }
    }

    public void addMatch(Match match) {
        // check that there is no ongoing games with eighter of the teams
        if (getMatch(match.getHomeTeam()) == null && getMatch(match.getHomeTeam()) == null) {
            try {
                this.scoreboardList.add(match);
            } catch (IllegalArgumentException e) {
                // the match need both a home team name and a away team name.
                // TODO: add information text in application
            }
        } else {
            // TODO: add information text in application
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

    public void stopMatch(String nameOfTeam) {
        Match matchToStop = getMatch(nameOfTeam);
        if (matchToStop != null) {
            this.scoreboardList.remove(matchToStop);
        } else {
            // TODO: add information text in application
        }
    }

}
