package kck.battleship.model.clases;

import kck.battleship.model.clases.RankingMemento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<RankingMemento> mementoList = new ArrayList<>();

    public void addMemento(RankingMemento memento) {
        mementoList.add(memento);
    }

    public RankingMemento getMemento(int index) {
        return mementoList.get(index);
    }
}
