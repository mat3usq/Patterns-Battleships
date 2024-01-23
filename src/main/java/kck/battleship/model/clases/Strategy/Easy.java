package kck.battleship.model.clases.Strategy;

import kck.battleship.model.clases.Position;
import kck.battleship.model.clases.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Easy implements Difficulty{
    private int[][] board;
    private List<Position> positions;

    private int lastshot = 0;
    private int enemyships;

    private Position lastshootPosition;
    private int smallestship = 2;

    public Easy() {
        this.board = new int[12][12];
        this.positions = new ArrayList<>();
        this.enemyships = 5;
        initializePositions();
    }

    private void initializePositions() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                    positions.add(new Position(row, column));
            }
        }
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

    public Position shootAtRandom() {
        if(lastshootPosition != null)
            board[lastshootPosition.getColumn()+1][lastshootPosition.getRow()+1] = lastshot;
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
                        //do nothing
                    }
                    else if(board[i+1][j]==1 && board[i-1][j]==2)
                    {
                        //do nothing
                    }
                    else if(board[i+1][j]==2 && board[i-1][j]==1)
                    {
                        //do nothing
                    }
                    else if(board[i][j+1]==2 && board[i][j-1]==2)
                    {
                        //do nothing
                    }
                    else if(board[i][j+1]==1 && board[i][j-1]==2)
                    {
                        //do nothing
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
        int randomIndex = random.nextInt(positions.size());
        Position target = positions.get(randomIndex);
        positions.remove(randomIndex);
        lastshootPosition= target;
   // System.out.println("randomowo strzelilo w pozycje" + target.getRow() + " " + target.getColumn());
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
        else if(result =='ℳ'){
            lastshot = 1;
        }
        else if(result =='✜'){
            lastshot = 2;
        }
        else {
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
                    }
                }
            }
        }
    }

    public void get_ships_and_change_smallest_ship(ArrayList<Ship> ships){
        int new_small_lenght = 100;
        for(Ship s: ships) {
            new_small_lenght = Integer.min(new_small_lenght,s.getLength());
        }
        if(new_small_lenght>smallestship)
        {
            smallestship=new_small_lenght;
        }

    }
}
