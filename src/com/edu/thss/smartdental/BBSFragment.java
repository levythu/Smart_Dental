package com.edu.thss.smartdental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.support.v4.app.FragmentManager;

public class BBSFragment extends Fragment {
	
	private RadioGroup radioGroup;
	private FragmentManager fragmentManager;
	
	public BBSFragment() {
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs, container,false);
		
		fragmentManager = getFragmentManager();
		radioGroup = (RadioGroup)rootView.findViewById(R.id.bbs_tab);
		radioGroup.check(R.id.bbs_tab_choose);
		changeFragment(0);
		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch(checkedId){
				case R.id.bbs_tab_choose: 
					changeFragment(0); 
					break;
				case R.id.bbs_tab_join:
					changeFragment(1);
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
			tempfragment = new BBSTabChooseFragment();
			break;
		case 1:
			tempfragment = new BBSTabJoinFragment();
		}
		if (tempfragment != null) {
			transaction.replace(R.id.bbs_tab_content, tempfragment);
    	transaction.commit();
        }
	}

}