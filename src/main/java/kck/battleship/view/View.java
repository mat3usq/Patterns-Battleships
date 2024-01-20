package kck.battleship.view;

import kck.battleship.controller.GameException;
import kck.battleship.model.clases.*;

import java.io.IOException;
import java.util.ArrayList;

public abstract class View {
    public abstract void printHomePage();

    public abstract void printLoginPage();

    public abstract void printMenuPage(int selected);

    public abstract void printExit();

    public abstract void printRules() throws IOException, GameException, InterruptedException;

    public abstract void printError(String message);

    public abstract void printShot(Player player, Position position, boolean isHit);

    public abstract void printWinner(Player player, Ranking rank);

    public abstract void printShip(Ship ship) throws IOException;

    public abstract void printBoards(Player firstPlayer, Player secondPlayer);

    public abstract void printBoard(BattleField battleField, ArrayList<Ship> ships) throws IOException;

    public abstract void showOptionToManuallyAddShip() throws IOException;

    public abstract void showOptionToPlay();

    public abstract void showOptionToSimulatedGame();

    public abstract void printRanking(int page) throws IOException, GameException, InterruptedException;

    public abstract void printShop() throws IOException, GameException, InterruptedException;

    public abstract void printBarrier(Player defender);

    public abstract boolean isRandomShipsArranged();

    public abstract void addShipsVisually(BattleField battleField, Ship ship, ArrayList<Ship> ships);

    public abstract void delayForGameplay();

    public abstract Position getPositionToShot(Player defender, Player attacker);

    public abstract void printMode(int selected);

    public abstract int getSelectedMode();

    public abstract void printModesInSimulation();

    public abstract void printSelectedModeInSimulation(int x, int y);

    public abstract int getFirstSelectedModeInSimulation();

    public abstract int getSecondSelectedModeInSimulation();

    public abstract void showNormalGameScreen();

    public abstract void showSimulateGameScreen();
}
