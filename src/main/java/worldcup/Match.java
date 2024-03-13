package worldcup;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamPoints;
    private int awayTeamPoints;

    public Match(String homeTeam, String awayTeam) {
        if (homeTeam != "" && awayTeam != "") {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeTeamPoints = 0;
            this.awayTeamPoints = 0;
        } else {
            throw new IllegalArgumentException("Both home team and away team need to have a name.");
        }

    }

    public String getHomeTeam() {
        return this.homeTeam;
    }

    public String getAwayTeam() {
        return this.awayTeam;
    }

    public int getHomeTeamPoints() {
        return this.homeTeamPoints;
    }

    public int getAwayTeamPoints() {
        return this.awayTeamPoints;
    }

    public ArrayList getScore() {
        return new ArrayList<>(List.of(this.homeTeamPoints, this.awayTeamPoints));
    }

    public int getScoreSum() {
        return this.homeTeamPoints + this.awayTeamPoints;
    }

    public String getMatchString() {
        return this.homeTeam + "(" + this.homeTeamPoints + ") - " + this.awayTeam + "(" + this.awayTeamPoints + ")";
    }

    public void homeTeamScores() {
        this.homeTeamPoints += 1;
    }

    public void awayTeamScores() {
        this.awayTeamPoints += 1;
    }

}
