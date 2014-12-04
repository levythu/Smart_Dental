package com.edu.thss.smartdental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.support.v4.app.FragmentManager;

public class BBSInFragment extends Fragment {
	
	private RadioGroup radioGroup;
	private FragmentManager fragmentManager;
	
	public BBSInFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in, container,false);
		
		fragmentManager = getFragmentManager();
		radioGroup = (RadioGroup)rootView.findViewById(R.id.bbs_in_tab);
		radioGroup.check(R.id.bbs_in_tab_view);
		changeFragment(0);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch(checkedId){
				case R.id.bbs_in_tab_view: 
					changeFragment(0); 
					break;
				case R.id.bbs_in_tab_select:
					changeFragment(1);
					break;
				case R.id.bbs_in_tab_group:
					changeFragment(2);
					break;
				case R.id.bbs_in_tab_news:
					changeFragment(3);
					break;
				case R.id.bbs_in_tab_post:
					changeFragment(4);
					break;
				//TODO ADD OTHER FRAGMENT
				}
               
			}} );
		
		return rootView;
	}
	
	private void changeFragment(int index){
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		Fragment tempfragment = null;
		switch(index){
		case 0:
			tempfragment = new BBSInTabViewFragment();
			break;
		case 1:
			tempfragment = new BBSInTabSelectFragment();
			break;
		case 2:
			//tempfragment = new BBSInTabSearchFragment();
			tempfragment = new BBSFragment();
			break;
		case 3:
			tempfragment = new BBSInTabNewsFragment();
			break;
		case 4:
			tempfragment = new BBSInTabPostFragment();
			break;
		}
		if (tempfragment != null) {
			transaction.replace(R.id.bbs_in_tab_content, tempfragment);
    	transaction.commit();
        }
	}

}