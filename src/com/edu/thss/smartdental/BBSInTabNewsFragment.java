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
	private List<HashMap<String, String>> newsListFromDB;
	
	public BBSInTabNewsFragment(List<HashMap<String, String>> newsList) {
		this.newsListFromDB = newsList;
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
		Iterator<HashMap<String, String>> iterator = this.newsListFromDB.iterator();
		
		iterator.next();
		while (iterator.hasNext()) {
			HashMap<String, String> elementFromDB = iterator.next();
			NewsElement element = new NewsElement(elementFromDB.get("newsId"), elementFromDB.get("newstype"), elementFromDB.get("username"), elementFromDB.get("content"), elementFromDB.get("time"));
			this.newsList.add(element);
		}
	}
}
