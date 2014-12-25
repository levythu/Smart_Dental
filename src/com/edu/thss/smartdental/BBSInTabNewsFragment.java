package com.edu.thss.smartdental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.edu.thss.smartdental.adapter.NewsListAdapter;
import com.edu.thss.smartdental.model.NewsElement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BBSInTabNewsFragment extends Fragment {
	
	private ListView listView;
	private NewsListAdapter listAdapter;
	private ArrayList<NewsElement> newsList;
	
	public BBSInTabNewsFragment(ArrayList<NewsElement> newsList) {
		this.newsList = newsList;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_bbs_in_news, container, false);
		
		this.listView = (ListView) rootView.findViewById(R.id.bbs_news_list);
		initNewsList();
		this.listAdapter = new NewsListAdapter(newsList, getActivity());
		listView.setAdapter(listAdapter);
		
		return rootView;
	}

	private void initNewsList() {
		this.newsList = new ArrayList<NewsElement>();
		
		
		NewsElement element = new NewsElement("1", "a", "ABC", "ABC", "1532");
		this.newsList.add(element);
	}
}
