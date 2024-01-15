package kck.battleship.model.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProbabilityIterator implements Observer {
    private ProbabilityTable probabilityTable;
    private int currentMaxProbability;
    private List<Position> maxProbabilityPositions;
    private int currentIndex;

    public ProbabilityIterator(ProbabilityTable probabilityTable) {
        this.probabilityTable = probabilityTable;
        this.maxProbabilityPositions = new ArrayList<>();
        this.currentIndex = 0;
        findMaxProbabilityPositions();
    }


    private void findMaxProbabilityPositions() {
        currentMaxProbability = -1;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                int probability = probabilityTable.get_position(i, j);
                if (probability > currentMaxProbability) {
                    currentMaxProbability = probability;
                    maxProbabilityPositions.clear();
                    maxProbabilityPositions.add(new Position(j-1, i-1));
                } else if (probability == currentMaxProbability) {
                    maxProbabilityPositions.add(new Position(j-1, i-1));
                }
            }
        }
    }

    public Position next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Position nextPosition = maxProbabilityPositions.get(currentIndex);
        currentIndex++;
        return nextPosition;
    }

    public boolean hasNext() {
        return currentIndex < maxProbabilityPositions.size();
    }

    @Override
    public void update(Position position,ArrayList<Integer> shiplenght){
        currentIndex = 0;
        findMaxProbabilityPositions();
    }



}
