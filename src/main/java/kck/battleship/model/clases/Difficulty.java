package kck.battleship.model.clases;

import kck.battleship.controller.GameException;

import java.util.ArrayList;

public interface Difficulty {
    public Position shootAtRandom();
    void get_result(char result);
    void enemy_ships_comprasion(int ships);

    public void get_ships_and_change_smallest_ship(ArrayList<Ship> ships);
}
