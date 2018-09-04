/*
 * 		Author: Kevin Lane
 * 		Lab 4
 * 		Last Modified: 10/17/2016
 * 
 * 		This class contains the following:
 * 		
 * 		1) A constructor to initialize a Sudoku object
 * 			from a text file
 * 		
 * 		2) A method to read a text file of Sudoku into
 * 			a two dimensional array
 *
 * 		3) A method to solve the Sudoku object puzzle
 * 
 * 		4) A method to get the digit at specific
 * 			coordinates in the two dimensional array
 * 
 * 		5) A method to make a move by changing the 
 * 			digit at specific coordinates in the 
 * 			two dimensional array
 * 
 * 		6) A method to find a different legal move
 * 			at specific coordinates, if there is
 * 			another legal move available
 * 
 * 		7) A method to find the next blank space and 
 * 			make a legal move there if possible
 * 
 * 		8) A method to determine whether or not a
 * 			move is legal
 * 
 * 		9) A method to check if solving the puzzle was
 * 			successful by looking to see if there are
 * 			any blank spaces in the two dimensional
 * 			array
 * 		
 * 		10) A method to print out the two dimensional
 * 			array 
 * 
 * 		11) A method to check the the solved array
 * 			against the actual solution
 * 
 * 
 * 		
 * 
 */

import java.util.Scanner;
import java.io.File;

public class Sudoku {

	private static final int BLANK = 0;;
	
	
	// A standard Sudoku puzzle is 9x9.
	private static final int DIMENSION = 9;
	
	// Arrays to hold the puzzle and the actual solution so that
	// we can check the program-generated solution against the
	// actual solution.
	private int[][] puzzle = new int[DIMENSION][DIMENSION];
	private int[][] solution = new int[DIMENSION][DIMENSION];

