package kck.battleship.model.clases.Strategy;

import kck.battleship.model.clases.*;
import kck.battleship.model.clases.Iterator.ProbabilityIterator;
import kck.battleship.model.clases.Iterator.ProbabilityTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Hard implements Difficulty{
    private int[][] board;
    private List<Position> positions;

    private int lastshot = 0;
    private int enemyships;

    private Position lastshootPosition;
    private int smallestship = 2;

    private ProbabilityTable probabilityTable;

    private ProbabilityIterator probabilityIterator;

    private ArrayList<Observer> observers;

    private  ArrayList<Integer> emptylist;

    public Hard() {
        this.board = new int[12][12];
        this.positions = new ArrayList<>();
        this.enemyships = 5;
        emptylist = new ArrayList<>();
        initializePositions();
        probabilityTable = new ProbabilityTable();
        probabilityIterator = new ProbabilityIterator(probabilityTable);
        observers = new ArrayList<>();
        addObserver(probabilityTable);
        addObserver(probabilityIterator);
    }

    private void initializePositions() {
        for(int i=0;i<=11;i++)
        {
            board[0][i]=1;
        }
        for(int i=0;i<=11;i++)
        {
            board[i][0]=1;
        }
        for(int i=0;i<=11;i++)
        {
            board[11][i]=1;
        }
        for(int i=0;i<=11;i++)
        {
            board[i][11]=1;
        }
    }

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void notifyObservers(Position position, ArrayList<Integer> shiplenght) {
        for (Observer observer : observers) {
            observer.update(position,shiplenght);
        }
    }

    public Position shootAtRandom() {
        if(lastshootPosition != null) {
            board[lastshootPosition.getColumn() + 1][lastshootPosition.getRow() + 1] = lastshot;
            notifyObservers(lastshootPosition, emptylist);
        }
//        System.out.println(lastshot);
        for(int i=1;i<=10;i++)
        {
            for(int j=1;j<=10;j++)
            {

                if(board[i][j] == 2)
                {
                    if(board[i+1][j]==2 && board[i-1][j]==2) {
                        //donothing
                    }
                    else if(board[i+1][j]==1 && board[i-1][j]==2) {
                        //donothing
                    }
                    else if(board[i+1][j]==2 && board[i-1][j]==1) {
                        //donothing
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==2) {
                        //donothing
                    }
                    else if(board[i][j+1]==1 && board[i][j-1]==2) {
                    //donothing
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==1) {
                        //donothing
                    }
                    else if(board[i][j+1] == 2 && board[i][j-1] == 0) {
                        return createAndHandlePosition(j-2, i-1);
                    }
                    else if(board[i][j+1] == 0 && board[i][j-1] == 2) {
                        return createAndHandlePosition(j, i-1);
                    }
                    else if(board[i-1][j] == 2 && board[i+1][j] == 0) {
                        return createAndHandlePosition(j-1, i);
                    }
                    else if(board[i-1][j] == 0 && board[i+1][j] == 2) {
                        return createAndHandlePosition(j-1, i-2);
                    }
                    else if(board[i-1][j] == 0 && board[i+1][j] == 0) {
                        return createAndHandlePosition(j-1, i-2);
                    }
                    else if(board[i-1][j] == 1 && board[i+1][j] == 0) {
                        return createAndHandlePosition(j-1, i);
                    }
                    else if(board[i-1][j] == 0 && board[i+1][j] == 1) {
                        return createAndHandlePosition(j-1, i-2);
                    }
                    else if(board[i][j+1] == 0 && board[i][j-1] == 0) {
                        return createAndHandlePosition(j, i-1);
                    }
                    else if(board[i][j+1] == 0 && board[i][j-1] == 1) {
                        return createAndHandlePosition(j, i-1);
                    }
                    else if(board[i][j+1] == 1 && board[i][j-1] == 0) {
                        return createAndHandlePosition(j-2, i-1);
                    }

                }

            }
        }
        Random random = new Random();
        while (probabilityIterator.hasNext()) {
         positions.add(probabilityIterator.next());
        }
        int randomIndex = random.nextInt(positions.size());
        Position target = positions.get(randomIndex);
        positions.clear();
        lastshootPosition= target;
      //  System.out.println("randomowo strzelilo w pozycje" + target.getRow() + " " + target.getColumn());
        return target;
    }

    private Position createAndHandlePosition(int column, int row) {
        Position tempPos = new Position(column, row);
        lastshootPosition = tempPos;
        positions.remove(tempPos);
        return tempPos;
    }
    public void get_result(char result)
    {
        if(result=='✘'){
            lastshot = 2;
        }
        else if(result =='◉'){
            lastshot = 1;
        }
        else if(result =='⎕'){
            lastshot = 2;
        }
        else if(result =='ℳ')
        {
            lastshot = 1;
        }
        else if(result =='✜')
        {
            lastshot = 2;
        }
        else
        {
            lastshot =0;
        }
    }

    @Override
    public void enemy_ships_comprasion(int ships) {
       // System.out.println("Statki o ktorym wiem " + enemyships + " statki obecnie " + ships);
        if(ships>enemyships)
            enemyships =ships;
        if(enemyships>ships) {
            enemyships = enemyships - 1;
            for(int i=1;i<=10;i++)
            {
                for(int j=1;j<=10;j++)
                {
                    if(board[i][j]==2)
                    {
                        board[i][j]=3;
                        if(board[i+1][j]!=2) {
                            board[i + 1][j] = 1;
                            notifyObservers(new Position(j-1,i),emptylist);
                        }
                        if(board[i-1][j]!=2) {
                            board[i - 1][j] = 1;
                            notifyObservers(new Position(j-1,i-2),emptylist);
                        }
                        if(board[i][j+1]!=2) {
                            board[i][j + 1] = 1;
                            notifyObservers(new Position(j,i-1),emptylist);
                        }
                        if(board[i+1][j-1]!=2) {
                            board[i][j - 1] = 1;
                            notifyObservers(new Position(j-2,i-1),emptylist);
                        }
                    }
                }
            }
        }
    }

    public void get_ships_and_change_smallest_ship(ArrayList<Ship> ships){
        int new_small_lenght = 100;
        ArrayList<Integer> lenghts = new ArrayList<>();
        for(Ship s: ships) {
            new_small_lenght = Integer.min(new_small_lenght,s.getLength());
            lenghts.add(s.getLength());
        }
        notifyObservers(lastshootPosition,lenghts);
        if(new_small_lenght>smallestship)
        {
            smallestship=new_small_lenght;
        }

    }

}

