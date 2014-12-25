package com.edu.thss.smartdental.adapter;

import java.util.ArrayList;

import com.edu.thss.smartdental.R;
import com.edu.thss.smartdental.model.NewsElement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {

	private ArrayList<NewsElement> list;
	private Context context;
	
	public NewsListAdapter(ArrayList<NewsElement> list, Context context) {
		this.list = list;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Object getItem(int index) {
		return this.list.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(this.context).inflate(R.layout.news_list_item, null);
		}
		
		NewsElement element = list.get(index);
		String time = element.getTime();
		String replyer;
		ArrayList<String> userList = element.getUserNameList();
		switch (userList.size()) {
		case 1:
			replyer = userList.get(0);
			break;
		case 2:
			replyer = userList.get(0) + " " + context.getResources().getString(R.string.and) + " " + userList.get(1);
			break;
		default:
			replyer = userList.get(0) + " " + context.getResources().getString(R.string.etc) + " " + Integer.toString(userList.size()) + " " + context.getResources().getString(R.string.person);
			break;
		}
		String title = context.getResources().getString(R.string.has_replied) + " " + element.getPostTitle();
		
		TextView replyerView = (TextView) convertView.findViewById(R.id.news_list_item_replyer);
		TextView titleView = (TextView) convertView.findViewById(R.id.news_list_item_title);
		TextView timeView = (TextView) convertView.findViewById(R.id.news_list_item_time);
		
		replyerView.setText(replyer);
		titleView.setText(title);
		timeView.setText(time);
		
		return convertView;
	}
	
}
