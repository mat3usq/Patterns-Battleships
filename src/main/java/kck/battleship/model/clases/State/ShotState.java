package kck.battleship.model.clases.State;

import kck.battleship.controller.GameException;
import kck.battleship.controller.ViewController;
import kck.battleship.model.clases.Player;
import kck.battleship.model.clases.Position;

public class ShotState implements PlayerState {
    @Override
    public boolean defend(Player player) {
        return player.areShipsStillSailing();
    }

    @Override
    public Position shot(Player attacker, Player defender) {
        Position shoot = null;
        if (attacker.isAI()) {
            boolean isAddHit;
            do {
                try {
                    shoot = attacker.shoot(defender.getBattleField(), defender.getShips());
                    isAddHit = defender.addShoot(shoot);
                } catch (GameException e) {
                    isAddHit = false;
                }
            } while (!isAddHit);
        } else try {
            shoot = ViewController.getInstance().getPositionToShot(defender, attacker);
            defender.addShoot(shoot);
        } catch (GameException e) {
            throw new RuntimeException(e);
        }
        return shoot;
    }
}
