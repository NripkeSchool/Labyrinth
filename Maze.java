import java.util.*;
public class Maze
{
   private int[][] maze; //0 Represents wall, 1 represents path
   private int maxRow;
   private int maxCol;
   private Random ran = new Random();
   public static void main(String[] args)
   {
       Scanner scan = new Scanner(System.in);
       Random ran2 = new Random();
       int row = scan.nextInt();
       int col = scan.nextInt();
       
       Maze m = new Maze(row, col);
       
       int startx = ran2.nextInt(row/2 + 1)*2;
       int starty = ran2.nextInt(col/2 + 1)*2;
       System.out.println(startx + ", " + starty);
       
       m.createMaze(startx, starty);
       m.printMaze();
   }
    
   public Maze(int row, int col)
   {
       maze = new int[row][col];
       maxRow = row;
       maxCol = col;
   }
   
   public void printMaze()
   {
      for (int row = 0; row<maxRow; row++)
      {
          for (int col = 0; col<maxCol; col++)
          {
              if (maze[row][col] == 0)
              {
                  System.out.print("# ");
              }else {
                  System.out.print("_ ");
              }
          }
          System.out.println();
      }
   }
   
   public void createMaze(int row, int col)
   {    
       if (row == maxRow-1 && col == maxCol-1)
       {
           return;
       }
       
       int dir;
       int times = 1;
       boolean[] directions = new boolean[4];
       
       while (times != 5)
       {
           dir = ran.nextInt(4);
           times++;
           while(directions[dir])
           {
             dir = ran.nextInt(4);   
           }
           directions[dir] = true;
           
           if (dir == 0 && isSafe(row-2, col))
           {
               maze[row-1][col] = 1;
               maze[row-2][col] = 1;
               createMaze(row-2, col);
           }
           if (dir == 1 && isSafe(row+2, col))
           {
               maze[row+1][col] = 1;
               maze[row+2][col] = 1;
               createMaze(row+2, col);
           }
           if (dir == 2 && isSafe(row, col-2))
           {
               maze[row][col-1] = 1;
               maze[row][col-2] = 1;
               createMaze(row, col-2);
           }
           if (dir == 3 && isSafe(row, col+2))
           {
               maze[row][col+1] = 1;
               maze[row][col+2] = 1;
               createMaze(row, col+2);
           }
       }
   }


   public boolean isSafe(int row, int col)
   {
       return (row < maxRow && row > -1 && col < maxCol && col > -1) && (maze[row][col] != 1);
   }
}
