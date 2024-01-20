package kck.battleship.model.clases.Strategy;

import kck.battleship.model.clases.Position;
import kck.battleship.model.clases.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Normal implements Difficulty {

    private int[][] board;
    private List<Position> positions;

    private int lastshot = 0;
    private int enemyships;

    private Position lastshootPosition;

    private int smallestship;

    public Normal() {
        this.board = new int[12][12];
        this.positions = new ArrayList<>();
        this.enemyships = 5;
        smallestship = 2;
        initializePositions();
    }

    private void initializePositions() {
        normal_inicialPostionsList();

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

    private void normal_inicialPostionsList()
    {
        positions.clear();
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                if((row+column)%smallestship == 1 && board[column+1][row+1]==0) {
                    positions.add(new Position(row, column));
                }
            }
        }
    }

    public Position shootAtRandom() {
        if(lastshootPosition != null)
            board[lastshootPosition.getColumn()+1][lastshootPosition.getRow()+1] = lastshot;
        System.out.println(lastshot);
        for(int i=1;i<=10;i++)
        {
            for(int j=1;j<=10;j++)
            {
                if(board[i][j] == 2)
                {
                    if(board[i+1][j]==2 && board[i-1][j]==2)
                    {
                        //donothing
                    }
                    else if(board[i+1][j]==1 && board[i-1][j]==2)
                    {
                        //donothing
                    }
                    else if(board[i+1][j]==2 && board[i-1][j]==1)
                    {
                        //donothing
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==2)
                    {
                        //donothing
                    }
                    else if(board[i][j+1]==1 && board[i][j-1]==2)
                    {
                        //donothing
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==1)
                    {
                        //donothing
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==0)
                    {
                        Position temppos = new Position(j-2,i-1);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i][j+1]==0 && board[i][j-1]==2)
                    {
                        Position temppos = new Position(j,i-1);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==2 && board[i+1][j]==0)
                    {
                        Position temppos = new Position(j-1,i);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==0 && board[i+1][j]==2)
                    {
                        Position temppos = new Position(j-1,i-2);
                        lastshootPosition = temppos;
                        positions.remove(temppos);
                        return temppos;
                    }
                    else if(board[i-1][j]==0 && board[i+1][j]==0)
                    {
                        Position temppos = new Position(j-1,i-2);
                        lastshootPosition = temppos;
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
        int randomIndex = random.nextInt(positions.size());
        Position target = positions.get(randomIndex);
        positions.remove(randomIndex);
        lastshootPosition= target;
        //System.out.println("randomowo strzelilo w pozycje" + target.getRow() + " " + target.getColumn());
        return target;
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
                        if(board[i+1][j]!=2)
                            board[i+1][j]=1;
                      if(board[i-1][j]!=2)
                         board[i-1][j]=1;
                        if(board[i][j+1]!=2)
                        board[i][j+1]=1;
                       if(board[i+1][j-1]!=2)
                           board[i][j-1]=1;
                    }
                }
            }
        }
        normal_inicialPostionsList();
    }

    public void get_ships_and_change_smallest_ship(ArrayList<Ship> ships){
        int new_small_lenght = 100;

        for(Ship s: ships) {
            new_small_lenght = Integer.min(new_small_lenght,s.getLength());
        }
        if(new_small_lenght>smallestship)
        {
            smallestship=new_small_lenght;
            normal_inicialPostionsList();
        }

    }
}
