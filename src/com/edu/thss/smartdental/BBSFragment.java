package com.edu.thss.smartdental;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.CircleListAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
public class BBSFragment extends Fragment {

	private FragmentManager fragmentManager;
	private ListView list;
	private ArrayList<String> circles;
	private CircleListAdapter listAdapter;
	private View join_circle_button;
	private Context context;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs, container, false);
		
		context = this.getActivity().getApplicationContext();
		
		join_circle_button = rootView.findViewById(R.id.join_circle_button);
		join_circle_button.setOnClickListener(new OnJoinButtonClickListener(context));
		
		fragmentManager = getFragmentManager();
		list = (ListView) rootView.findViewById(R.id.circle_list);
		initCircles();
		listAdapter = new CircleListAdapter(circles, this.getActivity().getApplicationContext());
		list.setAdapter(listAdapter);
		list.setOnItemClickListener(new OnCircleItemClickListener());
		
		return rootView;
	}
	
	private void initCircles(){
		circles = new ArrayList<String>();
		circles.add("老司机");
		circles.add("陈鸣海医生");
	}
	
	private class OnJoinButtonClickListener implements OnClickListener {

		private Context context;
		
		public OnJoinButtonClickListener(Context context) {
			this.context = context;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(context,JoinCircleActivity.class);
			startActivity(intent);
		}
		
	}
	
	private class OnCircleItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
				Fragment fragment= new BBSInFragment();
				fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
		}
		
	}
	
}
