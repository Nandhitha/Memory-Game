package com.example.memorygame;


import java.util.ArrayList;
import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class PlayActivity extends Activity implements OnClickListener
{
	ArrayList<String> al,sl;
	ListView listViewContent;
	ArrayAdapter<String> adapter;
	AdapterView<?> parent;
	TextView level;
	ImageButton up,down;
	Button done;
	int pos,flag=0;
	Bundle localBundle;
	static int levelCount=1;
	MediaPlayer mpClick,mpShut;
	ProgressBar pb;
	int total=100;
	CountDownTimer cdt;    
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_layout);
		al=new ArrayList<String>();
		sl=new ArrayList<String>();
		
		if(levelCount==1)
		{
			al.add("Argentina");
			al.add("Antartica");
			al.add("Germany");
			al.add("Afganistan");
			
			sl.add("Argentina");
			sl.add("Antartica");
			sl.add("Germany");
			sl.add("Afganistan");
		}
		if(levelCount==2)
		{
			al.add("India");
			al.add("France");
			al.add("Indonesia");
			al.add("Cambodia");	
			al.add("Canada");
			al.add("Iran");
			al.add("Iraq");
			
			sl.add("India");
			sl.add("France");
			sl.add("Indonesia");
			sl.add("Cambodia");	
			sl.add("Canada");
			sl.add("Iran");
			sl.add("Iraq");
		}
		if(levelCount==3)
		{
			al.add("Haiti");
			al.add("Hungary");
			al.add("Nepal");
			al.add("Kenya");
			al.add("Netherland");
			al.add("Korea");
			al.add("Niger");
			al.add("India");
			al.add("Norway");
			
			
			sl.add("Haiti");
			sl.add("Hungary");
			sl.add("Nepal");
			sl.add("Kenya");
			sl.add("Netherland");
			sl.add("Korea");
			sl.add("Niger");
			sl.add("India");
			sl.add("Norway");
	
		}
		//display list view contents from array 
		adapter	= new ArrayAdapter<String>(this, R.layout.listview_layout, al);
		listViewContent = (ListView) findViewById(R.id.listViewContent);
		listViewContent.setAdapter(adapter);
		listViewContent.setEnabled(false); //disable selection
		listViewContent.setBackgroundColor(Color.parseColor("#AD2EEC"));
		
		up= (ImageButton)findViewById(R.id.upImageButton);
		down= (ImageButton)findViewById(R.id.downImageButton);
		done=(Button)findViewById(R.id.doneButton);
		up.setVisibility(View.INVISIBLE);
        down.setVisibility(View.INVISIBLE);
        done.setVisibility(View.INVISIBLE);
        level=(TextView)findViewById(R.id.levelTextView);
        level.setText("Level "+levelCount);
        mpClick = MediaPlayer.create(this, R.raw.click);
  
        
        pb=(ProgressBar)findViewById(R.id.progressBar);
       /* pb.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(Color.parseColor("#02f68e")),
                android.graphics.PorterDuff.Mode.SRC_IN);*/
        
        //shuffle and display list view after 5 seconds
		new CountDownTimer(5000, 1000) {

		     public void onTick(long millisUntilFinished) 
		     {
		         total=(int)millisUntilFinished/50;
		         pb.setProgress( total);
		     }

		     public void onFinish() 
		     {
		    	 pb.setProgress( 0);
		    	 shuffleArray(al);
		         listViewContent.setAdapter(adapter);
		         up.setVisibility(View.VISIBLE);
		         down.setVisibility(View.VISIBLE);
		         done.setVisibility(View.VISIBLE);
		         listViewContent.setEnabled(true);
		     }
		  }.start();
		
		/*listViewContent.postDelayed(new Runnable() 
	    {
	        public synchronized void run() 
	        {	
	            shuffleArray(al);
	            listViewContent.setAdapter(adapter);
	            up.setVisibility(View.VISIBLE);
	            down.setVisibility(View.VISIBLE);
	            done.setVisibility(View.VISIBLE);
	            listViewContent.setEnabled(true);
	        }
	    }, 4000); //5 seconds delay  
	    */
		//get position of item
		listViewContent.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) {
		        	pos=position;
		        	
		        }});
				
		up.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				String temp;
				if(SettingsActivity.tbState==true)
					mpClick.start();
				if(pos!=0)
				{
					temp=al.get(pos);
					al.set(pos,al.get(pos-1));
					al.set(pos-1,temp);
					pos--;
					listViewContent.setAdapter(adapter);
				}				
			}
		});
	
		
		down.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				String temp;
				if(SettingsActivity.tbState==true)
					mpClick.start();
				if(pos!=al.size()-1)
				{
					temp=al.get(pos);
					al.set(pos,al.get(pos+1));
					al.set(pos+1,temp);	
					pos++;
					listViewContent.setAdapter(adapter);
				}
			}
		});
		
		mpShut = MediaPlayer.create(this, R.raw.shut);
		done.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				if(SettingsActivity.tbState==true)
					mpShut.start();
				for(int i=0;i<al.size();i++)
				{
					if(al.get(i)!=sl.get(i))
						flag=1;
				}
				//move to result activity
				if(flag==0)
				{
					//success
					Intent pass=new Intent(PlayActivity.this, ResultActivity.class);
					pass.putExtra("result","Success");
			        startActivity(pass);	
				}
				else
				{
					//failure
					Intent pass=new Intent(PlayActivity.this, ResultActivity.class);
					pass.putExtra("result", "Failure");
			        startActivity(pass);	
				}
			}});
	}
	
	
			
	public void shuffleArray(ArrayList<String> ar)
	{
		Random rnd = new Random();
	    for (int i = ar.size() - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      String a = ar.get(index);
	      ar.set(index,ar.get(i));
	      ar.set(i,a);
	    }
		/*
		int topIndex=0,bottomIndex=ar.size()-1;
		String temp;
		while(topIndex < ar.size()/2 && bottomIndex > ar.size()/2)
		{
			temp=ar.get(topIndex);
			ar.set(topIndex,ar.get(bottomIndex));
			ar.set(bottomIndex,temp);
			topIndex++;
			bottomIndex--;
		}
		//exchange middle and last element of array
		temp=ar.get(ar.size()/2);
		ar.set(ar.size()/2,ar.get(ar.size()-1));
		ar.set(ar.size()-1,temp);*/
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
	        		levelCount=1;
	        		ResultActivity.score=0;
	        		Intent pass=new Intent(PlayActivity.this,MemoryActivity.class);
					startActivity(pass);
	        	}
	        })
	    .setNegativeButton("No", null)
	    .show();
	}
	
	@Override
	public void onClick(DialogInterface arg0, int arg1) 
	{
		// TODO Auto-generated method stub		
	}
	
}
