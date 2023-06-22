package controller;

import model.GameBoard;
import model.HumanPlayer;
import model.Player;
import model.RandomPlayer;
import model.menace.MenacePlayer;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public class TicTacToe {

    public static void main(String[] args) {
        Player player1 = new HumanPlayer();
        Player player2 = new RandomPlayer();
        int[] wins = new int[3];
        int[] lastWins = new int[3];

        while (true) {
            for (int i = 0; i < 1000; i++) {
                GameBoard board = new GameBoard();
                while (board.winner() == ' ') {
                    if (board.turn() == 9) {
//                        System.out.println("Draw");
                        wins[0]++;
                        lastWins[0]++;
                        break;
                    }
                    if (board.turn() % 2 == 0) {
                        board.makeMove(player1.getMove(board));
                    } else {
                        board.makeMove(player2.getMove(board));
                    }
                }
                player1.processWinner(board.winner());
                player2.processWinner(board.winner());
                if (board.winner() == GameBoard.PLAYER_1) {
                    wins[1]++;
                    lastWins[1]++;
                } else if (board.winner() == GameBoard.PLAYER_2){
                    wins[2]++;
                    lastWins[2]++;
                }
            }
            System.out.printf("Last 1000:\n' ': %3d 'X': %3d 'O': %3d\n", lastWins[0], lastWins[1], lastWins[2]);
            System.out.printf("All time:\n' ': %3d 'X': %3d 'O': %3d\n", wins[0], wins[1], wins[2]);

            lastWins = new int[3];

        }
    }
}
