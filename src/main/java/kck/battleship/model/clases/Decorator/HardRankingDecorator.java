package kck.battleship.model.clases.Decorator;

public class HardRankingDecorator implements IRanking {
    private IRanking wrappedRanking;

    public HardRankingDecorator(IRanking ranking) {
        this.wrappedRanking = ranking;
    }

    @Override
    public void addPoints(int points) {
        wrappedRanking.addPoints((points * 2));
    }

    @Override
    public int getPoints() {
        return wrappedRanking.getPoints();
    }
}
