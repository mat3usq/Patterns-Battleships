package kck.battleship.model.clases.Iterator;

import kck.battleship.model.clases.Strategy.Observer;
import kck.battleship.model.clases.Position;

import java.util.ArrayList;

public class ProbabilityTable implements Observer {
    private int[][] probability;
    private ArrayList<Integer> shipslenght;

    public ProbabilityTable(){
        probability = new int[20][20];
        shipslenght = new ArrayList<>();
        initialShipsLenght();
        make_probability();

    }

    private void  initialShipsLenght(){
        shipslenght.add(2);
        shipslenght.add(2);
        shipslenght.add(3);
        shipslenght.add(3);
        shipslenght.add(4);
    }



    public void make_probability(){
        for (Integer ship: shipslenght){
            for(int i=1;i<=10;i++)
            {
                for(int j=1;j<=10;j++)
                {
                    boolean canaddprobabilty = true;
                    for(int k=j;k<j+ship;k++){
                        if(probability[i][k]==-1 || k>10) {
                            canaddprobabilty = false;
                            break;
                        }
                    }
                    if(canaddprobabilty) {
                        for (int k = j; k < j + ship; k++) {
                                probability[i][k] += 1;
                        }
                    }
                    canaddprobabilty = true;
                    for(int k=i;k<i+ship;k++){
                        if(probability[k][j]==-1 || k>10) {
                            canaddprobabilty = false;
                            break;
                        }

                    }
                    if(canaddprobabilty) {
                        for (int k = i; k < i + ship; k++) {
                                probability[k][j] += 1;
                        }
                    }
                }
            }
        }
        System.out.println("Probability Field:");
        for(int i = 1; i <= 10; i++) {
            for(int j = 1; j <= 10; j++) {
                System.out.print(probability[i][j] + " ");
            }
            System.out.println(); // This will create a new line after each row
        }
    }
    public void reset_probability()
    {
        for(int i=1;i<=10;i++)
        {
            for(int j=1;j<=10;j++)
            {
                if(probability[i][j]!= -1)
                    probability[i][j]=0;
            }
        }
    }

    public void add_shot(int x, int y)
    {
        probability[x][y]=-1;
        reset_probability();
        make_probability();
    }
    public Integer get_position(int x,int y)
    {
        return probability[x][y];
    }

    public void update(Position position, ArrayList<Integer> shiplenght){
        int x=position.getRow()+1;
        int y=position.getColumn()+1;
        if( shiplenght != null && shiplenght.isEmpty() != true) {
            shipslenght = shiplenght;
        }
        add_shot(x,y);

    }


}
