package kck.battleship.model.clases.Decorator;

public class NormalRankingDecorator extends RankingDecorator {

    private static final double EASY_MULTIPLIER = 1.5;

    public NormalRankingDecorator(IRanking ranking) {
        super(ranking);
    }

    @Override
    public void addPoints(int points) {
        super.addPoints((int)(points * EASY_MULTIPLIER));
    }

}
