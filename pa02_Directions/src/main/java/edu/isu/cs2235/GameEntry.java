package edu.isu.cs2235;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A score entry for the scoreboard.
 *
 * @author Isaac Griffith
 */
public class GameEntry {

    private final String name; // name of the player
    private final int score; // player's score

    /**
     * Constructs a new GameEntry for the player with the given name and score
     *
     * @param name The player's name
     * @param score The player's score
     */
    public GameEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The player's score
     */
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + score + ")";
    }

    /**
     * A factory method used to generate a random game entry
     *
     * @return A new GameEntry with a random name and score.
     */
    public static GameEntry createRandomEntry() {
        Random rand = new Random();

        Path path = Paths.get("data/names.csv");
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                Collections.shuffle(lines);
                String name;

                double x = rand.nextDouble();
                if (Double.compare(x, 0.5) < 0) {
                    name = lines.get(0).split(",")[0]; // Male
                } else {
                    name = lines.get(0).split(",")[1]; // Female
                }
                return new GameEntry(name, rand.nextInt(100000) + 1);
            } catch (IOException ex) {
                Logger.getLogger(GameEntry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }
}
