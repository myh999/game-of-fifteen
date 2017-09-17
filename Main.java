import java.util.Scanner;

public class Main {

	// constants
	static final int DIM_MIN = 3;
	static final int DIM_MAX = 9;

	// board
	static int[][] board = new int[DIM_MAX][DIM_MAX];

	// dimensions
	static int d = 0;

	public static void main(String[] args) throws Exception
	{
	    
	    Scanner scnr = new Scanner(System.in);
	    
	    do {
	    System.out.println("Choose dimensions (3 to 9)");
	    d = scnr.nextInt();
	    } while (d <= DIM_MIN && d >= DIM_MAX);
	    

	    // initialize the board
	    init();

	    // accept moves until game is won
	    while (true)
	    {
	        // clear the screen
	        clear();

	        // draw the current state of the board
	        draw();

	        // check for win
	        if (won())
	        {
	            System.out.println("ftw!");
	            break;
	        }

	        // prompt for move
	        System.out.println("Tile to move: ");
	        int tile = scnr.nextInt();
	        
	        // quit if user inputs 0 (for testing)
	        if (tile == 0)
	        {
	            break;
	        }
	        
	        move(tile);

	    }
	    
	    scnr.close();
	}

	/**
	 * Clears screen using ANSI escape sequences.
	 */
	static void clear()
	{
	    System.out.flush();
	}

	/**
	 * Initializes the game's board with tiles numbered 1 through d*d - 1
	 * (i.e., fills 2D array with values but does not actually print them).  
	 */
	static void init()
	{
	    int value = d * d - 1;
	    boolean odd = false;
	    if (value % 2 != 0) odd = true;
	    for (int i = 0; i < d; i++) {
	        for (int j = 0; j < d; j++) {
	            if (odd && value == 2) board[i][j] = 1;
	            else if (odd && value == 1) board[i][j] = 2;
	            else board[i][j] = value;
	            value--;
	        }
	    }
	}

	/**
	 * Prints the board in its current state.
	 */
	static void draw()
	{
	    for (int i = 0; i < d; i++) {
	        for (int j = 0; j < d; j++) {
	            if (board[i][j] == 0) System.out.print("-\t");
	            else System.out.print(board[i][j] + "\t");
	        }
	        System.out.println("\n");
	    }
	}

	/**
	 * If tile borders empty space, moves tile and returns true, else
	 * returns false. 
	 */
	static boolean move(int tile)
	{
	    for (int i = 0; i < d; i++) {
	        for (int j = 0; j < d; j++) {
	            if (tile == board[i][j]) {
	                if (i != 0 && board[i-1][j] == 0) {
	                    board[i-1][j] = tile;
	                    board[i][j] = 0;
	                    return true;
	                } else if (j != 0 && board[i][j-1] == 0) {
	                    board[i][j-1] = tile;
	                    board[i][j] = 0;
	                    return true;
	                } else if (i != d-1 && board[i+1][j] == 0) {
	                    board[i+1][j] = tile;
	                    board[i][j] = 0;
	                    return true;
	                } else if (j != d-1 && board[i][j+1] == 0) {
	                    board[i][j+1] = tile;
	                    board[i][j] = 0;
	                    return true;
	                } else return false;
	            }
	        }
	    }
	    return false;
	}

	/**
	 * Returns true if game is won (i.e., board is in winning configuration), 
	 * else false.
	 */
	static boolean won()
	{
	    int value = 1;
	    for (int i = 0; i < d; i++) {
	        for (int j = 0; j < d; j++) {
	            if (value != d * d && value != board[i][j]) return false;
	            else if (value == d * d && board[i][j] != 0) return false;
	            value++;
	        }
	    }
	    return true;
	}

}
