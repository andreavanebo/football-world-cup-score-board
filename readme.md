# Live Football World Cup Scoreboard

## Assumptions

The application is made with the assumption that a team can only play one match at the same time. If there would be a case where a team could have a "second-team" and in that way the same team could be playing two matches at the same time: a solution would be to give the teams the names "Norway 1" and "Norway 2" (but this is probably not the case for the World Cup).

And to clarify one detail: When the user is going to delete a match from the scoreboard, only one of the teamnames is needed. Since a team can only play one match at the same time, it is maximum one possible match for a given team name. The user can therefore write eighter the home team or the away team of the match they want to delete.

## How to run the code

Go to the file src/main/java/worldcup/App.java and run the main method.

## Classes

### Match.java

A class that represents a single match, between two teams: the home team and the away team. The initial score is 0-0, the score is changed by calling eighter homeTeamScores() og awayTeamScores().

### ScoreBoard.java

A class that represents a scoreboard, keeping track of several matches that is beeing played at this time. The class has a scoreboard-List, with Match-objects.

### MatchComparator.java

A simple class to create a comparator between matches, to sort in decreasing order by score sum. It is used in the class ScoreBoard in the function getScoreBoard() to sort the list.

### ScoreboardController.java

A class that detect the interactions in the application, handles the operations and gives the right responses to the user.

### App.java

A class that loads the application, and gets it up and going. This is where you start the application by running the main method.

## Tests

### MatchTests.java

Different tests to test the functionality of the Match class.

### ScoreboardTests.java

Different tests to test the functionality of the Scoreboard class.

### MatchComparatorTests.java

A test to test the functionality of the Match comparator class.

### testing of ScoreboardController

The controller was tested in the application, without creating tests. For example the example given in the task description was registrated in the application to see that the correct order was displayed in the end.

## Further Work

To further extend this application it would be benefitial to make a soultion where it is possible to add a goal and stop a match by just clicking a button. For example the scoreboard could have had a format where each row in the list included a button for each of the teams to add a goal, as well as a button to stop the match. For simplicity this was not done like this in this solution.
