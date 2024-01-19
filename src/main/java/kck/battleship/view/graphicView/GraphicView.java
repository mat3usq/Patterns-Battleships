package kck.battleship.view.graphicView;

import kck.battleship.controller.Game;
import kck.battleship.controller.GameException;
import kck.battleship.controller.ViewController;
import kck.battleship.model.clases.*;
import kck.battleship.model.types.TypesDirection;
import kck.battleship.model.types.TypesField;
import kck.battleship.view.View;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphicView extends View {
    public int menuSelected = 0;
    public int shopSelected = 0;
    public final int sizeOptions = 6;
    public final int sizeOptionsShop = 2;
    public HomeScreen homeScreen;
    public LoginScreen loginScreen;
    public MainScreen mainScreen;
    public GameScreen gameScreen;
    public Game game;

    public static String name;

    @Override
    public void printHomePage() {
        homeScreen = new HomeScreen();
    }

    @Override
    public void printLoginPage() {
        loginScreen = new LoginScreen();
    }

    @Override
    public void printExit() {
        if (homeScreen != null)
            homeScreen.setVisible(false);
        if (loginScreen != null)
            loginScreen.setVisible(false);
        if (mainScreen != null)
            mainScreen.setVisible(false);
        if (gameScreen != null)
            gameScreen.setVisible(false);

        new ExitScreen().setVisible(true);
        Timer exitTimer = new Timer(5000, ev -> System.exit(0));
        exitTimer.setRepeats(false);
        exitTimer.start();
    }

    @Override
    public void printMenuPage(int selected) {
        this.menuSelected = selected;
        mainScreen.menuPanel.requestFocusInWindow();
        mainScreen.menuPanel.upLabel.setVisible(false);
        mainScreen.menuPanel.leftLabel.setVisible(true);
        mainScreen.menuPanel.rightLabel.setVisible(true);
        switch (selected) {
            case 0:
                mainScreen.menuPanel.leftLabel.setBounds(185, 220, 30, 30);
                mainScreen.menuPanel.rightLabel.setBounds(385, 220, 30, 30);
                break;
            case 1:
                mainScreen.menuPanel.leftLabel.setBounds(185, 320, 30, 30);
                mainScreen.menuPanel.rightLabel.setBounds(385, 320, 30, 30);
                break;
            case 2:
                mainScreen.menuPanel.leftLabel.setBounds(185, 430, 30, 30);
                mainScreen.menuPanel.rightLabel.setBounds(385, 430, 30, 30);
                break;
            case 3:
                mainScreen.menuPanel.leftLabel.setBounds(185, 550, 30, 30);
                mainScreen.menuPanel.rightLabel.setBounds(385, 550, 30, 30);
                break;
            case 4:
                mainScreen.menuPanel.upLabel.setBounds(520, 120, 30, 30);
                mainScreen.menuPanel.upLabel.setVisible(true);
                mainScreen.menuPanel.leftLabel.setVisible(false);
                mainScreen.menuPanel.rightLabel.setVisible(false);
                break;
            case 5:
                mainScreen.menuPanel.leftLabel.setBounds(185, 670, 30, 30);
                mainScreen.menuPanel.rightLabel.setBounds(385, 670, 30, 30);
                break;
        }
    }

    public void printShopPage(int selected) {
        this.shopSelected = selected;
        switch (selected) {
            case 0:
                mainScreen.shopPanel.leftLabel.setBounds(125, 360, 30, 30);
                mainScreen.shopPanel.rightLabel.setBounds(445, 360, 30, 30);
                break;
            case 1:
                mainScreen.shopPanel.leftLabel.setBounds(105, 620, 30, 30);
                mainScreen.shopPanel.rightLabel.setBounds(490, 620, 30, 30);
                break;
        }
    }

    @Override
    public void printRules() {
        mainScreen.menuPanel.setVisible(false);
        mainScreen.rules.setVisible(true);
        mainScreen.rules.requestFocusInWindow();
    }

    @Override
    public void printError(String message) {
        gameScreen.manage.errorPage.setVisible(true);
        gameScreen.manage.errorLabel.setText(message);
        Timer timer = new Timer(2000, e1 -> {
            gameScreen.manage.errorPage.setVisible(false);
            gameScreen.manage.errorLabel.setText("");
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void printShot(Player player, Position position, boolean isHit) {
        if (player.getName().equals("Wróg") || player.getName().equals("Enemy2")) {
            if (isHit) {
                gameScreen.battle.shotLabelDefender.setText(position.toString(position));
                gameScreen.battle.shotImgDefender.setVisible(true);
            } else {
                gameScreen.battle.missLabelDefender.setText(position.toString(position));
                gameScreen.battle.missImgDefender.setVisible(true);
            }
        } else {
            if (isHit) {
                gameScreen.battle.shotLabelAttacker.setText(position.toString(position));
                gameScreen.battle.shotImgAttacker.setVisible(true);
            } else {
                gameScreen.battle.missLabelAttacker.setText(position.toString(position));
                gameScreen.battle.missImgAttacker.setVisible(true);
            }
        }
    }

    @Override
    public void printWinner(Player player, Ranking rank) {
        mainScreen.setVisible(true);
        mainScreen.menuPanel.setVisible(false);
        gameScreen.setVisible(false);
        mainScreen.results.setVisible(true);
        if (player.getName().equals("Enemy"))
            mainScreen.results.winnerName.setText("Zwyciezca: Computer 1");
        else if (player.getName().equals("Enemy2"))
            mainScreen.results.winnerName.setText("Zwyciezca: Computer 2");
        else
            mainScreen.results.winnerName.setText("Zwyciezca: " + player.getName());

        if (!player.isAI())
            mainScreen.results.points.setText("Twoj wynik: " + rank.getPoints());
        else
            mainScreen.results.points.setText("");

        Timer timer = new Timer(50, e -> {
            mainScreen.results.setVisible(false);
            mainScreen.menuPanel.setVisible(true);
            printMenuPage(0);
        });
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void printShip(Ship ship) {
        gameScreen.manage.shipLabel.setText("→ " + ship.getName() + ": ");
        gameScreen.manage.imgShip.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship" + ship.getLength() + ".png"))));
    }

    @Override
    public void printBoards(Player firstPlayer, Player secondPlayer) {
        ArrayList<Ship> firstPlayerShips = firstPlayer.getShips();
        ArrayList<Ship> secondPlayerShips = secondPlayer.getShips();
        BattleField firstBattleField = firstPlayer.getBattleField();
        BattleField secondBattleField = secondPlayer.getBattleField();
        ImageIcon wreck = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/wreck.gif")));
        ImageIcon fire = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/fireButton.gif")));
        ImageIcon water = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/grayButton.gif")));
        ImageIcon ship1_Hori = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship1_hori.png")));
        ImageIcon ship1_Vert = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship1_vert.png")));
        ImageIcon shipHeadLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipHeadLeft.png")));
        ImageIcon shipHeadTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipHeadTop.png")));
        ImageIcon shipBodyLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipBodyLeft.png")));
        ImageIcon shipBodyTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipBodyTop.png")));
        ImageIcon shipFootLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipFootLeft.png")));
        ImageIcon shipFootTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipFootTop.png")));

        for (Ship ship : firstPlayerShips) {
            int x = ship.getPosition().getRow();
            int y = ship.getPosition().getColumn();
            int dim = ship.getLength();
            int dir;
            if (ship.getDirection() == TypesDirection.HORIZONTAL)
                dir = 0;
            else dir = 1;

            if (dim == 1) {
                gameScreen.battle.firstMap.jButtons[x][y].setEnabled(false);
                if (dir == 0)
                    gameScreen.battle.firstMap.jButtons[x][y].setDisabledIcon(ship1_Hori);
                else
                    gameScreen.battle.firstMap.jButtons[x][y].setDisabledIcon(ship1_Vert);
            } else {
                if (dir == 0) {// horizontal
                    // Ship Head
                    gameScreen.battle.firstMap.jButtons[x][y].setDisabledIcon(shipHeadLeft);
                    gameScreen.battle.firstMap.jButtons[x][y].setEnabled(false);
                    // Ship Body
                    for (int i = 1; i < dim - 1; i++) {
                        gameScreen.battle.firstMap.jButtons[x][y + i].setDisabledIcon(shipBodyLeft);
                        gameScreen.battle.firstMap.jButtons[x][y + i].setEnabled(false);
                    }
                    // Ship Foot
                    gameScreen.battle.firstMap.jButtons[x][y + dim - 1].setDisabledIcon(shipFootLeft);
                    gameScreen.battle.firstMap.jButtons[x][y + dim - 1].setEnabled(false);
                } else { // vertical
                    // Ship Head
                    gameScreen.battle.firstMap.jButtons[x][y].setDisabledIcon(shipHeadTop);
                    gameScreen.battle.firstMap.jButtons[x][y].setEnabled(false);
                    // Ship Body
                    for (int i = 1; i < dim - 1; i++) {
                        gameScreen.battle.firstMap.jButtons[x + i][y].setDisabledIcon(shipBodyTop);
                        gameScreen.battle.firstMap.jButtons[x + i][y].setEnabled(false);
                    }
                    // Ship Foot
                    gameScreen.battle.firstMap.jButtons[x + dim - 1][y].setDisabledIcon(shipFootTop);
                    gameScreen.battle.firstMap.jButtons[x + dim - 1][y].setEnabled(false);
                }
            }
        }

        for (int i = 0; i < BattleField.getLength(); i++) {
            for (int j = 0; j < BattleField.getLength(); j++) {
                if (firstBattleField.getbattleField()[i][j] == TypesField.HIT.name) {
                    gameScreen.battle.firstMap.jButtons[i][j].setDisabledIcon(fire);
                    gameScreen.battle.firstMap.jButtons[i][j].setEnabled(false);
                } else if (firstBattleField.getbattleField()[i][j] == TypesField.MISS.name) {
                    gameScreen.battle.firstMap.jButtons[i][j].setDisabledIcon(water);
                    gameScreen.battle.firstMap.jButtons[i][j].setEnabled(false);
                }

                if (secondBattleField.getbattleField()[i][j] == TypesField.HIT.name) {
                    gameScreen.battle.secondMap.jButtons[i][j].setDisabledIcon(fire);
                    gameScreen.battle.secondMap.jButtons[i][j].setEnabled(false);
                } else if (secondBattleField.getbattleField()[i][j] == TypesField.MISS.name) {
                    gameScreen.battle.secondMap.jButtons[i][j].setDisabledIcon(water);
                    gameScreen.battle.secondMap.jButtons[i][j].setEnabled(false);
                }
            }
        }

        if (firstPlayer.isAI())
            showEnemyShipsSimulate(secondPlayerShips);
        else showEnemyShips(secondPlayerShips);

        for (Ship ship : firstPlayerShips) {
            if (firstBattleField.isShipSunk(ship)) {
                int startX = ship.getPosition().getRow();
                int startY = ship.getPosition().getColumn();
                TypesDirection direction = ship.getDirection();

                for (int k = 0; k < ship.getLength(); k++) {
                    int X = direction == TypesDirection.HORIZONTAL ? startX : startX + k;
                    int Y = direction == TypesDirection.HORIZONTAL ? startY + k : startY;

                    gameScreen.battle.firstMap.jButtons[X][Y].setDisabledIcon(wreck);
                    gameScreen.battle.firstMap.jButtons[X][Y].setEnabled(false);
                }
            }
        }

        for (Ship ship : secondPlayerShips) {
            if (secondBattleField.isShipSunk(ship)) {
                int startX = ship.getPosition().getRow();
                int startY = ship.getPosition().getColumn();
                TypesDirection direction = ship.getDirection();

                for (int k = 0; k < ship.getLength(); k++) {
                    int X = direction == TypesDirection.HORIZONTAL ? startX : startX + k;
                    int Y = direction == TypesDirection.HORIZONTAL ? startY + k : startY;

                    gameScreen.battle.secondMap.jButtons[X][Y].setDisabledIcon(wreck);
                    gameScreen.battle.secondMap.jButtons[X][Y].setEnabled(false);
                }
            }
        }
    }

    private void showEnemyShips(ArrayList<Ship> secondPlayerShips) {
        ImageIcon ship1_Hori = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship1_hori.png")));
        ImageIcon ship1_Vert = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship1_vert.png")));
        ImageIcon shipHeadLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipHeadLeft.png")));
        ImageIcon shipHeadTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipHeadTop.png")));
        ImageIcon shipBodyLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipBodyLeft.png")));
        ImageIcon shipBodyTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipBodyTop.png")));
        ImageIcon shipFootLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipFootLeft.png")));
        ImageIcon shipFootTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipFootTop.png")));

        for (Ship ship : secondPlayerShips) {
            int x = ship.getPosition().getRow();
            int y = ship.getPosition().getColumn();
            int dim = ship.getLength();
            int dir;
            if (ship.getDirection() == TypesDirection.HORIZONTAL)
                dir = 0;
            else dir = 1;

            if (dim == 1) {
                if (dir == 0) {
                    gameScreen.battle.secondMap.jButtons[x][y].setIcon(ship1_Hori);
                } else {
                    gameScreen.battle.secondMap.jButtons[x][y].setIcon(ship1_Vert);
                }
            } else {
                if (dir == 0) {// horizontal
                    gameScreen.battle.secondMap.jButtons[x][y].setIcon(shipHeadLeft);
                    for (int i = 1; i < dim - 1; i++) {
                        gameScreen.battle.secondMap.jButtons[x][y + i].setIcon(shipBodyLeft);
                    }
                    // Ship Foot
                    gameScreen.battle.secondMap.jButtons[x][y + dim - 1].setIcon(shipFootLeft);
                } else { // vertical
                    gameScreen.battle.secondMap.jButtons[x][y].setIcon(shipHeadTop);
                    for (int i = 1; i < dim - 1; i++) {
                        gameScreen.battle.secondMap.jButtons[x + i][y].setIcon(shipBodyTop);
                    }
                    gameScreen.battle.secondMap.jButtons[x + dim - 1][y].setIcon(shipFootTop);
                }
            }
        }
    }

    private void showEnemyShipsSimulate(ArrayList<Ship> secondPlayerShips) {
        ImageIcon ship1_Hori = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship1_hori.png")));
        ImageIcon ship1_Vert = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship1_vert.png")));
        ImageIcon shipHeadLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipHeadLeft.png")));
        ImageIcon shipHeadTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipHeadTop.png")));
        ImageIcon shipBodyLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipBodyLeft.png")));
        ImageIcon shipBodyTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipBodyTop.png")));
        ImageIcon shipFootLeft = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipFootLeft.png")));
        ImageIcon shipFootTop = new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/shipFootTop.png")));

        for (Ship ship : secondPlayerShips) {
            int x = ship.getPosition().getRow();
            int y = ship.getPosition().getColumn();
            int dim = ship.getLength();
            int dir;
            if (ship.getDirection() == TypesDirection.HORIZONTAL)
                dir = 0;
            else dir = 1;

            if (dim == 1) {
                if (dir == 0) {
                    gameScreen.battle.secondMap.jButtons[x][y].setDisabledIcon(ship1_Hori);
                } else {
                    gameScreen.battle.secondMap.jButtons[x][y].setDisabledIcon(ship1_Vert);
                }
            } else {
                if (dir == 0) {// horizontal
                    gameScreen.battle.secondMap.jButtons[x][y].setDisabledIcon(shipHeadLeft);
                    for (int i = 1; i < dim - 1; i++) {
                        gameScreen.battle.secondMap.jButtons[x][y + i].setDisabledIcon(shipBodyLeft);
                    }
                    // Ship Foot
                    gameScreen.battle.secondMap.jButtons[x][y + dim - 1].setDisabledIcon(shipFootLeft);
                } else { // vertical
                    gameScreen.battle.secondMap.jButtons[x][y].setDisabledIcon(shipHeadTop);
                    for (int i = 1; i < dim - 1; i++) {
                        gameScreen.battle.secondMap.jButtons[x + i][y].setDisabledIcon(shipBodyTop);
                    }
                    gameScreen.battle.secondMap.jButtons[x + dim - 1][y].setDisabledIcon(shipFootTop);
                }
            }
        }
    }

    @Override
    public void printBoard(BattleField battleField, ArrayList<Ship> ships) {
        for (Ship ship : ships) {
            if (ship.getPosition() != null)
                printShip(ship.getPosition().getRow(), ship.getPosition().getColumn(), ship.getLength(), ship.getDirection());
            else break;
        }
    }

    private void printShip(int x, int y, int dim, TypesDirection typesDirection) {
        int dir;
        if (typesDirection == TypesDirection.HORIZONTAL)
            dir = 0;
        else
            dir = 1;

        if (dim == 1) {
            gameScreen.manage.map.jButtons[x][y].setEnabled(false);
            if (dir == 0)
                gameScreen.manage.map.jButtons[x][y].setDisabledIcon(new ImageIcon(
                        Objects.requireNonNull(getClass().getResource("/ship/ship1_hori.png"))));
            else
                gameScreen.manage.map.jButtons[x][y].setDisabledIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/ship1_vert.png"))));
        } else {
            ImageIcon shipHeadLeft = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/ship/shipHeadLeft.png")));
            ImageIcon shipHeadTop = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/ship/shipHeadTop.png")));
            ImageIcon shipBodyLeft = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/ship/shipBodyLeft.png")));
            ImageIcon shipBodyTop = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/ship/shipBodyTop.png")));
            ImageIcon shipFootLeft = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/ship/shipFootLeft.png")));
            ImageIcon shipFootTop = new ImageIcon(
                    Objects.requireNonNull(getClass().getResource("/ship/shipFootTop.png")));
            if (dir == 0) {// horizontal
                // Ship Head
                gameScreen.manage.map.jButtons[x][y].setDisabledIcon(shipHeadLeft);
                gameScreen.manage.map.jButtons[x][y].setEnabled(false);
                // Ship Body
                for (int i = 1; i < dim - 1; i++) {
                    gameScreen.manage.map.jButtons[x][y + i].setDisabledIcon(shipBodyLeft);
                    gameScreen.manage.map.jButtons[x][y + i].setEnabled(false);
                }
                // Ship Foot
                gameScreen.manage.map.jButtons[x][y + dim - 1].setDisabledIcon(shipFootLeft);
                gameScreen.manage.map.jButtons[x][y + dim - 1].setEnabled(false);
            } else { // vertical
                // Ship Head
                gameScreen.manage.map.jButtons[x][y].setDisabledIcon(shipHeadTop);
                gameScreen.manage.map.jButtons[x][y].setEnabled(false);
                // Ship Body
                for (int i = 1; i < dim - 1; i++) {
                    gameScreen.manage.map.jButtons[x + i][y].setDisabledIcon(shipBodyTop);
                    gameScreen.manage.map.jButtons[x + i][y].setEnabled(false);
                }
                // Ship Foot
                gameScreen.manage.map.jButtons[x + dim - 1][y].setDisabledIcon(shipFootTop);
                gameScreen.manage.map.jButtons[x + dim - 1][y].setEnabled(false);
            }
        }
    }

    @Override
    public void showOptionToManuallyAddShip() {
        gameScreen.setVisible(true);
        gameScreen.manage.setVisible(true);
    }

    @Override
    public void showOptionToPlay() {
        gameScreen.battle.info.setToolTipText("<html>" +
                "<body style='width: 250px; background-color: #D3D3D3; font-size: 10px; text-align: center; margin: 20px;'>" +
                "<h2 style='color: navy;'>Witaj Piracie!</h2>" +
                "<br><ol><li><p>W tej grze jestes podczas bitwy!</p></li>" +
                "<br><li><p>Aby strzelic w plansze przeciwnika wystarczy kliknac na wybrane pole.</p></li>" +
                "<br><li><p>Po kliknieciu pojawi sie odpowiedni komunikat oraz zmiany na planszy.</p></li>" +
                "<br><li><p>Na końcu bitwy zostanie pokazany zwycięzca.</p></li>" +
                "<br><li><p><b>Po skonczonej bitwie zostaniesz przekierowany do menu.</b></p></li>" +
                "</ol></body>" +
                "</html>");
    }

    @Override
    public void showOptionToSimulatedGame() {
        gameScreen.battle.info.setToolTipText("<html>" +
                "<body style='width: 250px; background-color: #D3D3D3; font-size: 10px; text-align: center; margin: 20px;'>" +
                "<h2 style='color: navy;'>Witaj Piracie!</h2>" +
                "<br><ol><li><p>W tej grze nie możesz strzelać, ale możesz za to obserwować bitwę!</p></li>" +
                "<br><li><p>Na końcu bitwy zostanie pokazany zwycięzca.</p></li>" +
                "<br><li><p><b>Symulacja skończy się po odpowiednim komunikacie.</b></p></li>" +
                "</ol></body>" +
                "</html>");
    }

    @Override
    public void printRanking(int page) {
        mainScreen.ranking.updateRanking();
        mainScreen.ranking.setVisible(true);
        mainScreen.menuPanel.setVisible(false);
        mainScreen.ranking.requestFocusInWindow();
    }

    @Override
    public void printShop() {
        mainScreen.shopPanel.setVisible(true);
        mainScreen.menuPanel.setVisible(false);
        mainScreen.shopPanel.requestFocusInWindow();
    }

    @Override
    public void printBarrier(Player defender) {
        gameScreen.battle.barrier.setVisible(true);
        if (defender.getDurabilityForceField() == 0)
            gameScreen.battle.countBarrier.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/ship/fireButton.gif"))));
        else gameScreen.battle.countBarrier.setText(String.valueOf(defender.getDurabilityForceField()));
    }

    @Override
    public boolean isRandomShipsArranged() {
        return ViewController.addListenersOnPopupRandomArrangedShips();
    }

    @Override
    public void addShipsVisually(BattleField battleField, Ship ship, ArrayList<Ship> ships) {
        gameScreen.manage.currentShip = ship;
        gameScreen.manage.ships = ships;
        gameScreen.manage.battleField = battleField;
        printBoard(battleField, ships);
        printShip(ship);
    }

    @Override
    public Position getPositionToShot(Player defender, Player attacker) {
        Position shoot = null;
        try {
            shoot = new Position(gameScreen.battle.x, gameScreen.battle.y);
        } catch (Exception e) {
            printError(e.getMessage());
        }
        return shoot;
    }

    public void playTurn() {
        game.playRound();
    }

    @Override
    public void delayForGameplay() {
        Timer timer = new Timer(1000, e -> {
            gameScreen.battle.missImgAttacker.setVisible(false);
            gameScreen.battle.shotImgAttacker.setVisible(false);
            gameScreen.battle.missImgDefender.setVisible(false);
            gameScreen.battle.shotImgDefender.setVisible(false);
            gameScreen.battle.barrier.setVisible(false);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
