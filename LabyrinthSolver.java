import java.util.*;

/**
 * The LabyrinthSolver class is used to solve
 */

public class LabyrinthSolver
{
    private ArrayList<Integer> movesX;
    private ArrayList<Integer> movesY;
    private int maxRow;
    private int maxCol;
    private Labyrinth maze;
    
    public static void main(String[] args)
    {
        int row = Integer.parseInt(args[0]);
        int col = Integer.parseInt(args[1]);
        
        Labyrinth l = new Labyrinth(row, col);
        LabyrinthSolver ls = new LabyrinthSolver(row, col, l);  
        
        if(ls.findSafeMove(0, 0))
        {
           ls.printSolution();  
        }
        
        System.out.println(ls.getMaze().solves(ls.solves(l)));
    }
    
    public LabyrinthSolver(int row, int col, Labyrinth l)
    {
        maze = l;
        
        maxRow = row;
        maxCol = col;
        
        movesX = new ArrayList<Integer>();
        movesY = new ArrayList<Integer>();
        
        movesX.add(0);
        movesY.add(0);
    }
    
    public int[] solves(Labyrinth l)
    {
        int[] solution = new int[movesX.size()-1];
        //Up = 0, Down = 1, Left = 2, Right = 3
        
        for (int i = 0; i<movesX.size()-1; i++)
        {
            int xDif = movesX.get(i+1) - movesX.get(i);
            int yDif = movesY.get(i+1) - movesY.get(i);
            if (xDif != 0) //If it went Up/Down
            {
                if (xDif == 1) //Down
                {
                    solution[i] = 1;
                }else { //UP
                    solution[i] = 0;
                }
            }else { //If it went up/down
                if (yDif == 1) //Right
                {
                    solution[i] = 3;
                }else if (yDif == -1) { //Left
                    solution[i] = 0;
                }
            }
        }
        return solution;
    }
    
    public Labyrinth getMaze()
    {
        return maze;
    }

    public void printSolution()
    {
        for (int row = 0; row<maxRow; row++)
        {
            for (int col = 0; col<maxCol; col++)
            {
                if (maze.isStone(row, col))
                {
                   System.out.print("_ "); 
                }else {
                   System.out.print("# ");  
                }
            }
            System.out.println();
        }
    }
    
    public boolean findSafeMove(int row, int col)
    {
        if (row==(maxRow-1) && col==(maxCol-1))
        {
            return true;
        }
        
        //Up
        if (isSafe(row-1, col))
        {
            makeMove(row-1, col);
            if(findSafeMove(row-1, col))
            {
                return true;
            }
            removeMove(row-1, col);
        }
        
        //Down
        if (isSafe(row+1, col))
        {
            makeMove(row+1, col);
            if (findSafeMove(row+1, col))
            {
                return true;
            }
            removeMove(row+1, col);
        }
        
        //Left
        if (isSafe(row, col-1))
        {
            makeMove(row, col-1);
            if(findSafeMove(row, col-1))
            {
                return true;
            }
            removeMove(row, col-1);
        }
        
        //Right
        if (isSafe(row, col+1))
        {
            makeMove(row, col+1);
            if (findSafeMove(row, col+1))
            {
                return true;
            }
            removeMove(row, col+1);
        }
        
        return false;
    }
    
    public boolean isSafe(int row, int col)
    {
       return maze.isValid(row, col) && maze.isStone(row, col)&& hasntMoved(row, col);
    }
    
    public boolean hasntMoved(int row, int col)
    {
        for (int i = 0; i<movesX.size(); i++)
        {
          if (row == movesX.get(i) && col == movesY.get(i))
          {
              return false; //Computer has moved here
          }
        }
        
        return true; //They haven't moved here
    }
    
    public void makeMove(int row, int col)
    {
        movesX.add(row);
        movesY.add(col);
    }
    
    public void removeMove(int row, int col)
    {
        for (int i = 0; i<movesX.size(); i++)
        {
          if (row == movesX.get(i) && col == movesY.get(i))
          {
              movesX.remove(i);
              movesY.remove(i);
              return;
          }
        }
    }
}
