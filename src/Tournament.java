import java.util.*;

public class Tournament {
    Player player1;
    private Scanner scanner;

    List<Player> players;

    public Tournament() {
        this.start_new_tournament();
        this.players = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start_new_tournament() {
        // Create a new player object
        Player humanPlayer = new Player();
        humanPlayer.createNewPlayer("Human", "B");
        Player computerPlayer = new Player();
        computerPlayer.createNewPlayer("Computer", "W");
        this.players = new ArrayList<>();
        this.players.add(humanPlayer);
        this.players.add(computerPlayer);
        this.determineOrder();
        this.play_round(1);
    }

    public String getValidInput(String prompt, List<String> validInputs) {
        String input;

        while (true) {
            System.out.print(prompt);
            input = this.scanner.nextLine().trim().toUpperCase();

            if (validInputs.contains(input)) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

        return input;
    }
    public void play_again(){
        String input = this.getValidInput("Would you like to play again? (Y/N): ", Arrays.asList("Y", "N"));
        if (input.equals("Y")) {
            this.start_new_tournament();
        } else {
            boolean first = true;
            Player winner = null;
            for (Player player : players) {
                if (first) {
                    winner = player;
                    first = false;
                } else {
                    if (player.getRoundsWon() > winner.getRoundsWon()) {
                        winner = player;
                    }
                }
            }
            System.out.println("Player " + winner.getPlayerID() + " won the tournament!");
            System.out.println("Thanks for playing!");
        }
    }

    public void determineOrder() {
        boolean equal = true;
        while (equal) {
            equal = false;
            System.out.println("\nDetermining Order:");
            for (Player currentPlayer : players) {
                System.out.println("Player " + currentPlayer.getPlayerID() + " boneyard");
                // Assuming displayBoneyard() is implemented in the Player class
                currentPlayer.displayBoneyard();
                System.out.println("Shuffling Boneyard...");
                currentPlayer.shuffleBoneyard();
                System.out.println("Player " + currentPlayer.getPlayerID() + " shuffled boneyard");
                currentPlayer.displayBoneyard();
                currentPlayer.moveFromBoneyardToHandN(1);
            }

            System.out.println("\nPlayers hands:");
            for (Player currentPlayer : players) {
                System.out.print("Player " + currentPlayer.getPlayerID() + " has tile ");
                // Assuming displayHand() is implemented in the Player class
                currentPlayer.displayHand();
            }

            System.out.println("\nComparing Tiles...");
            players.sort((p1, p2) -> p2.getHand().get(0).compareTo(p1.getHand().get(0)));

            outerLoop:
            for (Player compPlayer : players) {
                for (Player currentPlayer : players) {
                    if (compPlayer.getHand().get(0).equals(currentPlayer.getHand().get(0)) && !compPlayer.equals(currentPlayer)) {
                        System.out.println("\n\nPlayer " + compPlayer.getPlayerID() + " and Player " + currentPlayer.getPlayerID() + " have equal tiles re-shuffling");
                        equal = true;
                        for (Player currentPlayer2 : players) {
                            currentPlayer2.moveFromHandToBoneyardN(1);
                        }
                        break outerLoop;
                    }
                }
            }
        }
    }

    public void play_round(int handnum) {
        if (handnum == 1) {
            if (players.get(0).getHand().size() == 1 && players.get(1).getHand().size() == 1) {
                for (Player currentPlayer : players) {
                    currentPlayer.moveFromBoneyardToHandN(5);
                }
            }
            playHand();
            handnum += 1;
        }
        if (handnum == 2) {
            if (players.get(0).getHand().size() == 0 && players.get(1).getHand().size() == 0) {
                for (Player currentPlayer : players) {
                    currentPlayer.moveFromBoneyardToHandN(6);
                }
            }
            playHand();
            handnum += 1;
        }
        if (handnum == 3) {
            if (players.get(0).getHand().size() == 0 && players.get(1).getHand().size() == 0) {
                for (Player currentPlayer : players) {
                    currentPlayer.moveFromBoneyardToHandN(6);
                }
            }
            playHand();
            handnum += 1;
        }
        if (handnum == 4) {
            if (players.get(0).getHand().size() == 0 && players.get(1).getHand().size() == 0) {
                for (Player currentPlayer : players) {
                    currentPlayer.moveFromBoneyardToHandN(4);
                }
            }
            playHand();
            handnum += 1;
        } else {
            this.scoreRound();
        }
    }

    public void displayAllStacks() {
        for (Player player : this.players) {
            System.out.print("Player " + player.getPlayerID() + "'s stack: ");
            for (Tile tile : player.getStack()) {
                tile.displayTile();
            }
            System.out.println();
        }
    }
    public void displayAllHands() {
        for (Player player : this.players) {
            System.out.print("Player " + player.getPlayerID() + "'s stack: ");
            for (Tile tile : player.getHand()) {
                tile.displayTile();
            }
            System.out.println();
        }
    }

    public Map<String, Integer> scoreHand() {
        Map<String, Integer> scores = new HashMap<>();
        for (Player player : this.players) {
            scores.put(player.getPlayerID(), player.scoreHand());
        }
        return scores;
    }

    public Map<String, Integer> scoreStacks() {
        Map<String, Integer> scores = new HashMap<>();
        for (Player player : this.players) {
            scores.put(player.getPlayerID(), player.scoreStack());
        }
        return scores;
    }
    public void playHand() {
        int consecutivePasses = 0;
        boolean allEmptyHands = true;
        Tile handTile;
        for (Player c_player : this.players) {
            if (c_player.getHand().size() != 0) {
                allEmptyHands = false;
                break;
            }
        }
        while (consecutivePasses < players.size() && !allEmptyHands) {
            for (Player currentPlayer : players) {
                System.out.println("\nPlayer " + currentPlayer.getPlayerID() + "'s turn\n");
                currentPlayer.displayHand();
                System.out.println();
                this.displayAllStacks();
                List<Object> recMove = currentPlayer.recommendMove(players);
                List<Object> move = currentPlayer.getValidMove(players, recMove);
                if (move.get(0) != "pass") {
                    handTile = (Tile) move.get(0);
                    Tile stackTile = (Tile) move.get(1);
                    System.out.println("\nPlayer " + currentPlayer.getPlayerID() + " played tile ");
                    handTile.displayTile();
                    System.out.println("to tile ");
                    stackTile.displayTile();
                    System.out.println("in the stacks");
                    List<Tile> stack = new ArrayList<>();
                    executeMove(handTile, stackTile);
                    consecutivePasses = 0;
                } else {
                    System.out.println("\nPlayer " + currentPlayer.getPlayerID() + " passed");
                    consecutivePasses++;
                    if (consecutivePasses >= players.size()) {
                        break;
                    }
                }
                if (consecutivePasses >= players.size()) {
                    break;
                }
            }
            allEmptyHands = true;
            for (Player c_player : this.players) {
                if (c_player.getHand().size() != 0) {
                    allEmptyHands = false;
                    break;
                }
            }
        }

          System.out.println("\n\nHand Over");
          System.out.println("Final Hands");
          this.displayAllHands();
          System.out.println("Final Stacks");
          this.displayAllStacks();
          // Score the hand
          System.out.println("Scoring Hands:");
          // Get the scores for the hand
          Map<String, Integer> handScores = this.scoreHand();
    //     // Get the scores for the stacks
         Map<String, Integer> stackScores = scoreStacks();
    // Create a dictionary to hold the final scores of the players
         Map<String, Integer> finalScores = new HashMap<>();
        for (Player currentPlayer : players) {
            int score = stackScores.get(currentPlayer.getPlayerID()) - handScores.get(currentPlayer.getPlayerID());
            finalScores.put(currentPlayer.getPlayerID(), score);
            currentPlayer.addScore(score);
            System.out.println("Player " + currentPlayer.getPlayerID() + " scored " + score + " points");
        }
      for (Player currentPlayer : players) {
         currentPlayer.clearHand();
        }
    }

    public void executeMove (Tile handTile, Tile stackTile){
        List<Tile> stack = new ArrayList<>();
        for (Player c_player_1 : this.players) {
            for (Tile tile_1 : c_player_1.getStack()) {
                if (tile_1.toString().equals(stackTile.toString())) {
                    stack = c_player_1.getStack();
                    int stackIndex = stack.indexOf(stackTile);
                    stack.set(stackIndex, handTile);
                    c_player_1.setStack(stack);
                    break;
                }
            }
            if (stack.size() != 0) {
                break;
            }
        }
        List<Tile> hand = new ArrayList<>();
        for (Player c_player_2 : this.players) {
            for (Tile tile_2 : c_player_2.getHand()) {
                if (tile_2.toString().equals(handTile.toString())) {
                    hand = c_player_2.getHand();
                    int handIndex = hand.indexOf(handTile);
                    hand.remove(handIndex);
                    c_player_2.setHand(hand);
                    break;
                }
            }
            if (hand.size() != 0) {
                break;
            }
        }
    }


    public void scoreRound () {
        Map<String, Integer> finalScores = new HashMap<>();
        boolean first = true;
        int highestScore = 0;
        Player winner = null;
        for (Player currentPlayer : players) {
            int score = currentPlayer.getScore();
            if (first) {
                winner = currentPlayer;
                highestScore = score;
                first = false;
            } else if (currentPlayer.getScore() > highestScore) {
                winner = currentPlayer;
                highestScore = score;
            }
            finalScores.put(currentPlayer.getPlayerID(), score);
            currentPlayer.addScore(score);
            System.out.println("Player " + currentPlayer.getPlayerID() + " scored " + score + " points");
        }
        winner.addRoundWins();
        System.out.println("Player " + winner.getPlayerID() + " won the round");
    }

    public static void main (String[]args){
        Tournament tournament = new Tournament();
    }
}



