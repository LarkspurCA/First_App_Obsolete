package com.example.first_app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.content.Context;

import android.util.Log;

public class GolfcourseListActivity extends FragmentActivity
        implements GolfcourseListFragment.Callbacks {

    private boolean mTwoPane;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create data model and initialize with golf courses
        ArrayList<Golfcourse> courses = new DataModel("courses.txt").getCourses();

    	FragmentManager fm = getSupportFragmentManager();
    	Log.v("myApp", "List Activity: R.Layout.activity_golfcourse_list: " + R.layout.activity_golfcourse_list);
        setContentView(R.layout.activity_golfcourse_list);

        if (findViewById(R.id.golfcourse_detail_container) != null) {
            mTwoPane = true;
            
            GolfcourseDetailFragment df = (GolfcourseDetailFragment) fm.findFragmentByTag("Detail");
            if (df == null) {
	            // Initialize new detail fragment
	            Log.v("myApp", "List Activity: Initialize new detail view");
	            df = new GolfcourseDetailFragment();
	            Bundle args = new Bundle();
	            args.putParcelable("course", new Golfcourse("Welcome to Golf Droid"));
	            df.setArguments(args);
	            fm.beginTransaction().replace(R.id.golfcourse_detail_container, df, "Detail").commit();
            }  
        	else {
        		Log.v("myApp", "List Activity, Use existing Detail Fragment " + df);	
        	}

        }
        
        // Initialize the golfcourse list fragment
      
        	GolfcourseListFragment cf = (GolfcourseListFragment) fm.findFragmentByTag("List");
        	if ( cf == null) {
        		cf = new GolfcourseListFragment();
            	Bundle arguments = new Bundle();
            	arguments.putParcelableArrayList("courses", courses);
            	cf.setArguments(arguments);           	
        		Log.v("myApp", "List Activity: Create a new List Fragment " + cf);
            	fm.beginTransaction().replace(R.id.golfcourse_list, cf, "List").commit();
        	}
        	else {
        		Log.v("myApp", "List Activity: Use existing List Fragment " + cf);
        	}

    }
   

    private String getId() {
	// TODO Auto-generated method stub
	return null;
}


	@Override
//    public void onItemSelected(String id) {
	public void onItemSelected(Golfcourse c) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
//            arguments.putString(GolfcourseDetailFragment.ARG_ITEM_ID, id);
            arguments.putParcelable("course", c);
            GolfcourseDetailFragment fragment = new GolfcourseDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.golfcourse_detail_container, fragment, "Detail")
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, GolfcourseDetailActivity.class);
//            detailIntent.putExtra(GolfcourseDetailFragment.ARG_ITEM_ID, id);
            detailIntent.putExtra("course", c);
            startActivity(detailIntent);
        }
    }
	
	// Define a class to handle the custom data
	// Initially, just reads a file and returns an array of objects
    
	class DataModel {
		
        final ArrayList<Golfcourse> coursesArray = new ArrayList<Golfcourse>();
		
        // Initializer to read a text file into an array of golfcourse objects    
		public DataModel(String coursesFilename) {
	        String line;
	        BufferedReader br;
	        
	        try{
	        	br = new BufferedReader(new InputStreamReader(getResources().getAssets().open(coursesFilename)));
	
	        	while((line = br.readLine()) != null) {
	        		StringTokenizer sTok = new StringTokenizer(line, ":");
	        		Golfcourse gc = new Golfcourse(sTok.nextToken());
	        		gc.address = sTok.nextToken();
	        		coursesArray.add(gc);
	        		Log.v("myapp", gc.name + ", " + gc.address);
	        	}
	        }
	        catch (IOException e){
	        	Log.v("myapp", e.getMessage());
	        }	
			
		}
		
		// Method to retrieve courses
	    public ArrayList<Golfcourse> getCourses() {
	    	return coursesArray;
	    }
	}
}
