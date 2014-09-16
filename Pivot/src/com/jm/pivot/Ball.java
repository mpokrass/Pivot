package com.jm.pivot;

/**
 * Creates an object that keeps track of a balls coordinates and velocity,
 * moving it according to acceleration and grid placement
 * 
 * @author Michelle Pokrass
 */
public class Ball
{
	// Declare the variables
	private float xCoord;

	private float yCoord;

	private float xVel;

	private float yVel;

	private final float UPDATE_TIME = 0.03f;

	private int[][] grid;
	
	private int squareSize;

	/**
	 * A constructor that creates a ball with a position and grid
	 * 
	 * @param x
	 *            int the x-coordinate of the top left corner of the ball
	 * @param y
	 *            int the y-coordinate of the top left corner of the ball
	 * @param grid
	 *            int[][] the maze the ball is contained in
	 */
	public Ball(float x, float y, int[][] grid, int squareSize)
	{
		xCoord = x;
		yCoord = y;
		// Set the initial velocities to zero
		xVel = 0;
		yVel = 0;
		this.grid = grid;
		this.squareSize = squareSize;
	}

	/**
	 * Checks if the given space is on the board
	 * 
	 * @param row
	 *            int the row
	 * @param col
	 *            int the column
	 * @return true if the position is on the grid, false otherwise
	 */
	public boolean onBoard(int row, int col)
	{
		boolean checkRow = row >= 0 && row < grid.length;
		boolean checkCol = col >= 0 && col < grid[0].length;
		return checkRow && checkCol;
	}

