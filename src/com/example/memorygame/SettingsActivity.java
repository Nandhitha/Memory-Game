package com.example.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.view.View;

public class SettingsActivity extends Activity
{
	static boolean tbState=true;
	ToggleButton tb;
	TextView tv;
	MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
		tb=(ToggleButton)findViewById(R.id.toggleButton);
		tb.setChecked(tbState);
		mp = MediaPlayer.create(this, R.raw.shut);
		
		tb.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				tb.setBackgroundColor(Color.parseColor("#02f68e"));
				if(SettingsActivity.tbState==true)
					mp.start();
				if(tb.isChecked()==true)
				{	
					tbState=true;
				}
				else
				{
					tbState=false;
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() 
	{
		PlayActivity.levelCount=1;
	    ResultActivity.score=0;
	    Intent pass=new Intent(SettingsActivity.this,MemoryActivity.class);
	    startActivity(pass);
	}
	
}
