package kck.battleship.model.clases;

import kck.battleship.model.clases.Player;

public class RankingMemento {
    private Player player;
    private int points;

    public RankingMemento(Player player, int points) {
        this.player = player;
        this.points = points;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoints() {
        return points;
    }
}
