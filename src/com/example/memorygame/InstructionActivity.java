package com.example.memorygame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class InstructionActivity extends Activity
{
	TextView instruction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.instruction_layout);
		instruction=(TextView)findViewById(R.id.instructionTextView);
		instruction.setText(Html.fromHtml("<h2>Instructions</h2><br>This game contains 3 levels.<br><br>List of words appears in a list and disappears after 5 seconds.<br><br>Sort the words in correct order using up and down arrow buttons"));
		instruction.setTextColor(Color.WHITE);
	}
	
	@Override
	public void onBackPressed() 
	{
		PlayActivity.levelCount=1;
	    ResultActivity.score=0;
	    Intent pass=new Intent(InstructionActivity.this,MemoryActivity.class);
	    startActivity(pass);
	}
	
}
