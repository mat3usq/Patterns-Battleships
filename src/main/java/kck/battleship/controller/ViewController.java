package kck.battleship.controller;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import kck.battleship.model.clases.BattleField;
import kck.battleship.model.clases.Player;
import kck.battleship.model.clases.Position;
import kck.battleship.model.clases.Ship;
import kck.battleship.model.types.TypesDirection;
import kck.battleship.view.graphicView.GraphicView;
import kck.battleship.view.textView.TextView;
import kck.battleship.view.View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ViewController {
    public static int choice;
    private static GraphicView graphicView;

    public ViewController(int x) {
        choice = x;

            graphicView = new GraphicView();
            graphicView.printHomePage();
            graphicView.waitForKeyHomePage();
            graphicView.chooseOption(0);
    }

    public static View getInstance() {
        return graphicView;
    }
}


