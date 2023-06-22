package model;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public class HumanPlayer extends Player {
    private final Map<Character, Move> keyboardToMoveMap = Map.of(
            'q', new Move(0, 0),
            'w', new Move(0, 1),
            'e', new Move(0, 2),
            'a', new Move(1, 0),
            's', new Move(1, 1),
            'd', new Move(1, 2),
            'z', new Move(2, 0),
            'x', new Move(2, 1),
            'c', new Move(2, 2)
    );

    private final Scanner keyboard;

    public HumanPlayer() {
        keyboard = new Scanner(System.in);
    }

    @Override
    public Move getMove(GameBoard board) {
        System.out.println(board);
        System.out.print("Please make a move: ");
        char playerInput = keyboard.next().charAt(0);

        while (keyboardToMoveMap.get(playerInput) == null || !board.potentialMoves().contains(keyboardToMoveMap.get(playerInput))) {
            System.out.println("That is not a valid move");
            System.out.print("Please use q, w, e; a, s, d; z, x, q; to make a move: ");
            playerInput = keyboard.next().charAt(0);
        }

        return keyboardToMoveMap.get(playerInput);
    }

    @Override
    public void processWinner(char winner) {
        System.out.printf("We have a winner: %c", winner);
    }
}
