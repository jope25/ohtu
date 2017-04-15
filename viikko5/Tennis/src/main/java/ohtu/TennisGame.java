package ohtu;

import java.util.Map;
import java.util.HashMap;

public class TennisGame {

    private Player player1;
    private Player player2;
    private Map<Integer, String> scores;

    public TennisGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        putScores();
    }

    public void pointFor(String playerName) {
        if (playerName.equals(player1.getName())) {
            player1.addPoint();
        } else {
            player2.addPoint();
        }
    }

    public String getScore() {
        int player1Points = player1.getPoints();
        int player2Points = player2.getPoints();

        if (player1Points == player2Points) {
            return tie();
        } else if (player1Points >= 4 || player2Points >= 4) {
            return advantageOrWinFor();
        } else {
            return score();
        }
    }

    private String tie() {
        if (player1.getPoints() == 4) {
            return "Deuce";
        }
        return scores.get(player1.getPoints()) + "-All";
    }

    private String advantageOrWinFor() {
        int differenceInPoints = player1.getPoints() - player2.getPoints();
        if (Math.abs(differenceInPoints) == 1) {
            return result("Advantage");
        } else {
            return result("Win for");
        }
    }
    
    private String result(String result) {
        if (player1.getPoints() > player2.getPoints()) {
            return result + " " + player1.getName();
        } else {
            return result + " " + player2.getName();
        }
    }

    private String score() {
        return scores.get(player1.getPoints()) + "-" + scores.get(player2.getPoints());
    }

    private void putScores() {
        scores = new HashMap<>();
        scores.put(0, "Love");
        scores.put(1, "Fifteen");
        scores.put(2, "Thirty");
        scores.put(3, "Forty");
    }
}
