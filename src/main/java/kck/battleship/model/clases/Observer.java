package kck.battleship.model.clases;

import java.util.ArrayList;

public interface Observer {
    public void update(Position position, ArrayList<Integer> shiplenght);
}
