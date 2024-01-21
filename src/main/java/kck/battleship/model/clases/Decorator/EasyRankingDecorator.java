package kck.battleship.model.clases.Decorator;


public class EasyRankingDecorator extends RankingDecorator {
    private static final double EASY_MULTIPLIER = 1;

    public EasyRankingDecorator(IRanking ranking) {
        super(ranking);
    }

    @Override
    public void addPoints(int points) {
        super.addPoints((int)(points * EASY_MULTIPLIER));
    }
}
