package kck.battleship.model.clases.FactoryMethod;

import kck.battleship.model.clases.Ship;
import kck.battleship.model.types.TypesShips;

public class CruiserFactory extends ShipFactory {
    @Override
    public Ship createShip() {
        return new Ship(TypesShips.getShipName(TypesShips.CRUISER), TypesShips.CRUISER.getShipLength());
    }
}
