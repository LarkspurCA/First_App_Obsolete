package com.example.first_app;

import java.util.ArrayList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;

public class GolfcourseDetailFragment extends Fragment {
	
  private Golfcourse course;

    public static final String ARG_ITEM_ID = "item_id";


    public GolfcourseDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("myApp", "Detail Fragment: onCreate");
        /*
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        */    
            // If intent arguments have a course object, get it
        if (getArguments().containsKey("course")) {
            course = getArguments().getParcelable("course");
      	  Log.v("myApp", "Detail Fragment: Retrieved course from argument");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	Log.v("myApp", "Detail Fragment: R.Layout.fragment_golfcourse_detail: " + R.layout.fragment_golfcourse_detail);
        View rootView = inflater.inflate(R.layout.fragment_golfcourse_detail, container, false);
        if (course != null) {
            ((TextView) rootView.findViewById(R.id.golfcourse_detail)).setText(course.name + " Details");
        }
        else {
        ((TextView) rootView.findViewById(R.id.golfcourse_detail)).setText("Welcome to Golf Droid");
        }
        return rootView;
    }


@Override
public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Log.v("myApp", "Detail Fragment: Ready to save details fragment to outState, Id = " + getId());
//    outState.putParcelable("course", course);
	}

}
