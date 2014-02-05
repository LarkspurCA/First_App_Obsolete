package com.example.first_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;


public class GolfcourseDetailActivity extends FragmentActivity {
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golfcourse_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
        	// Get the course to display and add to fragment argument
            Bundle arguments = new Bundle();
            arguments.putParcelable("course", getIntent().getParcelableExtra("course"));
//            arguments.putString(GolfcourseDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(GolfcourseDetailFragment.ARG_ITEM_ID));
            GolfcourseDetailFragment fragment = new GolfcourseDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.golfcourse_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, GolfcourseListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
}
