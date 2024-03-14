package worldcup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchComparatorTests {
    private Match match1;
    private Match match2;
    MatchComparator comparator = new MatchComparator();

    @BeforeEach
    public void setUp() {
        match1 = new Match("Spain", "Denamrk");
        match2 = new Match("Portugal", "France");

    }

    @Test
    public void testMatchComparator() {
        match1.homeTeamScores();
        match1.awayTeamScores();
        match2.homeTeamScores();

        assertEquals(-1, comparator.compare(match1, match2));

        match2.homeTeamScores();
        match2.awayTeamScores();

        assertEquals(1, comparator.compare(match1, match2));

        match1.awayTeamScores();

        assertEquals(0, comparator.compare(match1, match2));

    }

}
