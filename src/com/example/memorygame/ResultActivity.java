package com.example.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnClickListener
{
	static int score=0;
	TextView resultTextView,scoreTextView;
	Button playAgainButton,nextLevelButton,quitButton;
	String resultFromPlay;
	MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_layout);

		resultTextView=(TextView)findViewById(R.id.resultTextView);
		playAgainButton=(Button)findViewById(R.id.playAgainButton);
		nextLevelButton=(Button)findViewById(R.id.nextLevelButton);
		quitButton=(Button)findViewById(R.id.quitButton);
		scoreTextView=(TextView)findViewById(R.id.scoreTextView);
		
		Intent recieve=getIntent();
		resultFromPlay=recieve.getExtras().getString("result");
		mp = MediaPlayer.create(this, R.raw.shut);
		  
		if(resultFromPlay.equals("Success"))
		{	
			if(PlayActivity.levelCount < 3)
			{
				score=score+30;
				playAgainButton.setVisibility(View.INVISIBLE);
				resultTextView.setText("Success :)");	
			}
			else
			{
				score=score+40;
				playAgainButton.setVisibility(View.GONE);
				nextLevelButton.setVisibility(View.GONE);
				resultTextView.setText("Game over.You Won!!");	
			}
			scoreTextView.setText("Your Score:"+score);
		}
		else //resultFromPlay is "Failure"
		{
			scoreTextView.setText("Your Score:"+score);
			nextLevelButton.setVisibility(View.INVISIBLE);
			resultTextView.setText("Failure :(");
		}
		
		playAgainButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				playAgainButton.setBackgroundColor(Color.parseColor("#02f68e"));
				if(SettingsActivity.tbState==true)
					mp.start();
				Intent pass=new Intent(ResultActivity.this,PlayActivity.class);
				startActivity(pass);
			}
		});
		
		nextLevelButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				nextLevelButton.setBackgroundColor(Color.parseColor("#02f68e"));
				if(SettingsActivity.tbState==true)
					mp.start();
				PlayActivity.levelCount++;
				Intent pass=new Intent(ResultActivity.this,PlayActivity.class);
				startActivity(pass);
			}
		});
		
		quitButton.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				quitButton.setBackgroundColor(Color.parseColor("#02f68e"));
				PlayActivity.levelCount=1;
        		score=0;
				if(SettingsActivity.tbState==true)
					mp.start();
				Intent pass=new Intent(ResultActivity.this,MemoryActivity.class);
				startActivity(pass);
			}
		});
	}

	@Override
	public void onBackPressed() 
	{
		 new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Quit")
	        .setMessage("Are you sure you want to exit the game?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	        {
	        	@Override
	        	public void onClick(DialogInterface dialog, int which) 
	        	{
	        		PlayActivity.levelCount=1;
	        		score=0;
	        		Intent pass=new Intent(ResultActivity.this,MemoryActivity.class);
					startActivity(pass);
	        	}
	        })
	    .setNegativeButton("No", null)
	    .show();
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}	
