package model;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public abstract class Player {

    private final char playerSymbol;

    public Player(char playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public abstract Move getMove(GameBoard board);

    public char getPlayerSymbol() {
        return playerSymbol;
    }
}
