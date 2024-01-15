package kck.battleship.model.clases.Strategy;

import kck.battleship.model.clases.Position;

import java.util.ArrayList;

public interface Observer {
    void update(Position position, ArrayList<Integer> shiplenght);
}
