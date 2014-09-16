package com.jm.pivot;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Keeps track of a the Activity which holds the title screen and buttons
 * 
 * @author Michelle Pokrass
 */
public class PivotActivity extends Activity {

	private int level;
	static int button;
	// static int[] levels = { 0, R.id.level1, R.id.level2, R.id.level3,
	// R.id.level4, R.id.level5 };
	Button[] levelButtons;
	private ImageView title;
	LinearLayout background;
	LinearLayout background2;

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
		setContentView(R.layout.main);

		// Match background variables to the id in the R file
		background = (LinearLayout) findViewById(R.id.background);
		background2 = (LinearLayout) findViewById(R.id.background2);
		
		
		// Create a splash image to display on start up
		// Match image variable to the id in the R file
		title = (ImageView) this.findViewById(R.id.titlePage);
		
		// Hide the image once the user taps the screen
		title.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				title.setVisibility(View.GONE);
				background2.setVisibility(View.VISIBLE);
				background2.setBackgroundResource(R.drawable.floor_resized);
				return true;

			}
		});

		// Initialize and create listeners for each button
		levelButtons = new Button[31];

		// Match the variable to the id in the R file
		levelButtons[1] = (Button) this.findViewById(R.id.level1);
		// levelButtons[1].setBackgroundColor(Color.WHITE);
		levelButtons[1].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 1;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[2] = (Button) this.findViewById(R.id.level2);
		levelButtons[2].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 2;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[3] = (Button) this.findViewById(R.id.level3);
		levelButtons[3].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 3;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[4] = (Button) this.findViewById(R.id.level4);
		levelButtons[4].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 4;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[5] = (Button) this.findViewById(R.id.level5);
		levelButtons[5].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 5;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[6] = (Button) this.findViewById(R.id.level6);
		levelButtons[6].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 6;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[7] = (Button) this.findViewById(R.id.level7);
		levelButtons[7].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 7;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[8] = (Button) this.findViewById(R.id.level8);
		levelButtons[8].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 8;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
		levelButtons[9] = (Button) this.findViewById(R.id.level9);
		levelButtons[9].setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				level = 9;
				Intent i = new Intent(PivotActivity.this,
						MazeActivity.class);
				i.putExtra("level", level);
				startActivity(i);
			}
		});
		// Match the variable to the id in the R file
				levelButtons[10] = (Button) this.findViewById(R.id.level10);
				levelButtons[10].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 10;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				// Match the variable to the id in the R file
				levelButtons[11] = (Button) this.findViewById(R.id.level11);
				levelButtons[11].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 11;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				// Match the variable to the id in the R file
				levelButtons[12] = (Button) this.findViewById(R.id.level12);
				levelButtons[12].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 12;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				// Match the variable to the id in the R file
				levelButtons[13] = (Button) this.findViewById(R.id.level13);
				levelButtons[13].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 13;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				// Match the variable to the id in the R file
				levelButtons[14] = (Button) this.findViewById(R.id.level14);
				levelButtons[14].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 14;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						try {
							startActivity(i);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				// Match the variable to the id in the R file
				levelButtons[15] = (Button) this.findViewById(R.id.level15);
				levelButtons[15].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 15;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[16] = (Button) this.findViewById(R.id.level16);
				levelButtons[16].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 16;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[17] = (Button) this.findViewById(R.id.level17);
				levelButtons[17].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 17;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[18] = (Button) this.findViewById(R.id.level18);
				levelButtons[18].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 18;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[19] = (Button) this.findViewById(R.id.level19);
				levelButtons[19].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 19;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[20] = (Button) this.findViewById(R.id.level20);
				levelButtons[20].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 20;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[21] = (Button) this.findViewById(R.id.level21);
				levelButtons[21].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 21;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[22] = (Button) this.findViewById(R.id.level22);
				levelButtons[22].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 22;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[23] = (Button) this.findViewById(R.id.level23);
				levelButtons[23].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 23;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[24] = (Button) this.findViewById(R.id.level24);
				levelButtons[24].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 24;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[25] = (Button) this.findViewById(R.id.level25);
				levelButtons[25].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 25;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[26] = (Button) this.findViewById(R.id.level26);
				levelButtons[26].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 26;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[27] = (Button) this.findViewById(R.id.level27);
				levelButtons[27].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 27;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[28] = (Button) this.findViewById(R.id.level28);
				levelButtons[28].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 28;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[29] = (Button) this.findViewById(R.id.level29);
				levelButtons[29].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 29;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
				
				// Match the variable to the id in the R file
				levelButtons[30] = (Button) this.findViewById(R.id.level30);
				levelButtons[30].setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						level = 30;
						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
					}
				});
		
		// Code doesn't work: Sets all the buttons to the last called level
		// for (button = 1; button < 5; button++) {
		// levelButtons[button] = (Button) this.findViewById(levels[button]);
		//
		// // Add the listener code to handle the levelButton being pressed
		// levelButtons[button].setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// level = button;
		// // NEED TO FIX^
		// Intent i = new Intent(FinalProject2Activity.this,
		// MazeActivity.class);
		// i.putExtra("level", button);
		// startActivity(i);
		// }
		// });
		//
		// }
	}

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
        final Dialog dialog = new Dialog(PivotActivity.this);
        dialog.setContentView(R.layout.dialogbox);
        dialog.setTitle("Help");
        dialog.setCancelable(true);

        //set up text
        TextView text = (TextView) dialog.findViewById(R.id.helpText);
        text.setText("In this maze style game, your objective is to get from the starting position to the target position"
				+ " without falling into any holes. As you progress through the levels the difficulty of the mazes will"
				+ " increase more and more! " + "\nGood luck!!!"+"\n\n\u00a9 2012 By Michelle Pokrass and Jonathan Chan");

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

						Intent i = new Intent(PivotActivity.this,
								MazeActivity.class);
						i.putExtra("level", level);
						startActivity(i);
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
	 * Prompts the user to pick a level first
	 * 
	 */
	private void restartGame() {
		// Make new Alert Box
		AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
		// Confirm selection
		alertbox.setMessage("Choose a level first!");
		// set a neutral button and create a listener
		alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			// do nothing when the button is clicked
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});

		alertbox.show();

	}

}