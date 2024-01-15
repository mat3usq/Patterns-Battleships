package kck.battleship.controller;

import kck.battleship.model.clases.Player;
import kck.battleship.model.clases.Position;
import kck.battleship.model.clases.Ranking;
import kck.battleship.model.types.TypesField;
import kck.battleship.view.View;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private Player firstPlayer = null;
    private Ranking firstPlayerRank;
    private Player secondPlayer = null;
    private final View view = ViewController.getInstance();
    public  boolean hasExtraShip;
    private static Game gameInstance;

    private Game() {
    }

    public static Game getInstance() {
        if (gameInstance == null)
            gameInstance = new Game();
        return gameInstance;
    }

    public void setPlayerGame(String name) {
        firstPlayer = new Player(name);
        firstPlayer.getShop();
        hasExtraShip = firstPlayer.hasAirCrafter;
        firstPlayerRank = new Ranking(firstPlayer, 0);
        secondPlayer = new Player("Wróg", true);
    }

    public void setSimulateGame() {
        firstPlayer = new Player("Enemy", true);
        secondPlayer = new Player("Enemy2", true);
        firstPlayerRank = null;
    }

    public boolean playTurn(Player attacker, Player defender, Boolean reverse) {
        Position shoot = null;
        boolean isHit;

        if (attacker.isAI() && defender.isAI())
            view.showOptionToSimulatedGame();
        else
            view.showOptionToPlay();

        if (attacker.areShipsStillSailing()) {
            if (defender.getDurabilityForceField() > 0) {
                defender.setDurabilityForceField(defender.getDurabilityForceField() - 1);
                view.printBarrier(defender);
            } else {
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
                    shoot = view.getPositionToShot(defender, attacker);
                    defender.addShoot(shoot);
                } catch (GameException e) {
                    throw new RuntimeException(e);
                }

                isHit = defender.getBattleField().at(shoot) == TypesField.HIT.name;

                if (isHit) {
                    attacker.registerShoot(shoot);
                    updatePlayerPoints(attacker);
                }

                view.printShot(attacker, shoot, isHit);
            }

            view.delayForGameplay();

            if (attacker.isAI() && defender.isAI() && !reverse)
                view.printBoards(attacker, defender);
            else if (attacker.isAI() && defender.isAI() && reverse)
                view.printBoards(defender, attacker);
            else if (!attacker.isAI())
                view.printBoards(attacker, defender);
            else if (!defender.isAI())
                view.printBoards(defender, attacker);

            return true;
        } else return false;
    }

    public void playRound() {
        boolean attacker = playTurn(firstPlayer, secondPlayer, false);
        if (attacker) {
            Timer timer = new Timer(10, e -> playTurn(secondPlayer, firstPlayer, false));
            timer.setRepeats(false);
            timer.start();
        }

        if (!firstPlayer.areShipsStillSailing() || !secondPlayer.areShipsStillSailing()) {
            saveRanking();
            printResultGame();
        }
    }

    public void runSimulation() {
        new GameSimulationWorker().execute();
    }

    private class GameSimulationWorker extends SwingWorker<Void, Void> {
        @Override
        protected Void doInBackground() throws Exception {
            addAllShips();
            playSimulateGame();
            return null;
        }
    }

    private void playSimulateGame() throws InterruptedException {
        boolean attacker = playTurn(firstPlayer, secondPlayer, false);
        Thread.sleep(15);

        if (attacker)
            playTurn(secondPlayer, firstPlayer, true);

        Thread.sleep(15);

        if (!firstPlayer.areShipsStillSailing() || !secondPlayer.areShipsStillSailing())
            printResultGame();
        else playSimulateGame();
    }

    private void updatePlayerPoints(Player player) {
        if (!player.isAI()) {
            long diff = new Date().getTime() - player.getLastShootTime().getTime();
            firstPlayerRank.addPoints((int) (100 / (diff / 400.0)));
            player.setLastShootTime(new Date());
        }
    }

    public void addAllShips() throws IOException, InterruptedException {
        if (bothPlayersAreAI()) {
            addShipsForAIPlayers();
        } else {
            boolean random = view.isRandomShipsArranged();
            if (random)
                firstPlayer.randAddShips();
            else
                firstPlayer.addShips();

            secondPlayer.addShips();

            if (random)
                view.printBoards(getFirstPlayer(), getSecondPlayer());
        }
    }

    private boolean bothPlayersAreAI() {
        return firstPlayer.isAI() && secondPlayer.isAI();
    }

    private void addShipsForAIPlayers() throws IOException {
        firstPlayer.addShips();
        secondPlayer.addShips();
    }

    public void printResultGame() {
        if (firstPlayer.shipsLeft() > secondPlayer.shipsLeft())
            view.printWinner(firstPlayer, firstPlayerRank);
        else
            view.printWinner(secondPlayer, firstPlayerRank);
    }

    public void saveRanking() {
        if (!firstPlayer.isAI())
            firstPlayerRank.save();
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }
}
