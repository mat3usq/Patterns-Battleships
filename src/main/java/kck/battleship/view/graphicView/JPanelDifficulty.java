package kck.battleship.view.graphicView;

import kck.battleship.model.types.TypesShips;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class JPanelDifficulty extends JPanelBG {
    public Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    public JButton backMenu;
    public JButton firstComputerEasyMode;
    public JButton firstComputerNormalMode;
    public JButton firstComputerHardMode;
    public JButton secondComputerEasyMode;
    public JButton secondComputerNormalMode;
    public JButton secondComputerHardMode;
    public JLabel modesTitle;
    public JLabel firstComputerTitle;
    public JLabel secondComputerTitle;
    public JLabel firstComputerUpLabel;
    public JLabel secondComputerUpLabel;
    public JButton game;

    public JPanelDifficulty() {
        super(Toolkit.getDefaultToolkit()
                .createImage(MainScreen.class.getResource("/backgroundModes.png")));

        this.setBounds(0, 0, 600, 800);

        ImageIcon titleImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/titleModes.png")));
        modesTitle = new JLabel(titleImg);
        modesTitle.setBounds(100, 20, 400, 200);
        this.add(modesTitle);

        ImageIcon backModesImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/wroc.png")));
//        ImageIcon playViewImgHover = new ImageIcon(Objects.requireNonNull(getClass().getResource("/menu.png")));
        backMenu = new JButton(backModesImg);
//        play.setRolloverIcon(playViewImgHover);
        backMenu.setBorder(null);
        backMenu.setOpaque(false);
        backMenu.setBorderPainted(false);
        backMenu.setContentAreaFilled(false);
        backMenu.setCursor(cursor);
        backMenu.setText("backMenu");
        this.add(backMenu, 0);
        backMenu.setBounds(20, 20, 100, 100);

        ImageIcon easyModeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/easyMode.png")));
        ImageIcon normalModeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/normalMode.png")));
        ImageIcon hardModeImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/hardMode.png")));
        ImageIcon upArrow = new ImageIcon(Objects.requireNonNull(getClass().getResource("/arrowUp.png")));

        firstComputerEasyMode = new JButton(easyModeImg);
        firstComputerEasyMode.setBorder(null);
        firstComputerEasyMode.setOpaque(false);
        firstComputerEasyMode.setBorderPainted(false);
        firstComputerEasyMode.setContentAreaFilled(false);
        firstComputerEasyMode.setCursor(cursor);
        firstComputerEasyMode.setText("easyMode");

        this.add(firstComputerEasyMode, 1);
        firstComputerEasyMode.setBounds(70, 300, 150, 86);

        firstComputerNormalMode = new JButton(normalModeImg);
        firstComputerNormalMode.setBorder(null);
        firstComputerNormalMode.setOpaque(false);
        firstComputerNormalMode.setBorderPainted(false);
        firstComputerNormalMode.setContentAreaFilled(false);
        firstComputerNormalMode.setCursor(cursor);
        firstComputerNormalMode.setText("normalMode");

        this.add(firstComputerNormalMode, 2);
        firstComputerNormalMode.setBounds(230, 300, 150, 86);

        firstComputerHardMode = new JButton(hardModeImg);
        firstComputerHardMode.setBorder(null);
        firstComputerHardMode.setOpaque(false);
        firstComputerHardMode.setBorderPainted(false);
        firstComputerHardMode.setContentAreaFilled(false);
        firstComputerHardMode.setCursor(cursor);
        firstComputerHardMode.setText("hardMode");

        this.add(firstComputerHardMode, 3);
        firstComputerHardMode.setBounds(390, 300, 150, 86);

        firstComputerUpLabel = new JLabel(upArrow);
        this.add(firstComputerUpLabel, 4);

        firstComputerTitle = new JLabel("Computer");
        firstComputerTitle.setForeground(Color.BLACK);
        firstComputerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        firstComputerTitle.setBounds(242, 250, 500, 50);
        this.add(firstComputerTitle);


        secondComputerEasyMode = new JButton(easyModeImg);
        secondComputerEasyMode.setBorder(null);
        secondComputerEasyMode.setOpaque(false);
        secondComputerEasyMode.setBorderPainted(false);
        secondComputerEasyMode.setContentAreaFilled(false);
        secondComputerEasyMode.setCursor(cursor);
        secondComputerEasyMode.setText("easyMode");

        this.add(secondComputerEasyMode, 5);
        secondComputerEasyMode.setBounds(70, 470, 150, 86);

        secondComputerNormalMode = new JButton(normalModeImg);
        secondComputerNormalMode.setBorder(null);
        secondComputerNormalMode.setOpaque(false);
        secondComputerNormalMode.setBorderPainted(false);
        secondComputerNormalMode.setContentAreaFilled(false);
        secondComputerNormalMode.setCursor(cursor);
        secondComputerNormalMode.setText("normalMode");

        this.add(secondComputerNormalMode, 6);
        secondComputerNormalMode.setBounds(230, 470, 150, 86);

        secondComputerHardMode = new JButton(hardModeImg);
        secondComputerHardMode.setBorder(null);
        secondComputerHardMode.setOpaque(false);
        secondComputerHardMode.setBorderPainted(false);
        secondComputerHardMode.setContentAreaFilled(false);
        secondComputerHardMode.setCursor(cursor);
        secondComputerHardMode.setText("hardMode");

        this.add(secondComputerHardMode, 7);
        secondComputerHardMode.setBounds(390, 470, 150, 86);

        secondComputerUpLabel = new JLabel(upArrow);
        this.add(secondComputerUpLabel, 8);

        secondComputerTitle = new JLabel("Computer 2");
        secondComputerTitle.setForeground(Color.BLACK);
        secondComputerTitle.setFont(new Font("Arial", Font.BOLD, 24));
        secondComputerTitle.setBounds(237, 420, 500, 50);
        this.add(secondComputerTitle);

        ImageIcon playGameImg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/bitwa.png")));
        game = new JButton(playGameImg);
        game.setBorder(null);
        game.setOpaque(false);
        game.setBorderPainted(false);
        game.setContentAreaFilled(false);
        game.setCursor(cursor);
        game.setText("playGame");
        game.setBounds(215, 635, 175, 100);
        this.add(game, 9);
    }
}
