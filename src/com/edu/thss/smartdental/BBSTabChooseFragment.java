package com.edu.thss.smartdental;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.CircleListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
public class BBSTabChooseFragment extends Fragment {

	private FragmentManager fragmentManager;
	private ListView list;
	private ArrayList<String> circles;
	private CircleListAdapter listAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_choose, container, false);
		fragmentManager = getFragmentManager();
		list = (ListView) rootView.findViewById(R.id.circle_list);
		initCircles();
		listAdapter = new CircleListAdapter(circles, this.getActivity().getApplicationContext(), fragmentManager);
		list.setAdapter(listAdapter);
		
		return rootView;
	}
	
	private void initCircles(){
		circles = new ArrayList<String>();
		circles.add("老司机");
		circles.add("陈鸣海医生");
	}
	
}
