package com.jm.pivot;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Keeps track of the Activity which runs the BoardView
 * 
 * @author Michelle Pokrass
 */
public class MazeActivity extends Activity implements SensorEventListener {
	
	// Instance variables for the activity
	private BoardView boardView;
	private int picSize;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private Ball ball;
	private int level;
	private int isWin;
	
	/**
	 * Called when the activity is first started
	 * 
	 * @param savedInstanceState
	 *            If the app was closed due to memory usage from the android
	 *            device, use the saved information to recreate the activity
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.board);

		Intent i = getIntent();
		level = i.getIntExtra("level", 1);
		//Get the sensors and sensor managers
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		boardView = (BoardView) findViewById(R.id.boardView);
		//Get the display and dimensions of the screen
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		//Get the size of the images
		if (width > height) {
			picSize = width / 52;
		} else {
			picSize = height / 52;
		}
		//Start the view and create a ball onject
		boardView.initBoardView(picSize, level);
		ball = new Ball(boardView.getStartX(), boardView.getStartY(),
				boardView.getGrid(), picSize);

	}
	
	/**
	 * Called when app is resumed
	 */
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_UI);
	}
	
	/**
	 * Called when app is paused
	 */
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
	
	/**
	 * Abstract method which must be overridden
	 * Called when the accuracy changes
	 * 
	 * @param arg0
	 * 			Contains information from the sensor
	 * @param arg1
	 * 			Contains information from the sensor
	 */
	
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

	/**
	 * Called when accelerometer is changed due to tilting
	 * 
	 * @param event
	 *            Holds the information regarding how much the orientation of
	 *            the android device has changed
	 */
	public void onSensorChanged(SensorEvent event) {
		
		// Create an array to hold the values of the changes in the sensor
		float[] values = event.values;
		
		 final float alpha = 0.8f;
		 float[] gravity = {0f, 0f, 0f};
		 float[] linear_acceleration = {0f, 0f, 0f};

         gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
         gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
         gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

         linear_acceleration[0] = event.values[0] - gravity[0];
         linear_acceleration[1] = event.values[1] - gravity[1];
         linear_acceleration[2] = event.values[2] - gravity[2];
		
		// Check if you won and move
		isWin = ball.move(-linear_acceleration[0], linear_acceleration[1]);
		// If you win, go to the next level
		if (isWin == 1 && level != 30) {
			boardView.nextGame();
			ball = new Ball(boardView.getStartX(), boardView.getStartY(),
					boardView.getGrid(), picSize);
		}
		// If you fell into a hole, redo the level
		if (isWin == 2) {
			boardView.redoGame();
			ball = new Ball(boardView.getStartX(), boardView.getStartY(),
					boardView.getGrid(), picSize);
		}

		// view.addText(String.format("X: %1$1.2f, Y: %2$1.2f, Z: %3$1.2f",
		// values[0], values[1], values[2]));
		boardView.setX(ball.returnX());
		boardView.setY(ball.returnY());
		boardView.invalidate();
	}
	
	
	// Overide the menu to be able to use the restart level function
	/**
	 * Called when the menu button is tapped
	 * 
	 * @param menu
	 *            The menu to create
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	/**
	 * Called when an option from the menu is selected. Calls methods depending
	 * on which option was selected
	 * 
	 * @param item
	 *            The item which was selected
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.new_game:
			newGame();
			return true;
		case R.id.showHelp:
			showHelp();
			return true;
		case R.id.restart:
			restartGame();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Called when the user selects the "Help" option
	 * Shows a dialog box containing the help files
	 * 
	 */
	private void showHelp() {
		 //set up dialog
        final Dialog dialog = new Dialog(MazeActivity.this);
        dialog.setContentView(R.layout.dialogbox);
        dialog.setTitle("Help");
        dialog.setCancelable(true);

        //set up text
        TextView text = (TextView) dialog.findViewById(R.id.helpText);
        text.setText("In this maze style game, your objective is to get from the starting position to the target position"
				+ " without falling into any holes. As you progress through the levels the difficulty of the mazes will"
				+ " increase more and more! " + "\nGood luck!!!" + "\n\n\u00a9 2012 By Michelle Pokrass and Jonathan Chan");

        //set up image view
        ImageView img = (ImageView) dialog.findViewById(R.id.helpPic);
        img.setImageResource(R.drawable.help2);

        //set up button
        Button button = (Button) dialog.findViewById(R.id.helpButton);
        button.setOnClickListener(new OnClickListener() {
        
            public void onClick(View v) {
        	dialog.hide();
            }
        });
        //show dialog   
        dialog.show();
	}

	/**
	 * Called when the user selects the "New Game" option
	 * Starts a new game by bringing the user back to level 1
	 * 
	 */
	private void newGame() {
		// Make new Alert Box
		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		// Confirm selection
		alertbox.setMessage("Are you sure you want to start a new game?");
		// set a positive/yes button and create a listener
		alertbox.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						// Set to level 1

						level = 1;

						boardView.initBoardView(picSize, level);
						ball = new Ball(boardView.getStartX(), boardView
								.getStartY(), boardView.getGrid(), picSize);
					}
				});

		// set a negative/no button and create a listener
		alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
			// do nothing when the button is clicked
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});

		alertbox.show();

	}
	
	/**
	 * Called when the user selects the "Restart Level" option
	 * Restarts the current level
	 * 
	 */
	private void restartGame() {
		// Make new Alert Box
		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		// Confirm selection
		alertbox.setMessage("Are you sure you want to restart the level?");
		// set a positive/yes button and create a listener
		alertbox.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						boardView.initBoardView(picSize, level);
						ball = new Ball(boardView.getStartX(), boardView
								.getStartY(), boardView.getGrid(), picSize);
					}
				});

		// set a negative/no button and create a listener
		alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
			// do nothing when the button is clicked
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});

		alertbox.show();

	}

}
