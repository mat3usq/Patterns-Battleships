package kck.battleship.model.clases.Decorator;

public class NormalRankingDecorator implements IRanking {

    private IRanking wrappedRanking;

    public NormalRankingDecorator(IRanking ranking) {
        this.wrappedRanking = ranking;
    }

    @Override
    public void addPoints(int points) {
        wrappedRanking.addPoints((int)((points) * 1.5));
    }

    @Override
    public int getPoints() {
        return wrappedRanking.getPoints();
    }

}
