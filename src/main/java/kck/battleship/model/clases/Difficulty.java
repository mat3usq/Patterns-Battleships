package kck.battleship.model.clases;

import kck.battleship.controller.GameException;

public interface Difficulty {
    public Position shootAtRandom();
    void get_result(char result);
    void enemy_ships_comprasion(int ships);
}
