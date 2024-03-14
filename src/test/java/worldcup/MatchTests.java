package worldcup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchTests {

    private Match match1;

    @BeforeEach
    public void setUp() {
        match1 = new Match("Spain", "Denmark");

    }

    @Test
    public void testMatchConstructor() {
        Match match = new Match("England", "Spain");
        assertEquals("England", match.getHomeTeam(), "England should be the home team");
        assertEquals("Spain", match.getAwayTeam(), "Spain should be the away team.");

    }

    @Test
    public void testMatchConstructorIdenticalTeamNames() {
        try {
            // home team name is the same as away team name
            Match match = new Match("Japan", "Japan");
            fail("Exception should have been thrown when home team name is the same as away team name.");
        } catch (IllegalArgumentException e) {
            assertEquals("Home team name and away team name can not be the same.", e.getMessage(),
                    "The error message is not correct.");
        }
    }

    @Test
    public void testMatchConstructorEmptyTeamNames() {
        try {
            // home team name is forgotten
            Match match = new Match("", "Japan");
            fail("Exception should have been thrown when home team name is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        try {
            // away team name is forgotten
            Match match = new Match("England", "");
            fail("Exception should have been thrown when away team name is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        try {
            // both team names is forgotten
            Match match = new Match("", "");
            fail("Exception should have been thrown when both team names is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        try {
            // home team name is space
            Match match = new Match(" ", "Japan");
            fail("Exception should have been thrown when home team name is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        try {
            // away team name is space
            Match match = new Match("England", " ");
            fail("Exception should have been thrown when away team name is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

        try {
            // both team names is space
            Match match = new Match(" ", " ");
            fail("Exception should have been thrown when both team names is empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Both home team and away team need to have a name.", e.getMessage(),
                    "The error message is not correct.");
        }

    }

    @Test
    public void testStartingScore() {
        assertEquals(new ArrayList<>(List.of(0, 0)), match1.getScore(),
                "The initial score of the match should be 0-0.");
    }

    @Test
    public void testGetScore() {
        match1.homeTeamScores();
        assertEquals(new ArrayList<>(List.of(1, 0)), match1.getScore(), "The score should be 1-0 at this point.");
        match1.homeTeamScores();
        assertEquals(new ArrayList<>(List.of(2, 0)), match1.getScore(), "The score should be 2-0 at this point.");
        match1.awayTeamScores();
        assertEquals(new ArrayList<>(List.of(2, 1)), match1.getScore(), "The score should be 2-1 at this point.");
    }

    @Test
    public void testGetScoreSum() {
        match1.homeTeamScores();
        assertEquals(1, match1.getScoreSum(), "The sum should be 1 at this point.");
        match1.homeTeamScores();
        assertEquals(2, match1.getScoreSum(), "The sum should be 2 at this point.");
        match1.awayTeamScores();
        assertEquals(3, match1.getScoreSum(), "The sum should be 3 at this point.");
    }

    @Test
    public void testGetMatchString() {
        match1.homeTeamScores();
        assertEquals("Spain(1) - Denmark(0)", match1.getMatchString(), "The match string is not correct.");
        match1.homeTeamScores();
        assertEquals("Spain(2) - Denmark(0)", match1.getMatchString(), "The match string is not correct.");
        match1.awayTeamScores();
        assertEquals("Spain(2) - Denmark(1)", match1.getMatchString(), "The match string is not correct.");
    }

}
