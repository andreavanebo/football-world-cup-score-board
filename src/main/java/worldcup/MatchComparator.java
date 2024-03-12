package worldcup;

public class MatchComparator implements java.util.Comparator<Match> {
    @Override
    public int compare(Match match1, Match match2) {
        return match2.getScoreSum() - match1.getScoreSum();
    }
}
