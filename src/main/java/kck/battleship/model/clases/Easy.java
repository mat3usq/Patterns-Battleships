package kck.battleship.model.clases;

import kck.battleship.controller.GameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Easy  implements Difficulty{
    private int[][] board;
    private List<Position> positions;

    public Easy() {
        this.board = new int[10][10];
        this.positions = new ArrayList<>();
        initializePositions();
    }

    private void initializePositions() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                positions.add(new Position(row, column));
            }
        }
    }

    public Position shootAtRandom() throws GameException {
        if (positions.isEmpty()) {
            throw new GameException("Brak dostępnych pozycji do strzelania");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(positions.size());
        Position target = positions.get(randomIndex);
        board[target.getRow()][target.getColumn()] = 1;
        positions.remove(randomIndex);
        return target;
    }


}