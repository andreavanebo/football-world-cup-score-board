package worldcup;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class scoreboardController {

    @FXML
    Button startNewMatchButton;

    @FXML
    Button addGoalButton;

    @FXML
    Button stopMatchButton;

    @FXML
    TextField startMatchHomeTeamInput;

    @FXML
    TextField startMatchAwayTeamInput;

    @FXML
    TextField addGoalScoringTeamInput;

    @FXML
    TextField stopMatchTeamInput;

    @FXML
    TextArea scoreBoardOutputArea;

    @FXML
    Text errorFieldStartNewMatch;

    @FXML
    Text errorFieldAddGoal;

    @FXML
    Text errorFieldStopMatch;

    ScoreBoard scoreBoard = new ScoreBoard();

    @FXML
    private void startNewMatchButtonOnClick() {
        String homeTeam = startMatchHomeTeamInput.getText();
        String awayTeam = startMatchAwayTeamInput.getText();

        try {
            Match match = new Match(homeTeam, awayTeam);
            try {
                this.scoreBoard.addMatch(match);
                updateScoreBoard();
                startMatchHomeTeamInput.setText("");
                startMatchAwayTeamInput.setText("");
                errorFieldStartNewMatch.setText("");
            } catch (IllegalArgumentException e) {
                errorFieldStartNewMatch.setText("At least one of the given teams are in an ongoing match.");
            }

        } catch (IllegalArgumentException e) {
            errorFieldStartNewMatch.setText("Both home team and away team need to have a name.");
        }

    }

    @FXML
    private void addGoalButtonOnClick() {
        String teamName = addGoalScoringTeamInput.getText();
        Match match = this.scoreBoard.getMatch(teamName);
        if (match != null) {
            if (match.getHomeTeam().equals(teamName)) {
                match.homeTeamScores();
            } else {
                match.awayTeamScores();
            }
            updateScoreBoard();
            addGoalScoringTeamInput.setText("");
            errorFieldAddGoal.setText("");
        } else {
            errorFieldAddGoal.setText("There is no ongoing match with the team " + teamName);
        }

    }

    @FXML
    private void stopMatchButtonOnClick() {
        String teamName = stopMatchTeamInput.getText();
        try {
            this.scoreBoard.stopMatch(teamName);
            updateScoreBoard();
            stopMatchTeamInput.setText("");
            errorFieldStopMatch.setText("");
        } catch (IllegalArgumentException e) {
            errorFieldStopMatch.setText("There is no ongoing match with the team " + teamName);
        }

    }

    private void updateScoreBoard() {
        scoreBoardOutputArea.setText(this.scoreBoard.getScoreBoardText());

    }

}
