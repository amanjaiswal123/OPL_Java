public class Tile {
    // The tile class stores the value of the left and right side of a tile as well as overriding some basic operators
    // to make it easier to work with. It takes in DisplayTile which adds attributes and methods to the tile class to
    // make it easier to display it on the game board.

    private int left;
    private int right;
    private Player player;
    private boolean selected;
    private boolean doubleTile;

    public Tile(int left, int right, Player player) {
        super();
        // The left and right attributes store the value of the left and right side of the tile
        this.left = left;
        this.right = right;
        // The player attribute stores the player object that owns the tile, useful for scoring
        this.player = player;
        // If both sides of the tile are the same, the tile is a double
        this.doubleTile = this.left == this.right;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() {
        return this.selected;
    }

    public boolean isDouble() {
        return this.doubleTile;
    }

    public int getLeft() {
        return this.left;
    }

    public int getRight() {
        return this.right;
    }

    @Override
    public String toString() {
        return "|" + this.player.getColor() + this.left + this.right + "|";
    }

    public void displayTile() {
        System.out.print("|" + this.player.getColor() + this.left + this.right + "| ");
    }

    // Override basic operators to make it easier to work with tiles
    public int sum() {
        return this.left + this.right;
    }

    public int difference(Tile other) {
        return (this.left + this.right) - (other.left + other.right);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left;
        result = prime * result + right;
        return result;
    }

    public int indexOf(Object o) {
        if (!(o instanceof Tile)) {
            return -1;
        }
        Tile otherTile = (Tile) o;
        for (int i = 0; i < player.getHand().size(); i++) {
            Tile tile = player.getHand().get(i);
            if (tile.toString().equals(otherTile.toString())) {
                return i;
            }
        }
        return -1;
    }


    public boolean equals(Tile obj) {
        if (this.sum() == obj.sum()){
            return true;
        }
        else{
            return false;
        }
    }

    public int compareTo(Tile other) {
        // Compare tiles based on their sum
        return Integer.compare(this.sum(), other.sum());
    }

    public Player getPlayer() {
        return this.player;
    }
}
