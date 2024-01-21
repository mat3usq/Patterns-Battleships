package kck.battleship.model.clases.Decorator;

public abstract class RankingDecorator implements IRanking {
    protected IRanking decoratedRanking;

    public RankingDecorator(IRanking ranking) {
        this.decoratedRanking = ranking;
    }

    @Override
    public void addPoints(int points) {
        decoratedRanking.addPoints(points);
    }

    @Override
    public int getPoints() {
        return decoratedRanking.getPoints();
    }
}
