package com.edu.thss.smartdental.adapter;

import java.util.ArrayList;

import com.edu.thss.smartdental.BBSInFragment;
import com.edu.thss.smartdental.R;
import com.edu.thss.smartdental.model.CircleElement;
import com.edu.thss.smartdental.ui.dialog.JoinCircleDialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class JoinCircleListAdapter extends BaseAdapter implements Filterable{

	private ArrayList<CircleElement> list;
	private Context context;
	private FragmentManager fragmentManager;
	private CircleFilter filter;
	
	public JoinCircleListAdapter(ArrayList<CircleElement> list, Context context, FragmentManager fragmentManager) {
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
		final String docName = list.get(position).docName;
		TextView name =(TextView)convertView.findViewById(R.id.circle_list_item_title);
		name.setText(docName);
		
		name.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View v) {
					JoinCircleDialog dialog = new JoinCircleDialog();
					dialog.setDocName(docName);
					dialog.show(fragmentManager, "JoinCircle");
				}
			});
		
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

		private ArrayList<CircleElement> original;
		
		public CircleFilter(ArrayList<CircleElement> list){
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
				
				ArrayList<CircleElement> mList = new ArrayList<CircleElement>();
				for (CircleElement element: original){
					if (element.docName.toUpperCase().contains(searchword.toString().toUpperCase())) {
						mList.add(element);
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
			list = (ArrayList<CircleElement>)results.values;
			notifyDataSetChanged();
		}
		
	}
	
}
