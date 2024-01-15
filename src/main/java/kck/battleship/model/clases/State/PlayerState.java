package kck.battleship.model.clases.State;

import kck.battleship.model.clases.Player;
import kck.battleship.model.clases.Position;

public interface PlayerState {
    boolean defend(Player player);

    Position shot(Player attacker, Player defender);
}
