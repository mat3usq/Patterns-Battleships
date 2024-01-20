package kck.battleship.model.clases.Decorator;

public class EasyRankingDecorator implements IRanking {
    private IRanking wrappedRanking;

    public EasyRankingDecorator(IRanking ranking) {
        this.wrappedRanking = ranking;
    }

    @Override
    public void addPoints(int points) {
        wrappedRanking.addPoints(points);
    }

    @Override
    public int getPoints() {
        return wrappedRanking.getPoints();
    }
}
