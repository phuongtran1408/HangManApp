package com.example.hangman;

import java.util.ArrayList;
import java.util.Random;
import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EasyActivity extends ActionBarActivity {

	private String let1,let2,let3,let4, one, two, three, four;
	private ArrayList<String> wordlist;
	private Random randomGenerator = new Random();
	private EditText edit1, edit2, edit3, edit4;
	private int count = 0;
	private int maxAttempts = 10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy);
		
		wordlist = new ArrayList<String>();
		
		wordlist.add("LYNX");
		wordlist.add("MIND");
		wordlist.add("MEGA");
		wordlist.add("BEST");
		wordlist.add("SEXY");
		wordlist.add("BEAT");
		wordlist.add("FOUR");
		wordlist.add("POOP");
		wordlist.add("CODE");
		wordlist.add("MEET");
		wordlist.add("GOAL");
		wordlist.add("MEAL");
		wordlist.add("FOOD");
		wordlist.add("HELP");
		wordlist.add("MINE");
		wordlist.add("COOK");
		wordlist.add("COOL");
		wordlist.add("FANS");
		wordlist.add("BEAN");
		wordlist.add("LIST");
		wordlist.add("WORD");
		wordlist.add("QUIT");
		wordlist.add("LIFE");
		wordlist.add("DIED");
		wordlist.add("BOOK");
		wordlist.add("ZOOM");
		wordlist.add("COPE");
		wordlist.add("EASY");
				
		
		int index = randomGenerator.nextInt(wordlist.size());
		String word = wordlist.get(index);
		
		one = word.substring(0, 1);
		two = word.substring(1, 2);
		three = word.substring(2, 3);
		four = word.substring(3, 4);
		
		// Get user input from fields
		edit1 = (EditText)findViewById(R.id.editText1);
		edit2 = (EditText)findViewById(R.id.editText2);
		edit3 = (EditText)findViewById(R.id.editText3);
		edit4 = (EditText)findViewById(R.id.editText4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.easy, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void clickSubmit(View view) {		
		// Turn to string
		let1 = edit1.getText().toString().trim();
		let2 = edit2.getText().toString().trim();
		let3 = edit3.getText().toString().trim();
		let4 = edit4.getText().toString().trim();
		
		if(let1.equals("") || let2.equals("") || let3.equals("") || let4.equals("")) {
			AlertDialog.Builder builder = new AlertDialog.Builder(EasyActivity.this);
			builder.setMessage("One of the index is empty!");
			builder.setTitle("WARNING!");
			builder.setPositiveButton(android.R.string.ok, null);
			AlertDialog dialog = builder.create();
			dialog.show();
		}
		else {		
			count = 0;
			
			check(one, let1, edit1);
			check(two, let2, edit2);
			check(three, let3, edit3);
			check(four, let4, edit4);
			
			if(count != 4) {
				maxAttempts--;
				if(maxAttempts != 0) popUp();
				else {
					AlertDialog.Builder builder = new AlertDialog.Builder(EasyActivity.this);
					builder.setMessage("The answer is " + one + two + three + four);
					builder.setTitle("Sorry! Nice try!");
					builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(EasyActivity.this, EasyActivity.class);
					    	startActivity(intent);
							
						}
					});
					AlertDialog dialog = builder.create();
					dialog.show();
				}
			}
			else if(count == 4){
				Intent intent = new Intent(this, WinActivity.class);
		    	startActivity(intent);
			}
		}
	}
	
	public void check(String comp1, String comp2, EditText edit) {
		if(!comp1.equals(comp2)) {
			edit.setBackgroundColor(Color.RED);
		}
		else{
			edit.setBackgroundColor(Color.GREEN);
			count++;
		}
	}
	
	public void popUp(){
    	// alert dialog pop up too many login attempts
		AlertDialog.Builder builder = new AlertDialog.Builder(EasyActivity.this);
		builder.setMessage(maxAttempts + " attempts left.");
		builder.setTitle("Nope.");
		builder.setPositiveButton(android.R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.show();
    }
}
