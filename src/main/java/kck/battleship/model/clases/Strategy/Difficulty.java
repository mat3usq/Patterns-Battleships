package kck.battleship.model.clases.Strategy;

import kck.battleship.model.clases.Position;
import kck.battleship.model.clases.Ship;

import java.util.ArrayList;

public interface Difficulty {
    Position shootAtRandom();
    void get_result(char result);
    void enemy_ships_comprasion(int ships);

    void get_ships_and_change_smallest_ship(ArrayList<Ship> ships);
}
