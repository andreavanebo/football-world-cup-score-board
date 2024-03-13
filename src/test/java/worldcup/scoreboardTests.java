package worldcup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class scoreboardTests {

    private ScoreBoard scoreboard;
    private Match match1;
    private Match match2;

    @BeforeEach
    public void setUp() {
        scoreboard = new ScoreBoard();
        match1 = new Match("Spain", "Denmark");
        match2 = new Match("Portugal", "France");
        scoreboard.addMatch(match1);
        scoreboard.addMatch(match2);

    }

    @Test
    public void testStartingScore() {
        assertEquals(2, scoreboard.getNumberOfMatches(), "Matches is not added to the scoreboard correctly.");

        assertEquals(new ArrayList<>(List.of(0, 0)), scoreboard.getMatch("Spain").getScore(),
                "The initial score of a match is not 0-0.");
    }

    @Test
    public void testEmptyTeamNames() {
        assertEquals(2, scoreboard.getNumberOfMatches(), "Number of matches should be 2.");
        try {
            // home team name is forgotten
            Match match = new Match("", "Japan");
            fail("Exception should have been thrown when home team name is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        assertEquals(2, scoreboard.getNumberOfMatches(),
                "A match shouldn't be added to scoreboard without both team names.");

        try {
            // away team name is forgotten
            Match match = new Match("England", "");
            fail("Exception should have been thrown when away team name is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        assertEquals(2, scoreboard.getNumberOfMatches(),
                "A match shouldn't be added to scoreboard without both team names.");

        try {
            // both team names is forgotten
            Match match = new Match("", "");
            fail("Exception should have been thrown when both team names is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        assertEquals(2, scoreboard.getNumberOfMatches(),
                "A match shouldn't be added to scoreboard without both team names.");

    }

    @Test
    public void testAddMatchWithAlreadyPlayingTeam() {
        // it is allowed to create the matches
        Match match3 = new Match("Spain", "England");
        Match match4 = new Match("Norway", "Portugal");
        Match match5 = new Match("Portugal", "France");
        // but it should be allowed to add them to the scoreboard
        this.scoreboard.addMatch(match3);
        this.scoreboard.addMatch(match4);
        this.scoreboard.addMatch(match5);
        assertEquals(2, this.scoreboard.getNumberOfMatches(),
                "Matches which includes already playing teams shouldn't be added to the scoreboard.");
    }

    @Test
    public void testGetMatch() {
        // trying to get a match which actually exists
        assertEquals(match2, this.scoreboard.getMatch("Portugal"));
        assertEquals(match2, this.scoreboard.getMatch("France"));

        // trying to get a match which is not in the scoreboard
        assertEquals(null, this.scoreboard.getMatch("Norway"));
    }

    @Test
    public void testUpdateScore() {
        assertEquals(new ArrayList<>(List.of(0, 0)), scoreboard.getMatch("Spain").getScore(),
                "The initial score of a match is not 0-0.");
        scoreboard.getMatch("Spain").homeTeamScores();
        assertEquals(new ArrayList<>(List.of(1, 0)), scoreboard.getMatch("Spain").getScore(),
                "homeTeamScores() doesn't work as expected.");
        scoreboard.getMatch("Spain").homeTeamScores();
        assertEquals(new ArrayList<>(List.of(2, 0)), scoreboard.getMatch("Spain").getScore(),
                "homeTeamScores() doesn't work as expected.");
        scoreboard.getMatch("Spain").awayTeamScores();
        assertEquals(new ArrayList<>(List.of(2, 1)), scoreboard.getMatch("Spain").getScore(),
                "awayTeamScores() doesn't work as expected.");
    }

    @Test
    public void testStopMatch() {
        assertEquals(2, scoreboard.getNumberOfMatches(), "The number of matches should be 2 after the setup.");
        scoreboard.stopMatch("Spain");
        assertEquals(1, scoreboard.getNumberOfMatches(), "The match is not removed from the scoreboard.");
        scoreboard.stopMatch("France");
        assertEquals(0, scoreboard.getNumberOfMatches(), "The match is not removed from the scoreboard.");
    }

    @Test
    public void testGetScoreBoard() {
        // Spain scores against Denmark
        scoreboard.getMatch("Spain").homeTeamScores();
        // Denmark scores against Spain
        scoreboard.getMatch("Spain").awayTeamScores();
        // Denmark scores against Spain
        scoreboard.getMatch("Spain").awayTeamScores();

        // Portugal scores against France
        scoreboard.getMatch("Portugal").homeTeamScores();
        // France scores against Portugal
        scoreboard.getMatch("Portugal").awayTeamScores();

        /*
         * The scores at this point:
         * Spain (1) - Denmark (2)
         * Portugal (1) - France (1)
         */

        assertEquals(3, scoreboard.getMatch("Spain").getScoreSum(), "getScoreSum() doesn't return the correct sum.");
        assertEquals(2, scoreboard.getMatch("Portugal").getScoreSum(), "getScoreSum() doesn't return the correct sum.");

        assertEquals(2, scoreboard.getScoreBoard().getNumberOfMatches(), "The number of matches should be 2.");
        assertEquals(new ArrayList<>(List.of(match1, match2)),
                new ArrayList<>(List.of(scoreboard.getScoreBoard().getMatch("Spain"),
                        scoreboard.getScoreBoard().getMatch("Portugal"))),
                "The match with the highest total number of goals should be placed first.");

        // Portugal scores against France
        scoreboard.getMatch("Portugal").homeTeamScores();

        /*
         * The scores at this point:
         * Spain (1) - Denmark (2)
         * Portugal (2) - France (1)
         */

        // now it's a tie (both matches have 3 goals), and since Portugal-France is
        // newer it should be placed first in the list
        assertEquals(new ArrayList<>(List.of(match2, match1)),
                new ArrayList<>(List.of(scoreboard.getScoreBoard().getMatchOnIndex(0),
                        scoreboard.getScoreBoard().getMatchOnIndex(1))),
                "When it's a tie, the newest game should be placed first.");

        // Spain scores against Denmark
        scoreboard.getMatch("Spain").homeTeamScores();

        /*
         * The scores at this point:
         * Spain (2) - Denmark (2)
         * Portugal (2) - France (1)
         */
        assertEquals(new ArrayList<>(List.of(match1, match2)),
                new ArrayList<>(List.of(scoreboard.getScoreBoard().getMatchOnIndex(0),
                        scoreboard.getScoreBoard().getMatchOnIndex(1))),
                "The match with the highest total number of goals should be placed first.");
        Match match3 = new Match("Netherlands", "Poland");
        scoreboard.addMatch(match3);

        /*
         * The scores at this point:
         * Spain (2) - Denmark (2)
         * Portugal (2) - France (1)
         * Netherlands (0) - Poland (0)
         */
        assertEquals(new ArrayList<>(List.of(match1, match2, match3)),
                new ArrayList<>(List.of(scoreboard.getScoreBoard().getMatchOnIndex(0),
                        scoreboard.getScoreBoard().getMatchOnIndex(1),
                        scoreboard.getScoreBoard().getMatchOnIndex(2))),
                "The match with the highest total number of goals should be placed first.");
    }

}