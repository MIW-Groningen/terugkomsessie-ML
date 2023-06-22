package model.menace;

import model.GameBoard;
import model.Move;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public class MatchBox {
    private static final int INITIAL_BEAD_COUNT = 7;
    private static final int ADDED_BEADS_FOR_WIN = 3;
    private static final int SUBTRACTED_BEADS_FOR_LOSS = 1;
    private static final int ADDED_BEADS_FOR_DRAW = 1;
    private final Map<Move, Integer> moveProbabilities;

    public MatchBox(GameBoard board) {
        moveProbabilities = new HashMap<>();

        for (Move move : board.potentialMoves()) {
            moveProbabilities.put(move, INITIAL_BEAD_COUNT);
        }
    }

    public Move getRandomMove() {
        int totalBeads = moveProbabilities.values().stream().mapToInt(i -> i).sum();

        if (totalBeads <= 0) {
            throw new IndexOutOfBoundsException();
        }

        int randomRoll = (int) (Math.random() * totalBeads);

        int cumulativeProbality = 0;

        for (Move move : moveProbabilities.keySet()) {
            cumulativeProbality += moveProbabilities.get(move);
            if (cumulativeProbality > randomRoll) {
                return move;
            }
        }

        System.out.println(moveProbabilities);
        throw new IndexOutOfBoundsException();
    }

    public void reinforceWin(Move move) {
        moveProbabilities.put(move, moveProbabilities.get(move) + ADDED_BEADS_FOR_WIN);
    }

    public void reinforceLoss(Move move) {
        moveProbabilities.put(move, Math.max(0, moveProbabilities.get(move) - SUBTRACTED_BEADS_FOR_LOSS));
    }

    public void reinforceDraw(Move move) {
        moveProbabilities.put(move, moveProbabilities.get(move) + ADDED_BEADS_FOR_DRAW);
    }

    @Override
    public String toString() {
        return moveProbabilities.toString();
    }
}
