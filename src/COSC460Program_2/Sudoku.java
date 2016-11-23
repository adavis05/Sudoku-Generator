package COSC460Program_2;
import java.util.*;




public class Sudoku extends Thread {

	static boolean finished;
	
	//Method needed to run threads
	public void run() {
		int[][] board = setUpBoard();
		
		Random ran = new Random(System.currentTimeMillis());
		
		//Generates numbers 1 to 7
		//And 1 to 8
		//For solve method
		int i = ran.nextInt(8);
		int j = ran.nextInt(8);
		int k = ran.nextInt(8) + 1;
			
		//If these numbers satisfy the method, add them to the board
		if(solve(i, j, board, 0, k)) {
			printBoard(board);
		}
		else {
			System.out.println("No answer for this puzzle.");
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		System.out.println("Sudoku 9x9 puzzle:");
		System.out.println("");
		
		//Master thread
		//Needed so other threads run correctly
		for(int i = 0; i < 10; i++) {
			
			finished = false;
			
			Thread thread1 = new Sudoku();

			thread1.start();
			
			while(!finished);
			
			thread1.stop();
		}
		
		//Actual threads being called
		//9 needed for 9x9 board
		for(int i = 0; i < 10; i++) {
			
			finished = false;
			
			Thread thread1 = new Sudoku();
			Thread thread2 = new Sudoku();
			Thread thread3 = new Sudoku();
			Thread thread4 = new Sudoku();
			Thread thread5 = new Sudoku();
			Thread thread6 = new Sudoku();
			Thread thread7 = new Sudoku();
			Thread thread8 = new Sudoku();
			Thread thread9 = new Sudoku();
			
			thread1.start();
			thread2.start();
			thread3.start();
			thread4.start();
			thread5.start();
			thread6.start();
			thread7.start();
			thread8.start();
			thread9.start();
			
			while(!finished);
			
			thread1.stop();
			thread1 = null;
			thread2.stop();
			thread2 = null;
			thread3.stop();
			thread3 = null;
			thread4.stop();
			thread4 = null;
			thread5.stop();
			thread5 = null;
			thread6.stop();
			thread6 = null;
			thread7.stop();
			thread7 = null;
			thread8.stop();
			thread8 = null;
			thread9.stop();
			thread9 = null;
		}
	}
	
	/*
	 * Method created generate random numbers
	 * Isn't necessary to use
	 * */
	/*public static int randomFill() {
		
		Random ran = new Random();
		int randomNumber = ran.nextInt(9) + 1;
		return randomNumber;
	}*/
	
	public static int[][] setUpBoard() {
		
		int number = 0;
		
		//Creates board (81 spaces and or 9x9 grid)
		int[][] newBoard = new int[9][9];
		
		int[]puzzle = new int[81];
		//Arrays.fill(puzzle, randomFill());
		//int[] puzzle = new int {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,0 0}
		//Set board up with actual numbers
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				newBoard[i][j]=puzzle[number++];
			}
		}
		return newBoard;
	}
	
	public static boolean solve(int row, int col, int[][]board, int xStart, int yStart) {
		
		//Return true if recursion is finished
		//This instance is true and the puzzle is solved
		if(xStart == 81) 
			return true;
		if(finished) 
			return true;
		
		//Does a loop columns first
		if(++col == 9) {
			col = 0;
			//Does a loop throw rows after a column is complete
			if(++row == 9) 
				row = 0;
		}
		
		//This skips the filled cells
		//There shouldn't be any
		if(board[row][col] != 0) {
			return solve(row, col, board, xStart+1, yStart);
		}
		
		//This is where the array will be filled
		//If cell has value of 0
		for(int i = 1; i <= 9; ++i) {
			if(++yStart == 10) 
				yStart = 1;
			
			//First check if value is allowed
			//Then check if legal operation
			if(canPlace(row, col, yStart, board)) {
				//If allowed, run recursion method
				board[row][col] = yStart;
				
				if(solve(row, col, board, xStart+1, yStart))
					return true;
			}
		}
		board[row][col] = 0;
		return false;
	}
	
	public static boolean canPlace(int row, int col, int value, int[][]board) {
		int i;
		
		//Search 9 other possibilities
		for(i = 0; i < 9; i++) {
			//Checks row
			if(board[row][i] == value) 
				return false;
			
			//Checks columns
			if(board[i][col] == value) 
				return false;
			
			//Checks the sub square
			if(board[row/3*3+i%3][col/3*3+i/3] == value) 
				return false;
			
		}
		//Operation is legal
		return true;
	}
	
	public static void printBoard(int[][] board) {
    	
		//Method for readability
        for (int i = 0; i < 9; i++) {
            //Line between each row 3rd row
        	if( i%3 == 0)
                System.out.println(" -----------------------");
            
            for (int j = 0; j < 9; j++) {
                //For every 3rd number place
            	if (j%3 == 0) System.out.print("| ");
                if(board[i][j] == 0)
                	System.out.print(" ");
                else
                	System.out.print(Integer.toString(board[i][j])+ " ");
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
	}
}

