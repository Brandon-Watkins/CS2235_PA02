package edu.isu.cs2235;

/**
 * List Applications
 *
 * @author Isaac Griffith
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scoreboard board = new Scoreboard(10);

        for (int i = 0; i < 10; i++) {
            board.add(GameEntry.createRandomEntry());
        }

        board.printScores();

        System.out.println();
        for (int i = 0; i < 10000; i++) {
            board.add(GameEntry.createRandomEntry());
        }
        board.printScores();
    }
    
}
