package kck.battleship.model.clases.State;

import kck.battleship.controller.ViewController;
import kck.battleship.model.clases.Player;
import kck.battleship.model.clases.Position;
import kck.battleship.model.types.TypesField;

public class DefendState implements PlayerState {
    @Override
    public boolean defend(Player player) {
        if (player.getDurabilityForceField() > 0) {
            player.setDurabilityForceField(player.getDurabilityForceField() - 1);
            ViewController.getInstance().printBarrier(player);
        }
        return player.getDurabilityForceField() > 0;
    }

    @Override
    public Position shot(Player attacker, Player defender) {
        Position shoot = defender.allShoots.get(defender.allShoots.size() - 1);
        boolean isHit = defender.getBattleField().at(shoot) == TypesField.HIT.name;
        ViewController.getInstance().printShot(defender, shoot, isHit);
        ViewController.getInstance().delayForGameplay();
        return null;
    }
}
