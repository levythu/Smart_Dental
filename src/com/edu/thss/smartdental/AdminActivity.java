package com.edu.thss.smartdental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioGroup;
import android.util.Log;

public class AdminActivity extends FragmentActivity {

	RadioGroup radioGroup;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		radioGroup = (RadioGroup)findViewById(R.id.admin_tab);
		radioGroup.check(R.id.add_single);
		changeFragment(0);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
				case R.id.add_single: 
					changeFragment(0); 
					break;
				case R.id.add_many:
					changeFragment(1);
					break;
				}
			}} );
	}
	
	private void changeFragment(int position){
		Fragment fragment  = null;
		switch (position){
		case 0: 
			fragment = new InfoFragment();
			break;
		case 1: 
			fragment = new EMRFragment();
			break;
		default:
			break;
		}
		if(fragment != null){
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.doctor_frame, fragment).commit();
		}
		else{
			Log.e("AdminActivity", "Error in creating fragment");
		}
	}
}
