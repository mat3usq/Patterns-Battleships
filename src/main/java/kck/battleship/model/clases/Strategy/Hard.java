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
        System.out.println(lastshot);
        for(int i=1;i<=10;i++)
        {
            for(int j=1;j<=10;j++)
            {
                if(board[i][j] == 1 || board[i][j]==2)
                    System.out.println("shoot result =" + board[i][j] + " " + (j-1) + " " + (i-1));
                if(board[i][j] == 2)
                {
                    if(board[i+1][j]==2 && board[i-1][j]==2)
                    {
                        System.out.println("xd");
                    }
                    else if(board[i+1][j]==1 && board[i-1][j]==2)
                    {
                        System.out.println("xd");
                    }
                    else if(board[i+1][j]==2 && board[i-1][j]==1)
                    {
                        System.out.println("xd");
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==2)
                    {
                        System.out.println("xd");
                    }
                    else if(board[i][j+1]==1 && board[i][j-1]==2)
                    {
                        System.out.println("xd");
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==1)
                    {
                        System.out.println("xd");
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==0)
                    {
                        Position temppos = new Position(j-2,i-1);
                        System.out.println("2 i 0 po jotach");
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i][j+1]==0 && board[i][j-1]==2)
                    {
                        Position temppos = new Position(j,i-1);
                        System.out.println("0 i 2 po jotach");
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==2 && board[i+1][j]==0)
                    {
                        Position temppos = new Position(j-1,i);
                        System.out.println("2 i 0 po ikach");
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==0 && board[i+1][j]==2)
                    {
                        Position temppos = new Position(j-1,i-2);
                        System.out.println("0 i 2 po ikach");
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==0 && board[i+1][j]==0)
                    {
                        Position temppos = new Position(j-1,i-2);
                        lastshootPosition = temppos;
                        System.out.println("0 i 0 się wykonuje po i");
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==1 && board[i+1][j]==0)
                    {
                        Position temppos = new Position(j-1,i);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==0 && board[i+1][j]==1)
                    {
                        Position temppos = new Position(j-1,i-2);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i][j+1]==0 && board[i][j-1]==0)
                    {
                        Position temppos = new Position(j,i-1);
                        lastshootPosition = temppos;
                        System.out.println("0 i 0 się wykonuje po jotach");
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i][j+1]==0 && board[i][j-1]==1)
                    {
                        Position temppos = new Position(j,i-1);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i][j+1]==1 && board[i][j-1]==0)
                    {
                        Position temppos = new Position(j-2,i-1);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
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
        positions.remove(randomIndex);
        lastshootPosition= target;
        System.out.println("randomowo strzelilo w pozycje" + target.getRow() + " " + target.getColumn());
        return target;
    }
    public void get_result(char result)
    {
        if(result=='✘'){
            lastshot = 2;
            System.out.println("Lastresult" + result);
        }
        else if(result =='◉'){
            lastshot = 1;
            System.out.println("Lastresult" + result);
        }
        else if(result =='⎕'){
            lastshot = 2;
            System.out.println("Lastresult" + result);
        }
        else if(result =='ℳ')
        {
            lastshot = 1;
            System.out.println("Lastresult" + result);
        }
        else if(result =='✜')
        {
            lastshot = 2;
            System.out.println("Lastresult" + result);
        }
        else
        {
            System.out.println("Last result" + result);
            lastshot =0;
        }
    }

    @Override
    public void enemy_ships_comprasion(int ships) {
        System.out.println("Statki o ktorym wiem " + enemyships + " statki obecnie " + ships);
        if(enemyships!=ships) {
            enemyships = enemyships - 1;
            for(int i=1;i<=10;i++)
            {
                for(int j=1;j<=10;j++)
                {
                    if(board[i][j]==2)
                    {
                        System.out.println("czemu to sie robi");
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
