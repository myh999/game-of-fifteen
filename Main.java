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

	// Clears screen
	static void clear()
	{
	    System.out.flush();
	}

	// Initializes board with a 2-d array
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

	// Prints the board
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

	// Moves tile, if valid
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

	// Checks for correct order
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
