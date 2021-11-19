import java.util.*;

public class LabyrinthSolver
{
    private ArrayList<Integer> movesX;
    private ArrayList<Integer> movesY;
    private ArrayList<int[]> solution;
    private int maxRow;
    private int maxCol;
    private Labyrinth maze;
    
    public static void main(String[] args)
    {
        int row = Integer.parseInt(args[0]);
        int col = Integer.parseInt(args[1]);
      
        LabyrinthSolver ls = new LabyrinthSolver(row, col);  
        
        if(ls.findSafeMove(0, 0))
        {
           ls.printSolution();  
        }
        
        System.out.println(ls.getMaze().solves(ls.solveArray()));
    }
    
    public LabyrinthSolver(int row, int col)
    {
        maze = new Labyrinth(row, col);
        
        maxRow = row;
        maxCol = col;
        
        movesX = new ArrayList<Integer>();
        movesY = new ArrayList<Integer>();
        solution = new ArrayList<int[]>();
        
        movesX.add(0);
        movesY.add(0);
    }
    
    public Labyrinth getMaze()
    {
        return maze;
    }
    
    public int[] solveArray()
    {
        int[] newSolution = new int[solution.size()];
        
        for (int i = 0; i<solution.size(); i++)
        {
            if (solution.get(i) == Labyrinth.UP)
          {
              newSolution[i] = 0;
          }
          if (solution.get(i) == Labyrinth.DOWN)
          {
              newSolution[i] = 1;
          }
          if (solution.get(i) == Labyrinth.LEFT)
          {
              newSolution[i] = 2;
          }
          if (solution.get(i) == Labyrinth.RIGHT)
          {
              newSolution[i] = 3;
          }
        }
        
        return newSolution;
    }
    
    public void printSolution()
    {
        for (int i = 0; i<solution.size(); i++)
        {
          if (solution.get(i) == Labyrinth.UP)
          {
              System.out.println("UP");
          }
          if (solution.get(i) == Labyrinth.DOWN)
          {
              System.out.println("DOWN");
          }
          if (solution.get(i) == Labyrinth.LEFT)
          {
              System.out.println("LEFT");
          }
          if (solution.get(i) == Labyrinth.RIGHT)
          {
              System.out.println("RIGHT");
          }
        }
    }
    
    public boolean findSafeMove(int row, int col)
    {
        System.out.println("Row/Col: " + row + ", " + col);
        if (row==(maxRow-1) && col==(maxCol-1))
        {
            System.out.println("Solved");
            
            return true;
        }
        
        //Up
        if (isSafe(row-1, col))
        {
            makeMove(row-1, col);
            if(findSafeMove(row-1, col))
            {
                solution.add(maze.UP);
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
                solution.add(maze.DOWN);
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
                solution.add(maze.LEFT);
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
                solution.add(maze.RIGHT);
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
