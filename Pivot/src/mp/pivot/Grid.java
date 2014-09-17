package mp.pivot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import com.jm.pivot.R;

import android.view.View;

/**
 * Keeps track of a maze of walls, holes, open spaces, and a starting and end
 * position
 * 
 * @author Michelle Pokrass
 */
public class Grid {
	// Declares variables, including the resources containing the files
	private int[][] grid;

	static int[] levels = { 0, R.raw.level1, R.raw.level2, R.raw.level3,
			R.raw.level4, R.raw.level5, R.raw.level6, R.raw.level7,
			R.raw.level8, R.raw.level9, R.raw.level10, R.raw.level11,
			R.raw.level12, R.raw.level13, R.raw.level14, R.raw.level15,
			R.raw.level16, R.raw.level17, R.raw.level18, R.raw.level19,
			R.raw.level20, R.raw.level21, R.raw.level22, R.raw.level23,
			R.raw.level24, R.raw.level25, R.raw.level26, R.raw.level27,
			R.raw.level28, R.raw.level29, R.raw.level30 };

	char difficulty = 'e';

	/**
	 * Creates a random grid
	 */
	public Grid() {
		grid = new int[45][30];
		newRandomGrid();
	}

	/**
	 * Creates a grid from a file
	 * 
	 * @param view
	 *            View that is used to get the application context
	 * @param level
	 *            the int level number
	 */
	public Grid(View view, int level) {
		// Gets the resource
		InputStream is = view.getContext().getResources()
				.openRawResource(levels[level]);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			// Creates a new empty grid of integers
			grid = new int[49][35];
			// Reads in every line
			for (int i = 0; i < 49; i++) {
				String line = (br.readLine());
				// Reads in every character on the line
				for (int j = 0; j < 30; j++) {
					grid[i][j] = line.charAt(j) - '0';
				}
			}
			// Close the files
			is.close();
			br.close();
		}
		// Handles exceptions
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sets the border of a random grid
	 * 
	 */
	private void setBorder() {
		// Go through every position on the grid, making it an empty space
		for (int row = 0; row < 49; row++) {
			for (int col = 0; col < 30; col++) {
				grid[row][col] = 0;
			}
		}
		// Make top border of walls
		for (int col = 0; col < 30; col++) {
			grid[0][col] = 1;
		}

		// Make right border of walls
		for (int row = 0; row < 49; row++) {
			grid[row][29] = 1;
		}

		// Make bottom border of walls
		for (int col = 0; col < 30; col++) {
			grid[44][col] = 1;

		}
		// Make left border of walls
		for (int row = 0; row < 49; row++) {
			grid[row][0] = 1;
		}

	}

	/**
	 * Creates a new random grid
	 */
	private void newRandomGrid() {
		// Set the outside border
		setBorder();
		Random rndm = new Random();

		if (difficulty == 'e') {
			// Picks a random side to start building walls from
			int side = rndm.nextInt(4);
			boolean currentSide = true;
			if (side % 2 == 0) {
				if (side == 0) {
					currentSide = true;
				} else {
					currentSide = false;
				}
				// Builds walls up from the sides using a spacing interval
				for (int spacer = 5; spacer < 45; spacer += 7) {

					if (currentSide) {
						for (int col = 1; col < 25; col++)
							grid[spacer][col] = 1;
					} else {
						for (int col = 29; col > 6; col--)
							grid[spacer][col] = 1;
					}
					// Switches the side the wall starts from
					currentSide = !currentSide;
				}
			} else {
				if (side == 1) {
					currentSide = true;
				} else {
					currentSide = false;
				}
				// Builds walls up from the sides using a spacing interval
				for (int spacer = 9; spacer < 30; spacer += 9) {
					// Grow out the wall, depending on the current size
					if (currentSide) {
						for (int row = 1; row < 39; row++)
							grid[row][spacer] = 1;

					} else {
						for (int row = 44; row > 5; row--)
							grid[row][spacer] = 1;
					}
					// Switches the side the wall starts from
					currentSide = !currentSide;
				}
			}
		}

		else if (difficulty == 'm') {

		}

		else if (difficulty == 'h') {

		}
	}

	/**
	 * Sets the difficulty of the random maze generator
	 * 
	 * @param difficulty
	 *            char 'e', 'm', or 'h'
	 */
	private void setDifficulty(char difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Changes the format of the output of the string
	 * 
	 * @return the new formatted string
	 */
	public String toString() {
		// Goes through every element, displaying it in its correct position
		String str = "";
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++)
				str += grid[row][col];
			str += "\n";
		}
		return str;
	}

	/**
	 * Returns the game grid
	 * 
	 * @return the int[][] grid
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * Returns the value at the given position
	 * 
	 * @param row
	 *            int the row
	 * @param column
	 *            int the column
	 * @return the int value at the position
	 */
	public int getObject(int row, int column) {
		return grid[row][column];

	}

}
