package com.edu.thss.smartdental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.edu.thss.smartdental.RemoteDB.DBUtil;
import com.edu.thss.smartdental.adapter.JoinCircleListAdapter;
import com.edu.thss.smartdental.model.CircleElement;

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
	private ArrayList<CircleElement> circles;
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
		circles = new ArrayList<CircleElement>();
		/*DBUtil db = new DBUtil();
		List<HashMap<String, String>> docList = db.getAllDoctors();
		for (HashMap<String, String> element: docList) {
			CircleElement circleElement = new CircleElement(element.get("CName"), Integer.parseInt(element.get("CNo")));
			circles.add(circleElement);
		}*/
		CircleElement circleElement = new CircleElement("张三医生", 1);
		circles.add(circleElement);
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