	// To read puzzle and solution files.
	private Scanner scan;

	
	// This constructor takes the name of a puzzle file and calls
	// readPuzzle to read the puzzle into the puzzle array.
	public Sudoku(String fileName) {
		readPuzzle(fileName);
	}

	
	// Purpose: Read the puzzle into the puzzle array.
	// Parameters: 
	// Return Value:
	//
	//              NOTE: The program assumes that blank
	//                    spaces are indicated by zeros in
	//                    the puzzlefile.
	public void readPuzzle(String fileName) {

		try {
			// The puzzles are in folder called "puzzles" and the
			// user will enter the filename without the .txt file
			// extension, so we must add those.
			String puzzleFileName = "puzzles/" + fileName + ".txt";
			scan = new Scanner(new File(puzzleFileName));

			for (int i = 0; i < DIMENSION; i++) {
				for (int j = 0; j < DIMENSION; j++) {
					puzzle[i][j] = scan.nextInt();
				}
			}
			
			// The solutions are also in the "puzzles" folder and
			// they have the same name as the puzzle file with the
			// addition of ""-solution.txt".
			String solutionFileName = "puzzles/" + fileName + "-solution.txt";
			scan = new Scanner(new File(solutionFileName));

			for (int i = 0; i < DIMENSION; i++) {
				for (int j = 0; j < DIMENSION; j++) {
					solution[i][j] = scan.nextInt();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	// Purpose: Solve the puzzle.
	// Parameters: None.
	// Return Value: None.
	//
	public void solve() {

		// find out number of blanks
		
		// create a counter for number of blanks
		int numBlanks = 0;
		
		// look for 0s in the array and increment the
		// 	counter for each 0 that is found
		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				if (puzzle[i][j] == 0){
					numBlanks++;
				}
			}
		}
		
		// use number of blanks to initialize our stack
		Stack<Move>moveStack = new Stack<Move>(numBlanks);
		
		
		// while the number of moves in the stack is
		// less than the number of blanks, execute the 
		// following loop
		while (!success()){
			
			// create variable currentMove
			Move currentMove = nextMove();
			
			// if currentMove is legal, push it to the stack
			if (currentMove.getDigit() != 0){
				moveStack.push(currentMove);
			}
			
			
			else {
				
				// while currentMove is illegal
				while (currentMove.getDigit() == 0){
					
					// pop of the last move from the stack
					Move lastMove = moveStack.pop();
					
					// reset the array so that the last move
					// was not made
					makeMove(lastMove.getRow(),
							lastMove.getCol(), BLANK);
					
					// reset currentMove so that it is equal
					// to the 'changed' last move, whether it is
					// legal or not
					currentMove = changeMove(lastMove.getRow(), 
						lastMove.getCol(), lastMove.getDigit());
					
					// if the currentMove is legal, it will be
					// added to the stack and exit the loop
					if (currentMove.getDigit() != 0){
						moveStack.push(currentMove);
					}
					
					// if the currentMove is illegal, then
					// currentMove will be continually reset to
					// the last move made until that last move can
					// be changed to a legal move
				}
			}
			
			// add currentMove to the array
			
			makeMove(currentMove.getRow(), 
					currentMove.getCol(), currentMove.getDigit());
			
		}
    }


	// Purpose: Get the digit from a specified location in the puzzle.
	// Parameters: The row and column we want to get the digit of.
	// Return Value: The digit at that row and column.
	//
	public int getDigit(int row, int col) {
		return puzzle[row][col];
	}


	// 
	// Purpose: Put a digit in a specified location in the puzzle.
	// Parameters: The digit to be placed and the row and column 
	//             that it should be placed at.
	// Return Value: None.
	//
	public void makeMove(int row, int col, int digit) {
		puzzle[row][col] = digit;
	}

	
	// Purpose: Change the move at a specified location, if possible.
	// Parameters: The row and column of the specified location and
	//             the most recent digit tried there,
	// Return Value: A Move object that contains the move found or
	//               a value indicating that there are no more legal
	//               moves at that location.
	//
	public Move changeMove(int row, int col, int lastDigit) {

		// The Move object to return.
		Move move = new Move();

		// Try all the digits that come after lastDigit.
		for (int next = lastDigit + 1; next <= DIMENSION; next++) {
			
			// If the move is legal, return it.
			if (legalMove(row, col, next)) {
				move.setRow(row);
				move.setCol(col);
				move.setDigit(next);
				return move;
			}
		}

		// No legal moves were found, so set the digit to indicate
		// that and return the Move.
		move.setDigit(Move.NO_MOVE);
		return move;
	}
	
//	public Move changeMove(Move wrongMove){
//		
//	}


	// Purpose: Find the first empty space in the puzzle (going row by
	//          row, left to right) and try to find a legal move for that
	//          space.
	// Parameters: None.
	// Return Value: A Move object that contains the move found for the
	//               first empty space or a value indicating that there 
	//               were no legal moves at that location.
	//
	public Move nextMove() {

		// The Move object to return.
		Move move = new Move();


		// Go through row byrow, left to right.
		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {

				if (puzzle[row][col] == BLANK) {

					// Try all possible digits.
					for (int next = 1; next <= DIMENSION; next++) {

						// If the move is legal, return it.
						if (legalMove(row, col, next)) {
							move.setRow(row);
							move.setCol(col);
							move.setDigit(next);
							return move;                    }
					}

					// No legal moves were found, so set the digit 
					// to indicate that and return the Move.
					move.setDigit(Move.NO_MOVE);
					return move;            }

			}
		}

		// We should never get here, because if there are no more
		// empty places, the puzzle is solved, but we have to
		// return something.
		return null;
	}


	// Purpose: Check if a move is legal, given the state of the puzzle.
	// Parameters: The row and column of the move and the digit to 
	//             be palced there.
	// Return Value: True, if the move is legal; otherwise, false.
	//
	public boolean legalMove(int row, int col, int digit) {

		// Check the row and column.
		for (int i = 0; i < DIMENSION; i++) {
			if (puzzle[row][i] == digit || puzzle[i][col] == digit) {
				return false;
			}
		}

		// Check the 3x3 group that [row,col] belongs to. Note that
		// rGroup and cGroup are essentially indices of 3x3 groups.
		// In other words, they are indexed as if they were individual
		// entities, e.g. if rGroup and cGroup are both 1, they refer
		// to the 3x3 group in the center of the puzzle.
		int rGroup = row / 3;
		int cGroup = col / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (puzzle[rGroup * 3 + i][cGroup * 3 + j] == digit) {
					return false;
				}
			}
		}

		// If we never found a problem and returned false, it's legal.
		return true;
	}


	// Purpose: Check to see if the puzzle has been successfully completed.
	// Parameters: None.
	// Return Value: True, if the puzzle has been successfully completed; 
	//               otherwise, false.
	//               NOTE: 	Since we have ensured that we make only legal
	//                      moves, we have solved the puzzle if there are
	//                      no more blank spaces.
	// 
	public boolean success() {
		for (int r = 0; r < DIMENSION; r++) {
			for (int c = 0; c < DIMENSION; c++) {
				if (puzzle[r][c] == 0)
					return false;
			}
		}
		return true;
	}


	// Purpose: Print out the puzzle in a nicely formatted way.
	// Parameters: None.
	// Return Value: None.
	//
	public void printPuzzle(){

		for (int r = 0; r < DIMENSION; r++){
			for (int c = 0; c < DIMENSION; c++){
				if (puzzle[r][c] == 0)
					System.out.print("_ ");
				else
					System.out.print(puzzle[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	
	// Purpose: Check the computed solution against the actual solution.
	// Parameters: None.
	// Return Value: The number of errors found.
	//
	public int checkSolution() {

		int numErrors = 0;

	    for (int r = 0; r < DIMENSION; r++){
	        for (int c = 0; c < DIMENSION; c++){
	            if (puzzle[r][c] != solution[r][c])
	            	++numErrors;
	        }
	    }

	    return numErrors;
	}
	
}




