package com.example.memorygame;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


public class MemoryActivity extends Activity 
{

	Button playButton,instructionsButton,settingsButton,exitButton;
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.memory_layout1);
		playButton=(Button)findViewById(R.id.playButton);
		instructionsButton=(Button)findViewById(R.id.instructionsButton);
		settingsButton=(Button)findViewById(R.id.settingsButton);
		exitButton=(Button)findViewById(R.id.exitButton);
		mp = MediaPlayer.create(this, R.raw.home);
	
		playButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				playButton.setBackgroundColor(Color.parseColor("#02f68e"));
				if(SettingsActivity.tbState==true)
					mp.start();
				Intent i=new Intent(MemoryActivity.this, PlayActivity.class);
				startActivity(i);           
				
			}
		});
		
		instructionsButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				instructionsButton.setBackgroundColor(Color.parseColor("#02f68e"));
				if(SettingsActivity.tbState==true)
					mp.start();
				Intent i=new Intent(MemoryActivity.this, InstructionActivity.class);
		        startActivity(i);	
			}
		});
		
		settingsButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				settingsButton.setBackgroundColor(Color.parseColor("#02f68e"));
				if(SettingsActivity.tbState==true)
					mp.start();
				Intent i=new Intent(MemoryActivity.this, SettingsActivity.class);
		        startActivity(i);	
			}
		});
		
		exitButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				exitButton.setBackgroundColor(Color.parseColor("#02f68e"));
				if(SettingsActivity.tbState==true)
					mp.start();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
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
	        		Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
	        	}
	        })
	    .setNegativeButton("No", null)
	    .show();
	}
}
