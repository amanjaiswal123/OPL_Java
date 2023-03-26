import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Instantiate a new Player object
        Player player = new Player();

        // Access and modify the Player object's data members
        player.setPlayerID(1);
        player.setColor("Red");
        player.setScore(10);

        // Print out the Player object's data members
        System.out.println("Player ID: " + player.getPlayerID());
        System.out.println("Player color: " + player.getColor());
        System.out.println("Player score: " + player.getScore());
    }
}