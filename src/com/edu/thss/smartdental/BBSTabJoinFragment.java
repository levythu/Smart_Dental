package com.edu.thss.smartdental;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.JoinCircleListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

public class BBSTabJoinFragment extends Fragment {
	
	private FragmentManager fragmentManager;
	private ListView list;
	private ArrayList<String> circles;
	private JoinCircleListAdapter listAdapter;
	private EditText editText;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_join, container, false);
		editText = (EditText)rootView.findViewById(R.id.circle_searchbox);
		editText.addTextChangedListener(filterTextWatcher);
		fragmentManager = getFragmentManager();
		list = (ListView) rootView.findViewById(R.id.join_circle_list);
		initCircles();
		listAdapter = new JoinCircleListAdapter(circles, this.getActivity().getApplicationContext(), fragmentManager);
		list.setAdapter(listAdapter);
		
		return rootView;
	}
	
	private void initCircles() {
		circles = new ArrayList<String>();
		circles.add("老司机");
		circles.add("陈鸣海医生");
	}
	
	private TextWatcher filterTextWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int star, int count,
				int after) {
			listAdapter.getFilter().filter(s);	
		}
		
	};

}
