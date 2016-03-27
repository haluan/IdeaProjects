//*******************************************************************
//
//   Maze_Search.java          In Text          Application
//
//   Authors:  Lewis and Loftus
//
//   Classes:  Maze_Search
//             Maze
//
//*******************************************************************

//-------------------------------------------------------------------
//
//  Class Maze_Search contains the driver of a program that
//  demonstrates the use of recursion to solve a maze.
//
//  Methods:
//
//     public static void main (String[] args)
//
//-------------------------------------------------------------------

public class Maze_Search {

    //===========================================================
    //  Creates a new maze, prints its original form, attempts
    //  to solve it, and prints out its final form.
    //===========================================================
    public static void main (String[] args) {

        Mazalah labyrinth = new Mazalah();

        labyrinth.print_maze();

        if (labyrinth.solve(3, 3))
            System.out.println ("Maze solved!");
        else
            System.out.println ("No solution.");

        labyrinth.print_maze();

    }  // method main

}  // class Maze_Search

//-------------------------------------------------------------------
//
//  Class Maze represents a maze of characters.  The goal is to get
//  from the top left corner to the bottom right, following a path
//  of 1's.
//
//  Methods:
//
//     public void print_maze ()
//     public boolean solve (int row, int column)
//     private boolean valid (int row, int column)
//
//-------------------------------------------------------------------

class Mazalah {

    char[][] grid = {
            {'G','T','#','G','?','#'},
            {'G','?','#','G','T','#'},
            {'#','L','G','G','T','#'},
            {'#','T','#','G','T','#'},
            {'#','?','#','G','G','#'}
    };

    //===========================================================
    //  Prints the maze grid.
    //===========================================================
    public void print_maze () {

        System.out.println();

        for (int row=0; row < grid.length; row++) {
            for (int column=0; column < grid[row].length; column++)
                System.out.print (grid[row][column]);
            System.out.println();
        }

        System.out.println();

    }  // method print_maze

    //===========================================================
    //  Attempts to recursively traverse the maze.  It inserts
    //  special characters indicating locations that have been
    //  tried and that eventually become part of the solution.
    //===========================================================
    public boolean solve (int row, int column) {

        boolean done = false;

        if (valid (row, column)) {

            grid[row][column] = 'x';  // cell has been tried

            if (row == grid.length-1 && column == grid[0].length-1)
                done = true;  // maze is solved
            else {

                done = solve(row-1, column); //up
                if (!done)
                    done = solve (row, column+1);  // right
                if (!done)
                    done = solve (row+1, column);  // down
                if (!done)
                    done = solve (row, column-1);  // left
            }
            if (done) {  // part of the final path
                grid[row][column] = 'H';
                if(grid[row][column] == '?'){
                    System.out.println("CHANGE DIR "+row+", "+column);
                }
                if(grid[row][column] == 'T'){
                    System.out.println("Battle "+row+", "+column);
                }
                if(grid[row][column] == 'G'){
                    System.out.println("Gokemon "+row+", "+column);
                }
            }
        }

        return done;

    }  // method solve

    //===========================================================
    //  Determines if a specific location is valid.
    //===========================================================
    private boolean valid (int row, int column) {

        boolean result = false;

        // check if cell is in the bounds of the matrix
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length){
            //  check if cell is not blocked and not previously tried
            if (grid[row][column] == 'G' || grid[row][column] == 'T' || grid[row][column] == '?' || grid[row][column] == 'L') {


                result = true;
            }
        }


        return result;

    }  // method valid

}  // class Maze