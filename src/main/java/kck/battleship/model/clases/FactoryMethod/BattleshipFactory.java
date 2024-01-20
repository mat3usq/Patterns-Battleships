package kck.battleship.model.clases.FactoryMethod;

import kck.battleship.model.clases.Ship;
import kck.battleship.model.types.TypesShips;

public class BattleshipFactory extends  ShipFactory{
    @Override
    public Ship createShip() {
        return new Ship(TypesShips.getShipName(TypesShips.BATTLESHIP), TypesShips.BATTLESHIP.getShipLength());
    }
}
