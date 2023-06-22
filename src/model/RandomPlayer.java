package model;

import java.util.Set;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public class RandomPlayer extends Player {
    @Override
    public Move getMove(GameBoard board) {
        return getRandomMove(board.potentialMoves());
    }

    public Move getRandomMove(Set<Move> moveSet) {
        return moveSet.stream().skip((int) (moveSet.size() * Math.random())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Empty moveSet"));
    }
}
