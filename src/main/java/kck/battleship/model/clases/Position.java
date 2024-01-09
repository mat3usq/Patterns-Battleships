package kck.battleship.model.clases;

import kck.battleship.controller.GameException;

import java.util.Random;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) throws GameException {
        if (row < 0 || column < 0)
            throw new GameException("Wprowadz pozycje, ktora jest na polu bitwy");
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public static Position randPosition() throws GameException {
        Random random = new Random();
        int x = random.nextInt(BattleField.getLength());
        int y = random.nextInt(BattleField.getLength());
        return new Position(x, y);
    }

    public String toString(Position position) {
        return (char) ('a' + position.getRow()) + String.valueOf(position.getColumn() + 1);
    }
}
