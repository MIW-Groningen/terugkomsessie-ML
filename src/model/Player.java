package model;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public abstract class Player {
    public abstract Move getMove(GameBoard board);

    public void processWinner(char winner) {

    }
}
