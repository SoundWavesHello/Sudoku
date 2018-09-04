/*
 * Stephen Majercik
 * 10 October 2016 
 * 
 * This class can be used to create objects that contain the
 * specifications for a Sudoku move: the digit, and the row
 * and column of the space where the digit is to be places.
 * 
 */


public class Move {

	// Used as a value for "digit" to indicate that no move 
	// was possible in a given situation.
	public static final int NO_MOVE = 0;

	// Move information.
	private int row;
	private int col;
	private int digit;

	
	// Creates an "empty" Move.
	public Move() {
		row = 0;
		col = 0;
		digit = 0;
	}

	
	// Creates a Move placing a specified digit
	// at a specified row and column.
	public Move(int row, int col, int digit) {
		this.row = row;
		this.col = col;
		this.digit = digit;
	}


	// Standard getters and setters.

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}



}
