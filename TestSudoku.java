
/*
 * Stephen Majercik
 * 11 October 2016
 * 
 * This class tests the Sudoku class by:
 * 
 *     1) asking the user for a puzzle file name,
 *     2) reading in the puzzle and printing it out,
 *     3) solving it and printing the solution, and
 *     4) checking the computed solution against the
 *        actual solution
 * 
 */


import java.util.Scanner;

public class TestSudoku {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		// Get the puzzle file name.
		System.out.print("Please enter the file name without the \".txt\" extension: ");
		String fileName = scan.next();

		// Read and print the puzzle.
		Sudoku puzzle = new Sudoku(fileName);
		System.out.println();
		System.out.println("The Puzzle: ");
		puzzle.printPuzzle();

		// Solve the puzzle and print the solution.
		puzzle.solve();
		System.out.println();
		System.out.println("The Solution:");
		puzzle.printPuzzle();
		
		// Check the solution.
		System.out.println();
		if (puzzle.checkSolution() == 0) {
			System.out.println("Solution is correct");
		}
		else {
			System.out.println("Solution is NOT correct");
		}
		
	}

}


