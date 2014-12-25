package com.edu.thss.smartdental;

import java.util.ArrayList;

import com.edu.thss.smartdental.adapter.NewsListAdapter;
import com.edu.thss.smartdental.model.NewsElement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		this.listAdapter = new NewsListAdapter(newsList, getActivity());
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnNewsListItemClickListener());
		
		return rootView;
	}

	private class OnNewsListItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(getActivity(), BBSDetailActivity.class);
			intent.putExtra("postId", newsList.get(position).getPostID());
			startActivity(intent);
		}
		
	}
}
