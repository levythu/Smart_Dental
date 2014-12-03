package com.edu.thss.smartdental.adapter;

import java.util.ArrayList;

import com.edu.thss.smartdental.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class JoinCircleListAdapter extends BaseAdapter implements Filterable{

	private ArrayList<String> list;
	private Context context;
	private FragmentManager fragmentManager;
	private CircleFilter filter;
	
	public JoinCircleListAdapter(ArrayList<String> list, Context context, FragmentManager fragmentManager) {
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

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.circle_list_item, null);
		}
		String docName = list.get(position);
		TextView name =(TextView)convertView.findViewById(R.id.circle_list_item_title);
		name.setText(docName);
		
		return convertView;
	}

	@Override
	public Filter getFilter() {
		if (filter == null) {
			filter = new CircleFilter(list);
		}
		return filter;
	}
	
	class CircleFilter extends Filter {

		private ArrayList<String> original;
		
		public CircleFilter(ArrayList<String> list){
			this.original = list;
		}
		
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			String searchword = constraint.toString();
			FilterResults results = new FilterResults();
			
			if (searchword == null || searchword.length()==0) {
				results.values = this.original;
				results.count = this.original.size();
			} else {
				
				ArrayList<String> mList = new ArrayList<String>();
				for(String name: original){
					if (name.toUpperCase().contains(searchword.toString().toUpperCase())) {
						mList.add(name);
					}
				}
				results.values = mList;
				results.count = mList.size();
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			list = (ArrayList<String>)results.values;
			notifyDataSetChanged();
		}
		
	}
	
}
