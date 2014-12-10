package com.edu.thss.smartdental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.edu.thss.smartdental.RemoteDB.DBUtil;
import com.edu.thss.smartdental.adapter.CircleListAdapter;
import com.edu.thss.smartdental.model.CircleElement;

import android.app.Activity;
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
	private ArrayList<CircleElement> circles;
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
		circles = new ArrayList<CircleElement>();
		DBUtil db = new DBUtil();
		String username = getActivity().getSharedPreferences("setting", Activity.MODE_PRIVATE).getString("username", "");
		List<HashMap<String, String>> docList = db.selectDoctorsByname(username);
		Iterator<HashMap<String, String>> iterator = docList.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			HashMap<String, String> element = iterator.next();
			CircleElement circleElement = new CircleElement(element.get("doctorname"), element.get("doctorid"));
			circles.add(circleElement);
		}
	}
	
	//public void onResume() {
		//initCircles();
		//listAdapter = new CircleListAdapter(circles, this.getActivity().getApplicationContext());
		//list.setAdapter(listAdapter);
		//list.setOnItemClickListener(new OnCircleItemClickListener());
	//}
	
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
			Fragment fragment= new BBSInFragment(Integer.parseInt(circles.get(position).docID));
			fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
		}
		
	}
	
}
