package kck.battleship.model.clases.Decorator;

public class HardRankingDecorator extends RankingDecorator {
    private static final double EASY_MULTIPLIER = 2;

    public HardRankingDecorator(IRanking ranking) {
        super(ranking);
    }

    @Override
    public void addPoints(int points) {
        super.addPoints((int)(points * EASY_MULTIPLIER));
    }
}
