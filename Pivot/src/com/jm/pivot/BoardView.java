package com.jm.pivot;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Keeps track of a Maze and it's View
 * 
 * @author Michelle Pokrass
 */
public class BoardView extends View {

	// Constant for max level
	public final static int maxLvl = 30;

	// Instance variables for each board
	private static int squareSize;
	private Bitmap wall;
	private Bitmap floor;
	private Bitmap ball;
	private Bitmap target;
	private Bitmap hole;
	private boolean gameOver;
	private int level;
	Grid newGrid;
	float ballX;
	float ballY;
	private float startX;
	private float startY;

	// May still need this
	private final Paint mPaint = new Paint();

	// implement grid

	/**
	 * Constructs a board
	 * 
	 * @param context
	 *            the context(activity) to draw the maze in
	 * @param attrs
	 *            Attribute sets which this view has
	 * @param defStyle
	 *            the default style of the grid
	 */
	public BoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initBoardView(25, 1); // Included to avoid error when drawing the board
								// in
								// main.xml

		// Initialize ball positions
		ballX = 100;
		ballY = 100;
	}

	/**
	 * Constructs a board - used by main.xml
	 * 
	 * @param context
	 *            the context(activity) to draw the maze in
	 * @param attrs
	 *            Attribute sets which this view has
	 */
	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		try {
			display.getSize(size);
			//Toast.makeText(getContext(), "size: "+ size.x/40 , Toast.LENGTH_LONG).show();
			initBoardView(size.x/40, 1);
		} catch (Exception e) {
			initBoardView(25, 1);
		}
	}

	/**
	 * Initialize the board based on the given size of each tile and which level
	 * to draw
	 * 
	 * @param squareSize
	 *            size of each tile on the grid
	 * @param level
	 *            the level to implement from a text file
	 */
	public void initBoardView(int squareSize, int level) {
		// Set up the board
		BoardView.squareSize = squareSize;
		setFocusable(true);
		this.level = level;

		// Load and scale the two images
		Resources r = this.getContext().getResources();

		// Create wall from image in drawable resources
		Bitmap bitmap = Bitmap.createBitmap(squareSize, squareSize,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Drawable wallImage = r.getDrawable(R.drawable.wall);

		wallImage.setBounds(0, 0, squareSize, squareSize);
		wallImage.draw(canvas);
		wall = bitmap;

		// Create floor from image in drawable resources
		bitmap = Bitmap.createBitmap(squareSize, squareSize,
				Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		Drawable floorImage = r.getDrawable(R.drawable.floor);

		floorImage.setBounds(0, 0, squareSize, squareSize);
		floorImage.draw(canvas);
		floor = bitmap;

		// Create hole from image in drawable resources
		bitmap = Bitmap.createBitmap(2 * squareSize, 2 * squareSize,
				Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		Drawable holeImage = r.getDrawable(R.drawable.hole2);

		holeImage.setBounds(0, 0, 2 * squareSize, 2 * squareSize);
		holeImage.draw(canvas);
		hole = bitmap;

		// Create target from image in drawable resources
		bitmap = Bitmap.createBitmap(2 * squareSize, 2 * squareSize,
				Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		Drawable targetImage = r.getDrawable(R.drawable.end2);

		targetImage.setBounds(0, 0, 2 * squareSize, 2 * squareSize);
		targetImage.draw(canvas);
		target = bitmap;

		// Create ball from image in drawable resources
		bitmap = Bitmap.createBitmap(2 * squareSize, 2 * squareSize,
				Bitmap.Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		Drawable ballImage = r.getDrawable(R.drawable.ball3);

		ballImage.setBounds(0, 0, 2 * squareSize, 2 * squareSize);
		ballImage.draw(canvas);
		ball = bitmap;

		// Initialize the grid based on the given level
		newGrid = new Grid(this, level);

		// Look for starting position of the ball
		for (int row = 0; row < 49; row++) {
			for (int col = 0; col < 30; col++) {
				if (newGrid.getObject(row, col) == 5) {
					startX = col * 27;
					startY = row * 27;
				}
			}
		}
	}

	/**
	 * Save game state so that the user does not lose anything if the game
	 * process is killed while we are in the background.
	 * 
	 * @return a Bundle with this view's state
	 */
	public Bundle saveState() {
		// Save the current game state to a new Bundle
		Bundle map = new Bundle();
		map.putInt("level", level);
		map.putBoolean("gameOver", gameOver);
		return map;
	}

	/**
	 * Restore game state if our process is being re-launched
	 * 
	 * @param icicle
	 *            a Bundle containing the game state
	 */
	public void restoreState(Bundle icicle) {
		// Restore the game state from the given Bundle
		level = icicle.getInt("level");
		newGrid = new Grid(this, level);
		gameOver = icicle.getBoolean("gameOver");
		invalidate();
	}

	/**
	 * Sets up the next level
	 */
	public void nextGame() {
		// If the player is not at the maximum level, go to next level
		if (level != maxLvl)
			initBoardView(squareSize, level + 1);
		// If the player is at the max level, go to the first level
		else {
			level = 1;
			initBoardView(squareSize, level);
		}

	}

	/**
	 * Sets up the same level if the player falls into a hole
	 */
	public void redoGame() {
		// reinitialize the board
		initBoardView(squareSize, level);
	}

	/**
	 * Draws the maze
	 * 
	 * @param canvas
	 *            the canvas to draw on
	 */
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// Initialize variables for phase shift and vertical shift which let the
		// tiles shift over when drawn
		int pShift = 0;
		int vShift = 0;
		
		// Loop through the grid
		// Draw walls for 1
		// Draw targets for 3
		// Draw holes for 4
		for (int row = 0; row < 49; row++) {
			for (int col = 0; col < 30; col++) {
				if (newGrid.getObject(row, col) == 1)
					canvas.drawBitmap(wall, pShift, vShift, null);
				else if (newGrid.getObject(row, col) == 0)
					;
				// canvas.drawBitmap(floor, pShift, vShift, null);
				else if (newGrid.getObject(row, col) == 3) {
					canvas.drawBitmap(target, pShift, vShift, null);
				} else if (newGrid.getObject(row, col) == 4) {
					canvas.drawBitmap(hole, pShift, vShift, null);
				}
				// Shift the tile over by the square size
				pShift += squareSize;
			}
			// Shift the tile up by the square size
			vShift += squareSize;
			// Set horizontal shift back to 0 for the new line
			pShift = 0;
		}
		// Draw the ball
		canvas.drawBitmap(ball, ballX, ballY, null);
		mPaint.setColor(Color.WHITE);
		
		// Testing values
		//canvas.drawText("( " + ballX + " , " + ballY + " )", 100, 100, mPaint);
		//canvas.drawText("" + squareSize, 100, 200, mPaint);
	}
	
	/**
	 * Sets the ball's x position to the specified x-coordinate
	 * 
	 * @param x
	 * 		x-coordinate to set ball to
	 */
	public void setX(float x) {
		ballX = x;
	}
	
	/**
	 * Sets the ball's x position to the specified y-coordinate
	 * 
	 * @param y
	 * 		y-coordinate to set ball to
	 */
	public void setY(float y) {
		ballY = y;
	}
	
	/**
	 * Gets the current level's grid
	 * 
	 * @return the int array of the grid
	 */
	public int[][] getGrid() {
		return newGrid.getGrid();
	}
	
	/**
	 * Gets the ball's x coordinate starting position
	 * 
	 * @return the start position for the x coordinate
	 */
	public float getStartX() {
		return startX;
	}
	
	/**
	 * Gets the ball's y coordinate starting position
	 * 
	 * @return the start position for the y coordinate
	 */
	public float getStartY() {
		return startY;
	}

}
