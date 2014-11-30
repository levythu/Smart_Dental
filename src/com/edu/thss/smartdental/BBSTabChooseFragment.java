package com.edu.thss.smartdental;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Button;
public class BBSTabChooseFragment extends Fragment {

	private FragmentManager fragmentManager;
	private Button choosing;
	
	public BBSTabChooseFragment() {
		
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_choose, container, false);
		fragmentManager = getFragmentManager();
		choosing = (Button)rootView.findViewById(R.id.bbs_choose_button1);
		choosing.setOnClickListener(new View.OnClickListener(){
			 public void onClick(View v) {
				// FragmentTransaction transaction = fragmentManager.beginTransaction();
					Fragment fragment= new BBSInFragment();
					//FragmentManager fragmentManager = getSupportFragmentManager();
					fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
					
					/*mDrawerList.setItemChecked(position, true);
					mDrawerList.setSelection(position);
					setTitle(mNavMenuTitles[position]);
					mDrawerLayout.closeDrawer(mDrawerList);*/
			 }
		});
		return rootView;
	}
	
	//fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
	
}
