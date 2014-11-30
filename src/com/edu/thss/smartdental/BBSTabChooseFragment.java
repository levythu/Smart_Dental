package com.edu.thss.smartdental;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.CircleListAdapter;
import com.edu.thss.smartdental.model.EMRElement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Button;
public class BBSTabChooseFragment extends Fragment {

	private FragmentManager fragmentManager;
	private Button choosing;
	private ListView list;
	private ArrayList<String> circles;
	private CircleListAdapter listAdapter;
	
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
		
		list = (ListView) rootView.findViewById(R.id.circle_list);
		initCircles();
		listAdapter = new CircleListAdapter(circles, this.getActivity().getApplicationContext());
		list.setAdapter(listAdapter);
		
		return rootView;
	}
	
	private void initCircles(){
		circles = new ArrayList<String>();
		circles.add("老司机");
		circles.add("陈鸣海医生");
	}
	
	//fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
	
}
