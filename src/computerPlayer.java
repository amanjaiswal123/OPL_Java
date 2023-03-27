import java.util.List;

public class computerPlayer extends Player {
    public computerPlayer() {
        super();
    }
    @Override
    public List<Object> getValidMove(List<Player> players, List<Object> recMove) {
        System.out.println("\nThe Computer Chose to Play " + recMove.get(0) + " on " + recMove.get(1) + " because it has a difference of " + recMove.get(2) + " which is the lowest difference move on an opponent's stack.");
        return recMove;
    }
}
