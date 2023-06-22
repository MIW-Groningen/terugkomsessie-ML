package model.menace;

import model.GameBoard;
import model.Move;
import model.Player;

import java.awt.font.GlyphMetrics;
import java.util.*;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * Purpose of the program
 */
public class MenacePlayer extends Player {
    Map<GameBoard, MatchBox> pileOfMatchboxes;
    List<GameBoard> boardsThisGame;
    List<Move> movesThisGame;

    public MenacePlayer() {
        boardsThisGame = new ArrayList<>();
        movesThisGame = new ArrayList<>();

        pileOfMatchboxes = new HashMap<>();
        Set<GameBoard> boardStates = new HashSet<>();
        boardStates.add(new GameBoard());
        do {
            Set<GameBoard> nextBoardStates = new HashSet<>();
            for (GameBoard boardState : boardStates) {
                pileOfMatchboxes.put(boardState, new MatchBox(boardState));
                if (boardState.winner() == ' ') {
                    for (Move potentialMove : boardState.potentialMoves()) {
                        nextBoardStates.add(boardState.getBoardWithMove(potentialMove));
                    }
                }
            }
            boardStates = nextBoardStates;
        } while (boardStates.size() > 0);
    }

    @Override
    public Move getMove(GameBoard board) {
        Move selectedMove;
        try {
            selectedMove = pileOfMatchboxes.get(board).getRandomMove();
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            // effectively giving up
            selectedMove = getRandomMove(board.potentialMoves());
        }
        boardsThisGame.add(new GameBoard(board));
        movesThisGame.add(selectedMove);
        return selectedMove;
    }

    public Move getRandomMove(Set<Move> moveSet) {
        return moveSet.stream().skip((int) (moveSet.size() * Math.random())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Empty moveSet"));
    }

    @Override
    public void processWinner(char winner) {
        // check if player one or two
        Boolean won = null;

        if (boardsThisGame.get(0).turn() == 0) {
            // I am player 1
            won = winner == GameBoard.PLAYER_1;
        } else {
            won = winner == GameBoard.PLAYER_2;
        }

        for (int i = 0; i < movesThisGame.size(); i++) {
            MatchBox matchBox = pileOfMatchboxes.get(boardsThisGame.get(i));

            if (winner == ' ') {
                matchBox.reinforceDraw(movesThisGame.get(i));
            } else if (won) {
                matchBox.reinforceWin(movesThisGame.get(i));
            } else {
                matchBox.reinforceLoss(movesThisGame.get(i));
            }
        }

        boardsThisGame = new ArrayList<>();
        movesThisGame = new ArrayList<>();
    }
}
