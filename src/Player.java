import java.util.ArrayList;
import java.util.List;

public class Player {
    // The player class is used to create a player object. The player object is used to store the player's data including
    // their player id, color, boneyard, hand, stacks, score, and rounds won.
    private int playerID;
    private String color;
    private List<Tile> boneyard;
    private List<Tile> hand;
    private List<Tile> stack;
    private int score;
    private int roundsWon;
    private int handOffset;

    public Player() {
        // The player's ID used to identify the player
        this.playerID = -1;
        // The player's color used to identify the player's tiles
        this.color = "";
        // The player's boneyard is a list of tile objects in the player's boneyard. A list of tile objects
        this.boneyard = new ArrayList<>();
        // The player's hand is a list of tile objects inplayer's hand
        this.hand = new ArrayList<>();
        // The player's stack is a list of tile objects in the stacks
        this.stack = new ArrayList<>();
        // Represents the score of the player per round. Is reset every new round
        this.score = 0;
        // Represents the number of rounds the player has won
        this.roundsWon = 0;
        // Used to scroll through the player's hand in gui.hand_listener
        this.handOffset = 0;
    }

    // Getters and setters for the class members
    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Tile> getBoneyard() {
        return boneyard;
    }

    public void setBoneyard(List<Tile> boneyard) {
        this.boneyard = boneyard;
    }

    public List<Tile> getHand() {
        return hand;
    }

    public void setHand(List<Tile> hand) {
        this.hand = hand;
    }

    public List<Tile> getStack() {
        return stack;
    }

    public void setStack(List<Tile> stack) {
        this.stack = stack;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void setRoundsWon(int roundsWon) {
        this.roundsWon = roundsWon;
    }

    public int getHandOffset() {
        return handOffset;
    }

    public void setHandOffset(int handOffset) {
        this.handOffset = handOffset;
    }

    public void generateBoneyard(int n) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= x; y++) {
                Tile cTile = new Tile(x, x - y, this);
                boneyard.add(cTile);
            }
        }
    }
}
