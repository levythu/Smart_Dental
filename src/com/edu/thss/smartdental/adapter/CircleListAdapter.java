package com.edu.thss.smartdental.adapter;

import java.util.ArrayList;

import com.edu.thss.smartdental.BBSInFragment;
import com.edu.thss.smartdental.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

@SuppressLint("DefaultLocale")
public class CircleListAdapter extends BaseAdapter {

	private ArrayList<String> list;
	private Context context;
	private FragmentManager fragmentManager;
	
	public CircleListAdapter(ArrayList<String> list, Context context, FragmentManager fragmentManager) {
		this.list = list;
		this.context = context;
		this.fragmentManager = fragmentManager;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.circle_list_item, null);
		}
		String docName = list.get(position);
		TextView name =(TextView)convertView.findViewById(R.id.circle_list_item_title);
		name.setText(docName);
		name.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Fragment fragment= new BBSInFragment();
				fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
			}
		});
		
		return convertView;
	}

}
