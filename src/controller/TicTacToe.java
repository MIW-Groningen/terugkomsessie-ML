package controller;

import model.GameBoard;
import model.HumanPlayer;
import model.Player;
import model.RandomPlayer;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public class TicTacToe {

    public static void main(String[] args) {
        Player player1 = new HumanPlayer('X');
        Player player2 = new RandomPlayer('O');

        while (true) {
            GameBoard board = new GameBoard();
            while (board.winner() == ' ') {
                if (board.turn() % 2 == 0) {
                    board.makeMove(player1.getPlayerSymbol(), player1.getMove(board));
                } else {
                    board.makeMove(player2.getPlayerSymbol(), player2.getMove(board));
                }
                System.out.println(board);
            }
            System.out.println("We have a winner! " + board.winner());
        }
    }
}
