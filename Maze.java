import java.util.*;
public class Maze
{
   private int[][] maze;
   private Random ran = new Random();
   public static void main(String[] args)
   {
       Scanner scan = new Scanner(System.in);
       int row = scan.nextInt();
       int col = scan.nextInt();
       Maze m = new Maze(row, col);
       m.createMaze(0, 0);
   }
    
   public Maze(int row, int col)
   {
       maze = new int[row][col];
   }
   
   public void createMaze(int row, int col)
   {
       int direction = ran.nextInt(4);
       
       if (direction == 0) //Up
       {
           if (isSafe(row-1, col))
           {
               
           }
       }
       if (direction == 1) //Down
       {
           
       }
       if (direction == 2) //Left
       {
           
       }
       if (direction == 3) //Right
       {
           
       }
   }
   
   public boolean isSafe(int row, int col)
   {
       return row < 8 && row > -1 && col < 8 && col > -1;
   }
}
