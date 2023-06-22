package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public class GameBoard {
    private static final int BOARD_SIZE = 3;
    public static final char PLAYER_1 = 'X';
    public static final char PLAYER_2 = 'O';

    char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public GameBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public GameBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }

    public GameBoard(GameBoard gameBoard) {
        this(gameBoard.board);
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder(" --- --- --- \n");

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                returnString.append("| ").append(board[i][j]).append(" ");
            }
            returnString.append("|\n");
            returnString.append(" --- --- --- \n");
        }

        return returnString.toString();
    }

    public Set<Move> potentialMoves() {
        Set<Move> moves = new HashSet<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    moves.add(new Move(i, j));
                }
            }
        }

        return moves;
    }

    public void makeMove(Move move) {
        if (turn() % 2 == 0) {
            board[move.getX()][move.getY()] = PLAYER_1;
        } else {
            board[move.getX()][move.getY()] = PLAYER_2;
        }
    }

    public GameBoard getBoardWithMove(Move move) {
        GameBoard newBoard = new GameBoard(board);
        newBoard.makeMove(move);
        return newBoard;
    }

    public int turn() {
        return BOARD_SIZE * BOARD_SIZE - potentialMoves().size();
    }

    public char winner() {
        char winner = ' ';
        // check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            winner = board[i][0];
            for (int j = 1; j < BOARD_SIZE; j++) {
                if (board[i][j] != winner) {
                    winner = ' ';
                    break;
                }
            }
            if (winner != ' ') {
                return winner;
            }
        }

        // check columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            winner = board[0][i];
            for (int j = 1; j < BOARD_SIZE; j++) {
                if (board[j][i] != winner) {
                    winner = ' ';
                    break;
                }
            }
            if (winner != ' ') {
                return winner;
            }
        }

        // check diagonal 1
        winner = board[0][0];
        for (int j = 1; j < BOARD_SIZE; j++) {
            if (board[j][j] != winner) {
                winner = ' ';
                break;
            }
        }
        if (winner != ' ') {
            return winner;
        }

        // check diagonal 2
        winner = board[0][BOARD_SIZE - 1];
        for (int j = 1; j < BOARD_SIZE; j++) {
            if (board[j][BOARD_SIZE - 1 - j] != winner) {
                winner = ' ';
                break;
            }
        }
        if (winner != ' ') {
            return winner;
        }


        return winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameBoard board1 = (GameBoard) o;
        return Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