	/**
	 * Moves the ball using given acceleration, checking for collisions with
	 * walls or holes
	 * 
	 * @param xAccel
	 *            float the acceleration in the x-axis
	 * @param yAccel
	 *            float the acceleration in the y-axis
	 * @return
	 */
	public int move(float xAccel, float yAccel)
	{
		// Update the velocity
		xVel += (UPDATE_TIME) * xAccel;
		yVel += (UPDATE_TIME) * yAccel;
		// Get new coordinate
		float newX = xCoord + (UPDATE_TIME) * (xVel) * 3000f;
		float newY = yCoord + (UPDATE_TIME) * (yVel) * 3000f;
		// Use a trial coordinate while collision checking
		float trialX = 0f;
		float trialY = 0f;
		// Get the row and column of the new coordinates
		int row = (int) (newY / squareSize);
		int col = (int) (newX / squareSize);
		// If the ball is moving to the left, check for collisions
		if (xVel < 0)
		{
			boolean collided = false;
			// Check if the top left middle point of the ball is in a wall, if
			// so
			// move the ball over one column to the left
			if (onBoard(row + 1, col) && (grid[row + 1][col] == 1))
			{
				trialX = (col + 1) * squareSize;
				collided = true;
			}
			// Check if the middle point of the ball is in a wall, if so
			// move the ball over two columns to the left
			if (onBoard(row + 1, col + 1) && (grid[row + 1][col + 1] == 1))
			{
				trialX = (col + 2) * squareSize;
				collided = true;
			}
			// Check if the middle right of the ball is in a wall, if so
			// move the ball over three columns to the left
			if (onBoard(row + 1, col + 2) && (grid[row + 1][col + 2] == 1))
			{
				trialX = (col + 3) * squareSize;
				collided = true;
			}
			// If there is no collision, use the new coordinates for the ball's
			// position
			if (!collided)
			{
				trialX = newX;
			}
			// If there has been a collision, give the ball a smaller and
			// negative velocity
			else
			{
				xVel = (float) (-xVel / 4);
			}

		}
		// If the ball is moving to the right, check for collisions
		else if (xVel > 0)
		{
			boolean collided = false;
			// If the right middle of the ball is an a wall, move it one column
			// to the left
			if (onBoard(row + 1, col + 2) && (grid[row + 1][col + 2] == 1))
			{
				trialX = col * squareSize;
				collided = true;
			}
			// If the middle of the ball is an a wall, move it two columns
			// to the left
			if (onBoard(row + 1, col + 1) && (grid[row + 1][col + 1] == 1))
			{
				trialX = (col - 1) * squareSize;
				collided = true;
			}
			// If the left middle of the ball is in a wall, move it three
			// columns to the left
			if (onBoard(row + 1, col) && (grid[row + 1][col] == 1))
			{
				trialX = (col - 2) * squareSize;
				collided = true;
			}// If the ball does not collide with the wall, use the coordinates
				// for the ball's new position
			if (!collided)
			{
				trialX = newX;
			}
			// If there has been a collision, give the ball a smaller speed in
			// the opposite direction
			else
			{
				xVel = (float) (-xVel / 4);
			}
		}
		// If the ball is moving up, check for upwards collisions
		if (yVel < 0)
		{
			boolean collided = false;
			// If the top middle of the ball is on a wall, move the ball down
			// one row
			if (onBoard(row, col + 1) && (grid[row][col + 1] == 1))
			{
				trialY = (row + 1) * squareSize;
				collided = true;
			}
			// If the middle of the ball is on a wall, move the ball down two
			// rows
			if (onBoard(row + 1, col + 1) && (grid[row + 1][col + 1] == 1))
			{
				trialY = (row + 2) * squareSize;
				collided = true;
			}
			// If the bottom middle of the ball is on a wall, move the ball down
			// three rows
			if (onBoard(row + 2, col + 1) && (grid[row + 2][col + 1] == 1))
			{
				trialY = (row + 3) * squareSize;
				collided = true;
			}
			// If the ball has not collided with anything, use the new
			// coordinates for its position
			if (!collided)
			{
				trialY = newY;
			}
			// If there has been a collision give the ball slower speed in the
			// opposite direction
			else
			{
				yVel = (float) (-yVel / 4);
			}

		}
		// If the ball is moving down, check for collisions downwards
		else if (yVel > 0)
		{
			boolean collided = false;
			// If the bottom middle of the ball is on a wall, move it upwards by
			// one row
			if (onBoard(row + 2, col + 1) && (grid[row + 2][col + 1] == 1))
			{
				trialY = row * squareSize;
				collided = true;
			}
			// If the middle of the ball is on a wall, move it upwards by two
			// rows
			if (onBoard(row + 1, col + 1) && (grid[row + 1][col + 1] == 1))
			{
				trialY = (row - 1) * squareSize;
				collided = true;
			}
			// If the top middle of the ball is on a wall, move it upwards by
			// three rows
			if (onBoard(row, col + 1) && (grid[row][col + 1] == 1))
			{
				trialY = (row - 2) * squareSize;
				collided = true;
			}
			// If the ball has not collided with anything, use the new
			// coordinates for its position
			if (!collided)
			{
				trialY = newY;
			}
			// If there has been a collision give the ball slower speed in the
			// opposite direction
			else
			{
				yVel = (float) (-yVel / 4);
			}
		}
		// Check if the new coordinates are off the grid, if so place them back
		// on the grid
		// Check right wall
		if (trialX > squareSize * 30)
		{

			xCoord = squareSize * 30;
			if (xVel > 0)
			{
				xVel = -xVel / 4;
			}
		}
		// Check left wall
		else if (trialX < squareSize)
		{
			xCoord = squareSize;
			if (xVel < 0)
			{
				xVel = -xVel / 4;
			}
		}
		else
		{
			xCoord = trialX;
		}
		// Check bottom wall
		if (trialY > squareSize * 49)
		{
			yCoord = squareSize * 49;
			if (yVel > 0)
			{
				yVel = -yVel / 4;
			}
		}
		// Check top wall
		else if (trialY < squareSize)
		{
			yCoord = squareSize;
			if (yVel < 0)
			{
				yVel = -yVel / 4;
			}
		}
		else
		{
			yCoord = trialY;
		}
		// Get the new row and column of the ball
		int ballRow = (int) (yCoord / squareSize);
		int ballCol = (int) (xCoord / squareSize);
		// If the ball is at the goal, return 1
		if (grid[ballRow + 1][ballCol + 1] == 3 || grid[ballRow][ballCol] == 3
				|| grid[ballRow - 1][ballCol - 1] == 3)
			return 1;
		// If the ball is at a hole, return 2
		else if (grid[ballRow + 1][ballCol + 1] == 4
				|| grid[ballRow][ballCol] == 4
				|| grid[ballRow - 1][ballCol - 1] == 4)
			return 2;
		// Otherwise return 0
		else
			return 0;

	}

	/**
	 * Gets the x-coordinate of the ball
	 * 
	 * @return float x-coordinate
	 */
	public float returnX()
	{
		return xCoord;
	}

	/**
	 * Gets the y-coordinate of the ball
	 * 
	 * @return float y-coordinate
	 */
	public float returnY()
	{
		return yCoord;
	}

}
