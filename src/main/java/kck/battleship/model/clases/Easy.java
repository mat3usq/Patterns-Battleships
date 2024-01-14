package kck.battleship.model.clases;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Easy implements Difficulty{
    private int[][] board;
    private List<Position> positions;

    private int lastshot = 0;
    private int enemyships;

    private Position lastshootPosition;

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
                    }
                }
            }
        }
    }
}
