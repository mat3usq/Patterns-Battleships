package kck.battleship.controller;

import kck.battleship.model.clases.Ranking;
import kck.battleship.view.graphicView.GameScreen;
import kck.battleship.view.graphicView.GraphicView;
import kck.battleship.view.View;
import kck.battleship.view.graphicView.MainScreen;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;


public class ViewController {
    public final int choice;
    private static GraphicView graphicView;

    public ViewController(int x) {
        choice = x;
        graphicView = new GraphicView();
        graphicView.printHomePage();
        waitForKeyHomePage();
    }

    private void waitForKeyHomePage() {
        graphicView.homeScreen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        graphicView.printLoginPage();
                        addListenersOnMenu();
                        graphicView.homeScreen.setVisible(false);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        graphicView.printExit();
                        break;
                }
            }
        });
    }

    private void addListenersOnMenu() {
        graphicView.loginScreen.play.addActionListener(e -> {
            if (!graphicView.loginScreen.nicknameField.getText().isEmpty()) {
                GraphicView.name = graphicView.loginScreen.nicknameField.getText();
                graphicView.loginScreen.setVisible(false);
                graphicView.mainScreen = new MainScreen();
                graphicView.printMenuPage(0);

                addMenuActionsListeners();
                addMenuKeyListeners();

                addShopActionsListeners();
                addShopKeyListeners();

                addShopPopupActionsListeners();
                addShopPopupKeyListeners();

                addRulesActionsListeners();
                addRulesKeyListeners();

                addRankingActionsListeners();
                addRankingKeyListeners();
            }
        });
        graphicView.loginScreen.exit.addActionListener(e -> graphicView.printExit());
    }

    private void addMenuActionsListeners() {
        graphicView.mainScreen.menuPanel.playGame.addActionListener(ev -> {
            graphicView.printMenuPage(0);
            option(0);
        });

        graphicView.mainScreen.menuPanel.simulateGame.addActionListener(ev -> {
            graphicView.printMenuPage(1);
            option(1);
        });

        graphicView.mainScreen.menuPanel.shop.addActionListener(ev -> {
            graphicView.printMenuPage(2);
            option(2);
        });

        graphicView.mainScreen.menuPanel.rules.addActionListener(ev -> {
            graphicView.printMenuPage(3);
            option(3);
        });

        graphicView.mainScreen.menuPanel.ranking.addActionListener(ev -> {
            graphicView.printMenuPage(4);
            option(4);
        });

        graphicView.mainScreen.menuPanel.exit.addActionListener(ev -> {
            graphicView.printMenuPage(5);
            option(5);
        });
    }

    private void addMenuKeyListeners() {
        graphicView.mainScreen.menuPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        option(graphicView.menuSelected);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        option(5);
                        break;
                    case KeyEvent.VK_DOWN:
                        if (graphicView.menuSelected + 1 < graphicView.sizeOptions)
                            graphicView.printMenuPage(++graphicView.menuSelected);
                        break;
                    case KeyEvent.VK_UP:
                        if (graphicView.menuSelected - 1 >= 0)
                            graphicView.printMenuPage(--graphicView.menuSelected);
                        break;
                }
            }
        });
    }

    private void addShopActionsListeners() {
        graphicView.mainScreen.shopPanel.extraShip.addActionListener(ev -> {
            graphicView.printShopPage(0);
            graphicView.mainScreen.popup.setVisible(true);
            graphicView.mainScreen.popup.requestFocusInWindow();
        });

        graphicView.mainScreen.shopPanel.barrierShop.addActionListener(ev -> {
            graphicView.printShopPage(1);
            graphicView.mainScreen.popup.setVisible(true);
            graphicView.mainScreen.popup.requestFocusInWindow();
        });

        graphicView.mainScreen.shopPanel.backShop.addActionListener(ev -> {
            graphicView.mainScreen.menuPanel.setVisible(true);
            graphicView.mainScreen.shopPanel.setVisible(false);
            graphicView.printMenuPage(2);
        });
    }

    private void addShopKeyListeners() {
        graphicView.mainScreen.shopPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        graphicView.mainScreen.popup.setVisible(true);
                        graphicView.mainScreen.popup.requestFocusInWindow();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        graphicView.mainScreen.menuPanel.setVisible(true);
                        graphicView.mainScreen.shopPanel.setVisible(false);
                        graphicView.printMenuPage(2);
                        break;
                    case KeyEvent.VK_DOWN:
                        if (graphicView.shopSelected + 1 < graphicView.sizeOptionsShop)
                            graphicView.printShopPage(++graphicView.shopSelected);
                        break;
                    case KeyEvent.VK_UP:
                        if (graphicView.shopSelected - 1 >= 0)
                            graphicView.printShopPage(--graphicView.shopSelected);
                        break;
                }
            }
        });
    }

    private void addShopPopupActionsListeners() {
        graphicView.mainScreen.popup.okButton.addActionListener(ev -> {
            buyItemInShop();
            graphicView.mainScreen.shopPanel.requestFocusInWindow();
        });

        graphicView.mainScreen.popup.cancelButton.addActionListener(ev -> {
            graphicView.mainScreen.popup.setVisible(false);
            graphicView.mainScreen.shopPanel.requestFocusInWindow();
        });
    }

    private void addShopPopupKeyListeners() {
        graphicView.mainScreen.popup.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        buyItemInShop();
                        graphicView.mainScreen.shopPanel.requestFocusInWindow();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        graphicView.mainScreen.popup.setVisible(false);
                        graphicView.mainScreen.shopPanel.requestFocusInWindow();
                        break;
                }
            }
        });
    }

    private void addRulesActionsListeners() {
        graphicView.mainScreen.rules.backRules.addActionListener(ev -> {
            graphicView.mainScreen.menuPanel.setVisible(true);
            graphicView.mainScreen.rules.setVisible(false);
            graphicView.printMenuPage(3);
        });

        graphicView.mainScreen.rules.easyMode.addActionListener(ev -> {
            graphicView.printMode(0);
        });

        graphicView.mainScreen.rules.normalMode.addActionListener(ev -> {
            graphicView.printMode(1);
        });

        graphicView.mainScreen.rules.hardMode.addActionListener(ev -> {
            graphicView.printMode(2);
        });
    }

    private void addRulesKeyListeners() {
        graphicView.mainScreen.rules.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    graphicView.mainScreen.menuPanel.setVisible(true);
                    graphicView.mainScreen.rules.setVisible(false);
                    graphicView.printMenuPage(3);
                }
            }
        });
    }

    private void addRankingActionsListeners() {
        graphicView.mainScreen.ranking.backRanking.addActionListener(ev -> {
            graphicView.mainScreen.menuPanel.setVisible(true);
            graphicView.mainScreen.ranking.setVisible(false);
            graphicView.printMenuPage(4);
        });
    }

    private void addRankingKeyListeners() {
        graphicView.mainScreen.ranking.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    graphicView.mainScreen.menuPanel.setVisible(true);
                    graphicView.mainScreen.ranking.setVisible(false);
                    graphicView.printMenuPage(4);
                }
            }
        });
    }

    private void option(int selected) {
        switch (selected) {
            case 0 -> {
                try {
                    graphicView.game = Game.getInstance();
                    graphicView.game.setPlayerGame(GraphicView.name);
                    graphicView.mainScreen.setVisible(false);
                    graphicView.gameScreen = new GameScreen(false);
                    graphicView.game.addAllShips();
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case 1 -> {
                graphicView.mainScreen.setVisible(false);
                graphicView.gameScreen = new GameScreen(true);
                graphicView.gameScreen.setVisible(true);
                graphicView.gameScreen.battle.setVisible(true);
                graphicView.game = Game.getInstance();
                graphicView. game.setSimulateGame();
                graphicView.game.runSimulation();
            }
            case 2 -> {
                graphicView.printShop();
                graphicView.printShopPage(0);
            }
            case 3 -> graphicView.printRules();
            case 4 -> graphicView.printRanking(0);
            case 5 -> graphicView.printExit();
        }
    }

    private void buyItemInShop() {
        String s;
        graphicView.mainScreen.popup.cancelButton.setVisible(false);
        graphicView.mainScreen.popup.okButton.setVisible(false);
        graphicView.mainScreen.popup.confrimLabel.setVisible(true);

        if (graphicView.shopSelected == 0)
            s = Ranking.enoughPoints(GraphicView.name, 500, graphicView.shopSelected, true);
        else
            s = Ranking.enoughPoints(GraphicView.name, 300, graphicView.shopSelected, true);

        graphicView.mainScreen.popup.confrimLabel.setText(Objects.requireNonNullElse(s, "Pomyslnie zakupiono wybrana rzecz!"));

        Timer timer = new Timer(2000, e -> {
            graphicView.mainScreen.popup.setVisible(false);
            graphicView.mainScreen.popup.confrimLabel.setVisible(false);
            graphicView.mainScreen.popup.cancelButton.setVisible(true);
            graphicView.mainScreen.popup.okButton.setVisible(true);
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static boolean addListenersOnPopupRandomArrangedShips() {
        AtomicBoolean isOkPressed = new AtomicBoolean();
        graphicView.gameScreen.popup.okButton.addActionListener(e -> addRandomShipsListenersOK(isOkPressed));
        graphicView.gameScreen.popup.cancelButton.addActionListener(e -> addRandomShipsListenersCANCEL(isOkPressed));
        graphicView.gameScreen.popup.setVisible(true);
        return isOkPressed.get();
    }

    private static void addRandomShipsListenersOK(AtomicBoolean isOkPressed) {
        isOkPressed.set(true);
        graphicView.gameScreen.setVisible(true);
        graphicView.gameScreen.popup.setVisible(false);
        graphicView.gameScreen.battle.setVisible(true);
        graphicView.showOptionToPlay();
    }

    private static void addRandomShipsListenersCANCEL(AtomicBoolean isOkPressed) {
        isOkPressed.set(false);
        graphicView.gameScreen.popup.setVisible(false);

        graphicView.gameScreen.manage.game.addActionListener(ev -> {
            graphicView.gameScreen.manage.game.setVisible(false);
            graphicView.gameScreen.manage.setVisible(false);
            graphicView.gameScreen.battle.setVisible(true);
            graphicView.printBoards(graphicView.game.getFirstPlayer(), graphicView.game.getSecondPlayer());
            graphicView.showOptionToPlay();
        });
    }

    public static View getInstance() {
        return graphicView;
    }
}


