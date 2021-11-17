
public class LabyrinthSolver
{
    private int[][] maze;
    private int maxRow;
    private int maxCol;
    public static void main(String[] args)
    {
        int row = Integer.parseInt(args[0]);
        int col = Integer.parseInt(args[1]);
        
        LabyrinthSolver ls = new LabyrinthSolver(row, col);  
        
        ls.findSafeMove(0, 0);
    }
    
    public LabyrinthSolver(int row, int col)
    {
        Labyrinth maze = new Labyrinth(row, col);
        maxRow = row;
        maxCol = col;
    }
    
    public void findSafeMove(int row, int col)
    {
        if (row==maxRow && col==maxCol)
        {
            System.out.println("Solved");
            
            return;
        }
        
        //Up
        if (isSafe(row-1, col))
        {
            makeMove(row, col);
            findSafeMove(row-1, col);
            removeMove(row, col);
        }
        
        //Down
        if (isSafe(row+1, col))
        {
            makeMove(row, col);
            findSafeMove(row+1, col);
            removeMove(row, col);
        }
        
        //Left
        if (isSafe(row, col-1))
        {
            makeMove(row, col);
            findSafeMove(row, col-1);
            removeMove(row, col);
        }
        
        //Right
        if (isSafe(row, col+1))
        {
            makeMove(row, col);
            findSafeMove(row, col+1);
            removeMove(row, col);
        }
    }
    
    public boolean isSafe(int row, int col)
    {
        return 
    }
    
    public void makeMove(int row, int col)
    {
        
    }
    
    public void removeMove(int row, int col)
    {
        
    }
}
