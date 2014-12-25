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
			convertView = LayoutInflater.from(this.context).inflate(R.layout.circle_list_item, null);
		}
		
		NewsElement element = list.get(index);
		String time = element.getTime();
		
		TextView timeView =(TextView)convertView.findViewById(R.id.news_list_item_time);
		timeView.setText(time);
		
		return null;
	}
	
	
	
}