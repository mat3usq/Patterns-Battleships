package kck.battleship.controller;

import kck.battleship.view.graphicView.GraphicView;
import kck.battleship.view.View;


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


