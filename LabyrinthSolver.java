import java.util.*;

/**
 * The LabyrinthSolver class is used to solve an instance of the Labyrinth class
 * which creates a random maze. It utilizes a recursive method to traverse the maze 
 * called findSafeMove() which returns true whenever it reaches the end. 
 */

public class LabyrinthSolver
{
    private ArrayList<Integer> movesX; //Contains row position of moves
    private ArrayList<Integer> movesY; //Contains column position of moves
    private int maxRow;
    private int maxCol;
    private Labyrinth maze;

    /**
     * Constructor for LabyrinthSolver
     * @return void
     * @param Takes an integer representing the # of rows in the maze
     * @param Takes an integer representing the # of columns in the maze
     */
    public LabyrinthSolver(Labyrinth l)
    {
        maze = l;
        
        maxRow = l.rows;
        maxCol = l.cols;
        
        movesX = new ArrayList<Integer>();
        movesY = new ArrayList<Integer>();
        
        //The first move is always the starting square
        movesX.add(0);
        movesY.add(0);
    }
    
    /**
     * Helpful method for creating the solution array to the maze
     * @return Array of integers containing instructions to solve the maze
     */
    public int[] solveArray()
    {
        int[] solution = new int[movesX.size()-1]; //1 Less because it doesn't contain starting square
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
            }else { //If it went Left/Right
                if (yDif == 1) //Right
                {
                    solution[i] = 3;
                }else if (yDif == -1) { //Left
                    solution[i] = 2;
                }
            }
        }
        return solution;
    }
    
    /**
     * The solves method outputs a list of instructions to complete the maze which
     * can be fed to the Labyrinth class to check if it actually solves the maze.
     * @return Array of integers representing the direction of the move (Up = 0, Down = 1, Left = 2, Right = 3)
     * @param Takes a Labyrinth which it will solve
     */
    public static int[] solve(Labyrinth l)
    {        
        LabyrinthSolver ls = new LabyrinthSolver(l);
        ls.findSafeMove(0, 0);
        return ls.solveArray();
    }
    
    /**
     * Useful getter method
     * @return A Labyrinth Object representing the current maze
     */
    public Labyrinth getMaze()
    {
        return maze;
    }

    /**
     * The all-imporant recursive method which actually traverses the maze. It also
     * updates the movesX and movesY arrays so that they contain all the correct moves once finished
     * @return Boolean if it reached the end or not
     * @param An integer representing the current row of the maze
     * @param An integer representing the current column of the maze
     */
    public boolean findSafeMove(int row, int col)
    {
        if (row==(maxRow-1) && col==(maxCol-1))
        {
            return true; //Reached the end
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
    
    /**
     * The isSafe() method will check whether or not the location fed to it is a viable square to move to
     * for the findSafeMove() method.
     * @return Boolean if the square is in the maze/we haven't gone there/it is stone
     * @param An integer representing the current row position we are checking
     * @param An integer representing the current column position we are checking
     */
    public boolean isSafe(int row, int col)
    {
       return maze.isValid(row, col) && maze.isStone(row, col) && hasntMoved(row, col);
    }
    
    /**
     * Useful method that searches through the current row and column moves to see if
     * a move has already been made
     * @return Boolean if we have moved there are not
     * @param An integer representing the current row position we are checking
     * @param An integer representing the current column position we are checking
     */
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
    
    /**
     * Method for simplification that simply adds a move location to our moves arrays
     * @return void
     * @param An integer representing the row to add
     * @param An integer representing the column to add
     */
    public void makeMove(int row, int col)
    {
        movesX.add(row);
        movesY.add(col);
    }
    
    /**
     * Method for simplification that removes a move from our array
     * @return void
     * @param An integer representing the row to remove
     * @param An integer representing the column to remove
     */
    public void removeMove(int row, int col)
    {
        movesX.remove(movesX.size()-1);
        movesY.remove(movesY.size()-1);
    }
}
